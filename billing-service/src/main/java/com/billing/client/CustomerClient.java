package com.billing.client;

import com.customer.grpc.CustomerServiceGrpc;
import com.customer.grpc.GetCustomerByIdRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CustomerClient {
    private final CustomerServiceGrpc.CustomerServiceBlockingStub stub;
    public CustomerClient(CustomerServiceGrpc.CustomerServiceBlockingStub stub){
        this.stub = stub;
    }

    @Retry(name="customerService")
    @CircuitBreaker(name="customerService")
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void verifyCustomerExists(Long customerId){
        stub.withDeadlineAfter(5, TimeUnit.SECONDS)
                .getCustomerById(GetCustomerByIdRequest.newBuilder().setId(customerId).build());
    }
}