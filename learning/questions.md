# Questions

Keep unresolved questions concise. Add a date or context when it helps.

## YYYY-MM-DD: Question

- **Context:**
- **Current hypothesis:**
- **Evidence to check:**
- **Status:** unresolved

## 2026-09-19: Which transport actually serves the gRPC listeners — Netty on 9090 or servlet?

- **Context:** customer-service has both `spring-boot-starter-webmvc` (Tomcat on 8082) and `spring-boot-starter-grpc-server`. Spring gRPC docs describe BOTH a Netty server on 9090 AND auto-config that binds gRPC to the servlet container "if it detects that it is in a web application" (via a separate servlet starter).
- **Current hypothesis:** plain `spring-boot-starter-grpc-server` = Netty on 9090 (the servlet path needs its own convenience starter), so we should see 9090 listening after the first `BindableService` exists.
- **Evidence to check:** restart with `GrpcCustomerService` compiled; look for `Registered gRPC service: customer.v1.CustomerService` + `ss -tlnp` for both 8082 and 9090; confirm which port carries gRPC traffic (grpcurl).
- **Status:** unresolved — verify at next boot.

## 2026-09-19: Does IntelliJ pick up `target/generated-sources/protobuf` on a Maven reload?

- **Context:** the `com.customer.grpc` generated package caused a red wall in the IDE. Two candidate fixes: Maven "Reload All Maven Projects" (should register generated sources automatically on re-import) vs manually marking the directory as "Generated Sources Root".
- **Current hypothesis:** a reload is sufficient when the proto exists before import; the manual mark exists for cases where the ascesce plugin's output path isn't auto-registered.
- **Evidence to check:** reload the project, observe whether `com.customer.grpc` resolves; if not, mark `target/generated-sources/protobuf` as Generated Sources Root and confirm.
- **Status:** unresolved — first reload attempt not yet done.

## 2026-09-19: How do we round-trip test the gRPC service?

- **Context:** the service class registers as `customer.v1.CustomerService` on the gRPC port, but REST curl can't reach it. `grpcurl` is not installed.
- **Current hypothesis:** two viable paths — install `grpcurl` (with the auto-registered reflection service, `grpcurl -plaintext localhost:9090 list` works immediately) or write a small client using the generated blocking stub + a channel (pairs naturally with the next phase's "gRPC call").
- **Evidence to check:** confirm Reflection is auto-registered (docs say yes), then test whichever client we build.
- **Status:** unresolved — decided at the "gRPC call" phase.

## 2026-10-01: Why did the first CreateBill hang 42s on `waiting for name_resolver`?

- **Context:** the very first call after starting billing failed with `Code: DeadlineExceeded — ... waiting for name_resolver` after 42.6s, then every subsequent call failed instantly with the frozen-deadline error. A fresh restart fixed it (first call succeeded in 0.266s). `getent hosts localhost` returns **only `::1` (IPv6)** on this machine, and direct tests to both `127.0.0.1:9090` and `[::1]:9090` succeed instantly.
- **Current hypothesis:** two separate issues that got conflated. (1) A cold-channel IPv6 name-resolution path on first use, plausibly a ~40s TCP connect timeout on the `::1` route — this is what the 42.6s was. (2) The frozen deadline, which is the *real* bug and independent of resolution. After the first failure the channel was already warm, so the retry measured 0.019s with "Name resolution delay 0.000000000 seconds" — resolution was never the ongoing problem.
- **Evidence to check:** restart billing and time the *first* call only, then time a second call immediately after. If the 42s recurs on cold start but not warm, it is the IPv6 path and not the deadline. If both are instant, the 42s was a one-off environment artifact (e.g. the machine had just resumed).
- **Status:** unresolved — the frozen-deadline fix removed the visible symptom, so the 42s was never reproduced in isolation. Worth re-testing if a first call is ever slow again; consider `customer.service.host=127.0.0.1` in `application.properties` to bypass the IPv6 route entirely.

## 2026-10-01: Should the breaker be outer (retry inside) instead?

- **Context:** verified from bytecode that `Retry` aspect order = 2147483642 and `CircuitBreaker` = 2147483643; Spring treats the lower value as outer, so **retry wraps the breaker** and each of the 3 attempts is counted separately in the breaker's window. Net effect: 1 logical call = 3 entries, and a real outage trips in ~2 logical calls.
- **Current hypothesis:** the default (breaker inner) is the right choice here because customer-service is a genuine hard dependency — catching a real outage fast matters more than tolerating occasional single-request blips. The swap (breaker outer) would make 1 logical call = 1 entry, so a blip that retry absorbed counts as 0 failures, and the breaker would need ~5 logical calls to trip. The cost is a slower reaction to a true outage.
- **Evidence to check:** the exact property names to flip the order (`resilience4j.retry.circuitBreakerAspectOrder` / `resilience4j.circuitbreaker.retryAspectOrder` are guesses and must be verified before use). Then re-run the 5-scenario battery and compare how many logical calls it takes to trip. Decide with real numbers whether the twitchier default is acceptable for this dependency.
- **Status:** unresolved — defer until Phase 2 metrics make breaker state observable during a load test.

## 2026-10-01: How would a non-idempotent retry be protected here?

- **Context:** established that `@Retry` is currently safe only because `verifyCustomerExists` is a **read**. If billing ever calls a remote service that creates or charges something, the lost-reply case produces a duplicate. The fix is an idempotency key (client-generated unique ID, server stores processed IDs, returns the original result on a duplicate).
- **Current hypothesis:** gRPC has no built-in retry dedup — this is an application concern. A unique request ID carried in a metadata header, with the server checking "have I processed this ID?" before doing the work, is the standard approach. The open sub-question is **where the dedup store lives**: same Postgres DB as the domain write, a separate table, or Redis. Same-DB is simplest and gives the strongest guarantee (the check and the write share a transaction); a separate store can drift.
- **Evidence to check:** if a payment service is ever added, verify whether it already accepts an idempotency header (Stripe's is `Idempotency-Key`) before designing our own. Also confirm whether the same transaction can cover both the "seen this ID" insert and the domain write — if yes, a crash between them cannot produce a duplicate.
- **Status:** unresolved — not needed for the current phase (read-only retry), but required before any remote write is added.