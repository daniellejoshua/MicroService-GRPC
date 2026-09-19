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