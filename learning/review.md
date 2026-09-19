# Review

Keep short retrieval prompts or review metadata for meaningful topics.

## YYYY-MM-DD: Topic

- **Recall prompt:**
- **Last reviewed:**
- **Confidence:**
- **Next review or exercise:**

## 2026-09-19: One gRPC call, end to end

- **Recall prompt:** trace one `getCustomerById` from the client calling `stub.getCustomerById(...)` to your service and back. Name the sender, the receiver, what is a Java object, and where the only bytes live. At which call does serialization happen, and where did deserialization already happen before your method ran?
- **Last reviewed:** 2026-09-19
- **Confidence:** medium
- **Next review or exercise:** explain what would need to exist on the other side (a client) before this round-trip works, and which port it would knock on.

## 2026-09-19: The two Customers and `toProto`

- **Recall prompt:** `CustomerEntity` vs the generated proto `Customer` — which world does each live in, and why can't one class do both jobs? Write `toProto(CustomerEntity)` from memory (without looking).
- **Last reviewed:** 2026-09-19
- **Confidence:** medium
- **Next review or exercise:** add a `phone` field to the proto, the entity, and `toProto`, and predict which files must change and which must NOT.

## 2026-09-19: The 4-move observer pattern

- **Recall prompt:** write the `ping` rpc from memory, then say what changes for `getCustomerById`, `listCustomers`, and `createCustomer`. What are the two exclusive endings for a StreamObserver, and when does `return` appear without `onCompleted`?
- **Last reviewed:** 2026-09-19
- **Confidence:** medium
- **Next review or exercise:** type `getCustomerById`, `listCustomers`, and `createCustomer` for real in `GrpcCustomerService` (still missing on disk), then review them.

## 2026-09-19: Field numbers and wire packing

- **Recall prompt:** why can't a field be number 0? Which numbers cost 1 byte? What does "labels stay, packing flexes" mean? Your proto adds `phone` — which number, and why is 4 better than 500?
- **Last reviewed:** 2026-09-19
- **Confidence:** medium-high
- **Next review or exercise:** open `customer.proto` and explain why the existing numbers (1, 2, 3) must never be reassigned once billing starts calling.

## 2026-09-19: Generated sources are build output, not source

- **Recall prompt:** where do `com.customer.grpc.Customer` and `CustomerServiceGrpc.CustomerServiceImplBase` physically live, who created them, and why doesn't `src/main/java/com/customer/grpc/` exist? Why did IntelliJ show a red wall even though the code was correct?
- **Last reviewed:** 2026-09-19
- **Confidence:** medium
- **Next review or exercise:** predict the effect of `mvn clean` on the IDE's error count, then verify (warning: build is required before the app can boot again).