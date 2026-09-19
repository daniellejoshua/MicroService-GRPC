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