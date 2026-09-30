package com.billing.config;

import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.common.retry.configuration.RetryConfigCustomizer;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Predicate;

@Configuration
public class ResilienceConfig {
    private static boolean isNetworkDown(Throwable t ){
        return t instanceof StatusRuntimeException s && s.getStatus().getCode() == Status.Code.UNAVAILABLE;
    }

    private static boolean isRealFailure(Throwable t ){
        return !(t instanceof StatusRuntimeException s && s.getStatus().getCode() == Status.Code.NOT_FOUND);
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RetryConfigCustomizer customerRetry(){
        Predicate<Throwable> onlyNetworkDown = ResilienceConfig::isNetworkDown;
        return RetryConfigCustomizer.of("customerService",builder -> builder.retryOnException(onlyNetworkDown));

    }
    @Bean
    public CircuitBreakerConfigCustomizer customerBreaker(){
        Predicate<Throwable> realFailuresOnly = ResilienceConfig::isRealFailure;
        return CircuitBreakerConfigCustomizer.of("customerService",builder -> builder.recordException(realFailuresOnly));


    }
}