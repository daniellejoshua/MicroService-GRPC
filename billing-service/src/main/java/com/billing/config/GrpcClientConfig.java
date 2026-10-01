package com.billing.config;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.customer.grpc.CustomerServiceGrpc;


@Configuration
public class GrpcClientConfig {
    @Value("${customer.service.host:localhost}")
    private String host;

    @Value("${customer.service.port:9090}")
    private int port;

    @Bean
    public ManagedChannel customerChannel(){
        return ManagedChannelBuilder.forAddress(host,port).usePlaintext().build();
    }
    @Bean
    public CustomerServiceGrpc.CustomerServiceBlockingStub customerBlockingStub(ManagedChannel channel) {
       return CustomerServiceGrpc.newBlockingStub(channel);
    }

}