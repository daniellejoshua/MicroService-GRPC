# Mistakes

Record meaningful bugs or recurring misconceptions. Preserve the original reasoning.

## YYYY-MM-DD: Short title

- **Problem:**
- **Mistake:**
- **Original mental model:**
- **Correct mental model:**
- **Category:**
- **Prevention:**
- **Follow-up exercise:**

## 2026-09-19: "Expected gRPC 9090 to open with the starter alone"

- **Problem:** after the first successful boot, resulting in `ss -tlnp` showing only 8082, the gRPC door was predicted to be listening on 9090 already.
- **Mistake:** told the learner to expect "two doors" (REST 8082 + gRPC 9090) at boot with two starters in the pom and no service code.
- **Original mental model:** having `spring-boot-starter-grpc-server` on the classpath is enough to open the gRPC port.
- **Correct mental model:** Spring gRPC only starts the server when it finds **at least one bean that implements `BindableService`**. Zero services → no listener. The port opens only after the first service bean (`@GrpcService` extending `CustomerServiceImplBase`) exists.
- **Category:** missing domain knowledge (activation is event-driven, not classpath-driven).
- **Prevention:** check the activation condition ("what bean must exist for this listener to start?") before predicting a port will listen.
- **Follow-up exercise:** predict what changes on the next boot after `GrpcCustomerService` is compiled in, and how to confirm it from the log line.

## 2026-09-19: "The service is the sender" — recap slip

- **Problem:** during the end-of-session recap, the learner stated "service is the sender."
- **Mistake:** the recap slipped one role — earlier in the same session the learner correctly answered "receiving side."
- **Original mental model:** the word "service" sounds like the thing that provides/sends something to the outside world.
- **Correct mental model:** in one gRPC call the *client stub* serializes and sends; the *service class* is the receiver — it wakes up with an already-deserialized request and sends back via the observer pipe. "Service" = the listener on the server side.
- **Category:** terminology slip, not a conceptual gap (corrected earlier, contradicted in the recap).
- **Prevention:** in recaps, place the two actors side by side (client stub = sender, service = receiver) instead of paraphrasing one term in isolation.
- **Follow-up exercise:** name the sender and the receiver for a single `getCustomerById` call, then say which one's bytes get serialized and which one's framework deserializes.

## 2026-09-19: IntelliJ red wall on generated `com.customer.grpc` imports

- **Problem:** "check it now it has so many errors the service file" — IDE flagged many unresolved symbols in `GrpcCustomerService`.
- **Mistake:** suspected the Java code was broken before verifying.
- **Original mental model:** many red lines in the editor = errors in my source file.
- **Correct mental model:** the code was clean; the generated `com.customer.grpc` package sits under `target/generated-sources/protobuf/` and IntelliJ only treats that folder as a source root **after a Maven project reload**. The proto was added after import → IntelliJ never indexed the generated classes → "Cannot resolve symbol" on every `com.customer.grpc.*` import. The last real Maven compile predated `GrpcCustomerService.java` too (`target/classes/.../service/` still showed only `CustomerService.class`), so nothing had recompiled it yet. Terminal `./mvnw compile` is the decisive test, not the IDE.
- **Category:** tooling/IDE-config misconception ("red lines = my bug").
- **Prevention:** distinguish "Cannot resolve symbol" on generated packages (IDE indexing) from real compile errors; run the Maven compile once before debugging the code.
- **Follow-up exercise:** after `Reload All Maven Projects` / marking `target/generated-sources/protobuf` as Generated Sources Root, confirm the red wall disappears with zero code changes — and explain why.

## 2026-09-19: Reported steps 1–3 + properties done, but they weren't

- **Problem:** learner said "i already did the 1-3 steps and set up the application properties." On read-back, `CustomerController.java` was still the full FirstProject copy (importing `com.customer.dto.CustomerRequest` and calling methods on an empty `CustomerService` stub, which cannot compile) and `application.properties` was empty (0 lines — no datasource, no port, no ddl-auto).
- **Mistake:** claimed a state the files didn't have; the controller (step 4) and properties (step 5) were unfinished.
- **Original mental model:** the work was done because the plan items felt familiar from FirstProject; the files hadn't been updated.
- **Correct mental model:** "done" must be verified against the actual files. A controller referencing a missing `dto` package + an empty service = compile error; an empty `application.properties` = no datasource → Spring cannot start ("Failed to configure a DataSource"). State claims are checked by reading files, not by plan familiarity. (This is the inverse of the lesson — remember self-checks.)
- **Category:** state-misreporting / verification lapse.
- **Prevention:** before saying "done," open each claimed file and confirm its contents match the intended phase.
- **Follow-up exercise:** list the two concrete consequences that would have shown up on the very first boot with those files, in the order they'd appear.

## 2026-10-01: `withDeadlineAfter` frozen on a singleton stub

- **Problem:** after the deadline was moved to the call site, every `CreateBill` worked. Before the move, the first call succeeded but **every call after ~5s failed instantly** with `Code: DeadlineExceeded — ClientCall started after CallOptions deadline was exceeded -7.6s ago`. Isolated with a two-call test: call at t=0s → bill created; call at t=6s → dead. Same app, same running services, no restart in between.
- **Mistake:** `GrpcClientConfig.customerBlockingStub` returned `newBlockingStub(channel).withDeadlineAfter(5, TimeUnit.SECONDS)`. The deadline was baked into a Spring singleton `@Bean`, so the absolute expiry timestamp was computed **once at startup** and never recomputed.
- **Original mental model:** "the timeout 5s is in the channel which is a bug" — and "we are not updating the note because we have it in the client config which is a config for grpc start up." The second half was exactly right and identified the real cause.
- **Correct mental model:** `withDeadlineAfter` does not start a timer per call. It **converts a duration into an absolute instant** and stamps that instant onto a *new stub*, then that stub is reused. Verified in the bytecode of `AbstractStub.withDeadlineAfter` — it calls `CallOptions.withDeadlineAfter(...)` and `build(...)`; there is no timer, counter, or per-call logic. Put on a singleton, the stamp ages like a stopped clock. General rule: **absolute timestamps belong on per-call objects, never on singletons.** The `ManagedChannel` was innocent — a shared channel is correct gRPC practice; the bug was the *timestamp* on a long-lived object.
- **Category:** object-lifetime misconception (singleton vs per-call), reinforced by a misleading method name.
- **Prevention:** ask "does this value change per call, and where does this expression run?" A `@Configuration`/`@Bean` method runs once at startup — nothing per-call can live there. Fixed values (host, port, channel) belong in config; changing values (deadline, headers, request id) belong at the call site or in a `ClientInterceptor` (interceptors run per call, so a deadline there is *not* frozen).
- **Follow-up exercise:** place a 30s timeout on `GetBill`'s future `findBillAsync` call and predict from memory whether it will freeze the same way. Then verify with the t=0s / t=6s two-call test.

## 2026-10-01: Config-value labels flipped — three knobs, three homes

- **Problem:** exam question "which is the deadline: `wait-duration-in-open-state=30s` or `max-attempts=3`?" answered "deadline is 30s and max-attempts is retry."
- **Mistake:** `retry` was correct; `30s` was labelled *deadline* when it is the **breaker's** open-state wait. The actual deadline was never named — it is `withDeadlineAfter(5, TimeUnit.SECONDS)` in **Java code**, not in properties at all.
- **Original mental model:** all the resilience numbers live together in `application.properties`, so they were treated as interchangeable.
- **Correct mental model:** the knobs are split across three files, and which file a number lives in tells you what it controls. **Java — `CustomerClient`:** the 5s per-attempt deadline (`withDeadlineAfter`) and the two status predicates. **`application.properties` (retry):** `max-attempts=3`, `wait-duration=500ms`, exponential backoff. **`application.properties` (breaker):** `sliding-window-size=10`, `minimum-number-of-calls=5`, `failure-rate-threshold=50`, `wait-duration-in-open-state=30s`. Only the numbers belong in properties; the *decisions* (which status is retryable) need code because properties can't express a predicate.
- **Category:** terminology/recall slip, not a conceptual gap — the breaker state machine and the idempotency reasoning were both solid (10 of 13 correct overall).
- **Prevention:** when three similar-looking config blocks are in play, name the file each number came from before explaining its behavior. The filename is the label.
- **Follow-up exercise:** from memory, fill a four-column table — knob, file, value, effect — for all eight resilience settings currently in the project.

## 2026-10-01: Breaker described as a call count, not a failure rate

- **Problem:** "when there are 5>= calls the breaker will be open"; "so just 2 calls that has 3 retries every each right"; "3 calls has 3 retries so 9?"; "does every retry count as 1 in the thing that needs 50%".
- **Mistake:** the learner counted *calls* and predicted 9 entries for 3 logical calls. The breaker never reaches 9 — once OPEN it rejects before any I/O, so **no new entries are recorded**. Rejected calls tell you nothing about the server, which is the point of the mechanism.
- **Original mental model:** "more than N calls → open," a tally rather than a rate. The `5` was remembered as the trigger; the `50%` and the `minimum-number-of-calls` gate were not wired together.
- **Corrected understanding:** the breaker needs **5 entries minimum** *before it judges anything* (3 entries at 100% failure stays CLOSED), then measures **≥50% failure over the last 10**. Server 100% down → the 5th entry trips it. 5 entries with 3 failed (60%) → also opens. `sliding-window-size=10` is the maximum buffer, never a requirement. And because retry wraps the breaker (aspect orders 2147483642 vs 2147483643, lower = outer), each retry attempt passes through it — so 1 logical call = 3 entries and a real outage trips in **2 logical calls**, which the live test confirmed (1st call 1.517s, 2nd onwards 0.012s).
- **Category:** state-machine misreading — the *effect* of an open breaker (stop counting) was inverted into (keep counting).
- **Prevention:** for any rate-based threshold, write the two conditions as a conjunction before predicting: "at least N **and** at least X% of the window." A raw count is almost never the trigger.
- **Follow-up exercise:** given a window of 10 entries with 4 failures, state whether the breaker opens, and explain what would need to change for the answer to be yes.