package com.customer.service;

import com.customer.entity.CustomerEntity;
import com.customer.grpc.*;
import com.customer.repository.CustomerRepository;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Optional;

@GrpcService
public class GrpcCustomerService extends CustomerServiceGrpc.CustomerServiceImplBase {

    private final CustomerRepository customerRepository;
    private static final String EMAIL_PATTERN = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$";

    public GrpcCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void ping(PingRequest request, StreamObserver<PingResponse> responseObserver) {
        PingResponse reply = PingResponse.newBuilder()
                .setMessage("pong")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
    private Customer toProto(CustomerEntity entity) {
        return Customer.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .build();
    }
    @Override
    public void createCustomer(CreateCustomerRequest request, StreamObserver<CreateCustomerResponse> responseObserver){
        if (request.getName().isBlank()){
            responseObserver.onError(new StatusException(Status.INVALID_ARGUMENT.withDescription("Name must not be blank")));
            return;
        }

        if (!request.getEmail().matches(EMAIL_PATTERN)){
            responseObserver.onError(new StatusException(Status.INVALID_ARGUMENT.withDescription("Email must be valid")));
            return;
        }


        if (customerRepository.findByEmail(request.getEmail()).isPresent()){
            responseObserver.onError(new StatusException(Status.ALREADY_EXISTS.withDescription("Email is already registered")));
            return;
        }

        try {
            CustomerEntity entity = new CustomerEntity();
            entity.setName(request.getName());
            entity.setEmail(request.getEmail());

            CustomerEntity saved = customerRepository.save(entity);

            CreateCustomerResponse reply = CreateCustomerResponse.newBuilder()
                .setCustomer(toProto(saved)).build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
    }   catch (DataIntegrityViolationException e) {
            responseObserver.onError(new StatusException(Status.ALREADY_EXISTS.withDescription("Email is already registered")));
    }
}
    @Override
    public void listCustomers(ListCustomersRequest request, StreamObserver<ListCustomersResponse> responseObserver){
        List<CustomerEntity> all_customer = customerRepository.findAll();
        List<Customer> proto = all_customer.stream().map(this::toProto).toList();

        ListCustomersResponse reply = ListCustomersResponse.newBuilder().addAllCustomers(proto).build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getCustomerById(GetCustomerByIdRequest request, StreamObserver<GetCustomerByIdResponse> responseObserver){
        Long id = request.getId();
        Optional<CustomerEntity> opt = customerRepository.findById(id);

        if (opt.isEmpty()){
            responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription("Customer not Found with the id:" + id)));
            return;
        }

        Customer proto = toProto(opt.get());
        GetCustomerByIdResponse reply = GetCustomerByIdResponse.newBuilder().setCustomer(proto).build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }


}