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

## 2026-10-01: The frozen deadline — the cost of a value in the wrong scope

- **Recall prompt:** why did every call fail 5 seconds after startup with `deadline exceeded -7.6s ago`? Where was the deadline written, when did that expression run, and what one-line rule would have prevented it? Which object was NOT at fault, and why is sharing it correct?
- **Last reviewed:** 2026-10-01
- **Confidence:** high (found it, diagnosed it, fixed it, proved the fix with a t=0s / t=6s two-call test)
- **Next review or exercise:** add a 30s deadline to the *future* `findBillAsync` call and predict from memory whether it freezes the same way. Then verify with the two-call timing test.

## 2026-10-01: The three resilience layers and where each number lives

- **Recall prompt:** name the three layers (deadline / retry / breaker) and for each: what question does it answer, and does it fail slow or fail fast? Then fill a four-column table from memory — knob, file, value, effect — for all eight settings: 5s, `max-attempts`, `wait-duration`, backoff multiplier, `sliding-window-size`, `minimum-number-of-calls`, `failure-rate-threshold`, `wait-duration-in-open-state`.
- **Last reviewed:** 2026-10-01
- **Confidence:** medium — breaker mechanics solid (trip threshold, half-open all-or-nothing, error codes), but the *which-file-does-this-number-live-in* recall failed on the exam
- **Next review or exercise:** reconstruct the eight-row table cold, then open the two files and check every row.

## 2026-10-01: The breaker is a rate, and the retry wraps it

- **Recall prompt:** with `minimum-number-of-calls=5`, `sliding-window-size=10`, `failure-rate-threshold=50`, and a server that is 100% down — how many entries until it trips? Why can it never reach 9? A window of 5 entries with 3 failed — open or not? And how many breaker entries does one logical call contribute, given the aspect orders 2147483642 (retry) and 2147483643 (breaker)?
- **Last reviewed:** 2026-10-01
- **Confidence:** medium-high — answered the rate question correctly when given the comparison, but predicted entry counts from a tally instead
- **Next review or exercise:** draw the full state machine CLOSED → OPEN → HALF_OPEN → CLOSED with the transition condition written next to each arrow, and mark where the 3 half-open probes come from (real user calls, not test pings).

## 2026-10-01: Retry safety — idempotency and side effects

- **Recall prompt:** `@Retry` is on `verifyCustomerExists`. Why is that safe? Move the same annotation to a remote card charge and describe the exact failure. What is the fix, and why must it be applied *before* lowering `max-attempts`?
- **Last reviewed:** 2026-10-01
- **Confidence:** high (raised the idempotency question unprompted — the strongest signal in this phase)
- **Next review or exercise:** design the idempotency key end to end: what the client generates, what header carries it, where the server stores processed IDs, and what it returns on a duplicate. Then name the failure mode if that store is lost.