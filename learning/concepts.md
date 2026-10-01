# Concepts

Record durable understanding, not every definition encountered.

## YYYY-MM-DD: Title

- **Learner's explanation:** ...
- **Corrected understanding:** ...
- **Example:** ...
- **Related concepts:** ...

## 2026-09-19: The .proto file is the contract — verbs and nouns

- **Learner's explanation:** "proto file is like the contract, service is the sender, and message is what will be sent"; asked to step back with "what is proto" and later "can the value change depending on the value we sent?"
- **Corrected understanding:** a `.proto` file is a plain-text, language-neutral contract. Compiler (`protoc`) turns it into real Java/C++/Python classes. Two kinds of declarations: **service** = behavior (verbs: Ping, GetCustomerById, ListCustomers, CreateCustomer) and **message** = data (nouns: request/response types, `Customer` with id/name/email). The contract idea is the point: both the server AND any future client compile the *same* file, so they can't drift apart silently. `option java_package` and `package` are separate namespaces (wire name vs Java package); `java_multiple_files = true` = one `.java` per message instead of a nest inside one outer class.
- **Example:** `message Customer { int64 id = 1; string name = 2; string email = 3; }` → generated immutable `Customer.java` in `com.customer.grpc`.
- **Related concepts:** proto2 vs proto3, IDL (interface definition language), protoc, generated sources, JSON (free-form text) vs proto (compile-time-typed + binary).

## 2026-09-19: Field numbers — labels stay, packing flexes

- **Learner's explanation:** "1 is the first in case there is 2?"; "it should be less than 9 since it will be needed in an 8bit?"; "the value can change depending on the value we sent?"
- **Corrected understanding:** field numbers are **permanent labels**. The number travels on the wire, not the name — renaming is safe, changing a number breaks old stored data. **0 is reserved** (protobuf uses it as a special marker internally). `1`–`15` cost 1 byte (put hot fields there), `16`+ cost extra; `19000`–`19999` are reserved by the format. There is NO "under 9 / 8-bit" law. Numbers encode as **varints**: value 0/empty → *nothing is sent*, small values pack small, big values take more bytes — the label stays fixed, the packing flexes to the value.
- **Example:** adding `phone` to `Customer` → `4` (next free, in the cheap range); `phone = 500` would *work*, just waste bytes.
- **Related concepts:** varint wire encoding, tag = (field_number << 3) | wire_type, `reserved` for deleting numbers, backward/forward compatibility.

## 2026-09-19: Two Customers — entity (DB) vs proto (wire), service is the translator

- **Learner's question:** "its just like the replication of customer table right? i mean customer entity is table named Customer as well"
- **Corrected understanding:** **`CustomerEntity`** (your `@Entity` class, JPA) is the Java mirror of the `customer` table — in fact `ddl-auto=update` *created* the table from it. The generated proto **`Customer`** (in `com.customer.grpc`) is a different class from a different world: the *network* version. It only looks like the table because the `.proto` says so; it knows nothing about databases. Two classes, same idea, different worlds — JPA class can't speak gRPC, proto class can't speak Postgres. The whole job of the service layer = converting between them (the `.set(...)` copying in `toProto`).
- **Example:** same person two outfits — `CustomerEntity` = uniform for the database, `Customer` = party outfit for the wire. One can never wear the other's clothes.
- **Related concepts:** JPA entities vs transport DTOs, `@Table(name="customer")`, generated vs hand-written classes, field-by-field mapping.

## 2026-09-19: The network loop — we build and choose, the framework ships

- **Learner's model (after coaching):** "us programmers are mainly for serialization"; then corrected to "we just build and choose what we pass and the framework handles the serialization and deserialization."
- **Corrected understanding:** you never write serialization. protoc generates the pack/unpack calls (`toByteArray()` / `parseFrom(bytes)`) and the gRPC framework calls them for you. The programmer writes: (1) the shapes (the `.proto`), (2) the logic (receive → decide → build → send), (3) the flow (repository calls, entity↔proto mapping). The wire only ever carries **bytes**; Java objects never leave their own program.
- **Where the pieces live:** client side = **stub** (sender), server side = your **service class** (receiver). Clients serialize their request → bytes travel → server framework deserializes → your method wakes up with an already-unpacked `request` object → you reply → bytes return.
- **Example:** future billing service calls `CustomerServiceGrpc.*BlockingStub` → bytes to localhost:9090 → `GrpcCustomerService.getCustomerById(...)` runs → bytes back.
- **Related concepts:** TCP, HTTP/2 (what Netty speaks), serialization vs deserialization, ports as front doors (8082 REST, 9090 gRPC), "receiving side vs sending side."

## 2026-09-19: gRPC method anatomy — the 4 moves and the observer endings

- **Learner's question:** "what is the use of toProto"; "so this part is like serializing it again back to the wire?"
- **Corrected understanding:** every unary rpc override is the same template — *receive → do the DB work → build the reply with `newBuilder()...build()` → `onNext(reply)` + `onCompleted()`*. No `return` statement: the `StreamObserver` is the pipe back to the caller. Better: `build()` only creates a Java object in memory; **serialization happens inside `onNext`** when the framework ships it. Endings are exclusive: happy = `onNext` then `onCompleted()`; problem = `onError(...)` then `return` — never both. There's no deserialize step to write: the incoming `request` was unpacked before your method ran.
- **Key ingredients:** `@GrpcService` (`org.springframework.grpc.server.service.GrpcService`) on a class extending the generated `CustomerServiceGrpc.CustomerServiceImplBase`; constructor takes `CustomerRepository`. The `toProto(entity)` private helper = write the entity→proto copy once, call it from the 3 methods that need it (edit one place if a field is added).
- **Example:** `ping` is the minimal template — `PingResponse.newBuilder().setMessage("pong").build(); observer.onNext(reply); observer.onCompleted();`
- **Related concepts:** StreamObserver lifecycle, immutable proto builders, unary vs streaming rpc, the four rpc shapes (`rpc A returns B`, `stream` variants), component scanning for the `@GrpcService` bean.

## 2026-09-19: Generated sources are NOT in src — protoc writes them to target

- **Learner's suspicion:** "there is something missing on my structure" / "i mean the grpc package?"
- **Corrected understanding:** `com.customer.grpc` is a generated package — it lives under `target/generated-sources/protobuf/com/customer/grpc/`, not under `src/main/java`. You never create it; `customer.proto` + the ascopes protobuf-maven-plugin (pinned by the Spring Boot parent) create it on every build. That's why `GrpcCustomerService` can import `com.customer.grpc.Customer` without any `grpc` folder in the source tree. If IntelliJ shows a red wall on those imports, it's the IDE not having registered the generated folder as a source root (Maven reload), not a Java error.
- **Example:** `target/classes/.../service/` holding only `CustomerService.class` after an old build = the new `GrpcCustomerService.java` simply hasn't been recompiled yet.
- **Related concepts:** generate-sources phase, build output vs source tree, IDE Maven reload, `Cannot resolve symbol` vs real compile errors.

## 2026-09-19: gRPC flow recap (the whole model the learner rebuilt)

- **Learner's final recap:** "proto file is like the contract, service is the sender, and message is what will be sent, and next is the we just build and choose what to send and the framework does it for us."
- **Corrected understanding:** the recap was right except one word — **service is the RECEIVER, not the sender**. The sender is the client (its stub). Corrected recap: (1) proto = the contract both sides agree on; (2) service = the receiver that runs logic when a request arrives; (3) message = the data passed; (4) we build objects + choose what to pass, the framework serializes, ships, and deserializes.
- **Related concepts:** client vs server roles, stub vs service, the wire model, contract-first development.

## 2026-10-01: Stub, bean, and channel — the three gRPC client objects

- **Learner's questions:** "what is stub again in very simple words"; "what's a blocking stub is synchronous right"; "what is a stub again and a bean in a very simple terms".
- **Corrected understanding:** three distinct objects with different lifetimes. **Stub** = the tool you call (looks like a local method, secretly ships bytes over the network). **Bean** = an object Spring creates once at startup and shares with everyone who injects it. **Channel** = the long-lived TCP connection underneath, shared by design so connections get reused. `ManagedChannel` = real connection; stub = a *view* on that channel; Spring bean = whatever scope you declare it in.
- **The rule that caused the bug:** a `@Bean` is a singleton created **once**, so anything computed there is computed once. Long-lived objects (channel, stub) are correct beans; anything that must change per call (a deadline) is not.
- **Example:** `CustomerClient(CustomerServiceBlockingStub stub)` — Spring hands the client the shared stub. One channel, one stub, every caller.
- **Related concepts:** dependency injection, singleton scope, blocking vs async stubs, `usePlaintext()` vs TLS.

## 2026-10-01: Blocking stub + the deadline is a ceiling, not a wait

- **Learner's question:** "so a blocking stub is synchronous right"; "no service call should last more than 5 seconds".
- **Corrected understanding:** a **blocking** stub parks the calling thread until the reply arrives (or the deadline fires). That is exactly why it needs a deadline: without one, a dead server hangs the thread **forever** (observed directly — a `CreateBill` never returned while customer-service was down). `withDeadlineAfter(5, SECONDS)` caps the wait; it does not consume 5s. A refused connection fails in ~0.02s; only a server that *accepts and never answers* burns the full 5s. And the deadline is **per attempt**, so 3 retries = up to 15s of deadline time.
- **Side effect to remember:** a blocking call inside a gRPC *server* method blocks a server thread. `GrpcBillService.createBill` looks async (it takes a `StreamObserver`) but the moment it calls `verifyCustomerExists`, its server thread is parked. That is why bulkheads exist in a later phase.
- **Example:** server dead + connection refused → measured **1.5s** total, not 15s or 16.5s, because each attempt failed instantly.
- **Related concepts:** `StatusRuntimeException` (client-side) vs `StatusException` (server-side), thread pools, tail latency.

## 2026-10-01: The three resilience layers — deadline, retry, circuit breaker

- **Learner's model:** "the call will just hang for 5s then it will die and when there are more than 10 tries the breaker will automatically says unavailable."
- **Corrected understanding:** three layers answering one problem (customer-service isn't answering), each reacting differently. **Deadline** = "wait at most 5s per attempt, then give up." **Retry** = "try up to 3 times on `UNAVAILABLE`, waiting 0.5s then 1.0s (exponential backoff) — maybe it's a blip." **Circuit breaker** = "it's really dead, stop knocking." Three corrections to the original model: (1) the 5s is a *ceiling*, not a fixed wait; (2) the breaker counts a **failure rate over a window** (≥50% of the last 10, minimum 5 calls), not a raw count; (3) after 30s it goes to **HALF_OPEN, not straight to CLOSED**.
- **Why each exists:** deadline protects a *thread*, retry covers *transient* failures, breaker prevents the *death spiral* where every request pays the full retry cost against a dead server.
- **Example:** dead server — 1st call 1.517s (3 attempts + backoff), 2nd call 0.012s (breaker now open). Same storm, 20 requests: 1.5s total instead of 30s.
- **Related concepts:** exponential backoff, sliding window, half-open probes, fail-fast, call isolation.

## 2026-10-01: The breaker counts a rate, not a tally

- **Learner's questions:** "when there are 5>= calls the breaker will be open"; "does every retry count as 1 in the thing that needs 50%"; "then the second breaker will open and if its open after 30s it will close"; "does the 3 needs to be sucessfull or it can be 1 of 2 or 2 of 3".
- **Corrected understanding:** `minimum-number-of-calls=5` means the breaker **ignores everything** until 5 entries exist — 3 entries at 100% failure still stays CLOSED. Then it judges: `failure-rate-threshold=50` over `sliding-window-size=10`. Server 100% down → the 5th entry trips it. 5 entries with 3 failed = 60% → also opens. Counting alone would let ordinary noise trip it, which is why it's a rate.
- **Retry inner, breaker outer/inner matters:** verified from bytecode that `Retry` order = 2147483642 and `CircuitBreaker` = 2147483643, and lower = outer, so **retry wraps the breaker**. Each attempt passes *through* it → 1 logical call = **3 breaker entries**, so a real outage trips in ~2 logical calls. Swapping the order gives 1 entry per logical call (slower to trip, but immune to a blip that retry absorbed).
- **Half-open is all-or-nothing:** after 30s, the first **3 real user calls** (not test pings) are let through as probes. 3 of 3 pass → CLOSED. Any single failure → back to OPEN for another 30s. Not "2 of 3" — a server failing 1-in-3 would look healthy and get full traffic while dropping calls.
- **Error code tells you which layer acted:** server down + breaker closed → `Unavailable` (real gRPC status). Breaker open → `Unknown`, because the breaker throws `CallNotPermittedException` (a plain `RuntimeException`, not a `StatusRuntimeException`) and gRPC has no status for it.
- **Example:** 1st call → 3 entries, breaker ignores (below 5). 2nd call → attempt 1 = entry 4, attempt 2 = entry 5 → **OPEN** → attempt 3 and every later call rejected in 0.012s.
- **Related concepts:** aspect order, `@Order` semantics, failure-rate vs failure-count, degraded vs rejected traffic.

## 2026-10-01: Retry is only safe on idempotent operations

- **Learner's question:** "what will trigger the retry? will it like be idempotent" — the right question to ask before shipping any retry.
- **Corrected understanding:** a retry assumes the first attempt failed. Sometimes it didn't — the server completed the work and the **reply was lost**. `Attempt 1: card charged, reply lost → billing sees UNAVAILABLE → Attempt 2: charged again`. The client cannot distinguish "never arrived" from "arrived, answer lost." Any operation with a **side effect** (create, charge, delete, send) is unsafe to retry without protection.
- **The fix is an idempotency key:** the client sends a unique ID with each create; the server remembers processed IDs and returns the *original* result instead of doing the work twice. That's how Stripe and PayPal avoid double-charging. Order of fixes matters — add the key *first*, then retries become safe and you keep the blip recovery.
- **The current code is safe by placement:** `@Retry` sits on `verifyCustomerExists`, which only **reads** (`getCustomerById`). Reads have no side effects, so retrying 3 times is free of duplicates. The actual bill write is a **local** `billRepository.save(...)` — no network, nothing to retry. Had the retry been on a remote create, this bug would already exist.
- **Example:** 5 entries, 3 failed = 60% ≥ 50% → OPEN. Contrast with the lost-reply charge: retry without a key = customer paid twice.
- **Related concepts:** idempotency, at-least-once vs exactly-once, side effects, transactional outbox, safe-to-retry status codes.