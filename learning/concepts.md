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