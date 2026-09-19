package com.customer.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class CustomerServiceGrpc {

  private CustomerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "customer.v1.CustomerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.customer.grpc.PingRequest,
      com.customer.grpc.PingResponse> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Ping",
      requestType = com.customer.grpc.PingRequest.class,
      responseType = com.customer.grpc.PingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.customer.grpc.PingRequest,
      com.customer.grpc.PingResponse> getPingMethod() {
    io.grpc.MethodDescriptor<com.customer.grpc.PingRequest, com.customer.grpc.PingResponse> getPingMethod;
    if ((getPingMethod = CustomerServiceGrpc.getPingMethod) == null) {
      synchronized (CustomerServiceGrpc.class) {
        if ((getPingMethod = CustomerServiceGrpc.getPingMethod) == null) {
          CustomerServiceGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.customer.grpc.PingRequest, com.customer.grpc.PingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customer.grpc.PingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customer.grpc.PingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CustomerServiceMethodDescriptorSupplier("Ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.customer.grpc.GetCustomerByIdRequest,
      com.customer.grpc.GetCustomerByIdResponse> getGetCustomerByIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCustomerById",
      requestType = com.customer.grpc.GetCustomerByIdRequest.class,
      responseType = com.customer.grpc.GetCustomerByIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.customer.grpc.GetCustomerByIdRequest,
      com.customer.grpc.GetCustomerByIdResponse> getGetCustomerByIdMethod() {
    io.grpc.MethodDescriptor<com.customer.grpc.GetCustomerByIdRequest, com.customer.grpc.GetCustomerByIdResponse> getGetCustomerByIdMethod;
    if ((getGetCustomerByIdMethod = CustomerServiceGrpc.getGetCustomerByIdMethod) == null) {
      synchronized (CustomerServiceGrpc.class) {
        if ((getGetCustomerByIdMethod = CustomerServiceGrpc.getGetCustomerByIdMethod) == null) {
          CustomerServiceGrpc.getGetCustomerByIdMethod = getGetCustomerByIdMethod =
              io.grpc.MethodDescriptor.<com.customer.grpc.GetCustomerByIdRequest, com.customer.grpc.GetCustomerByIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCustomerById"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customer.grpc.GetCustomerByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customer.grpc.GetCustomerByIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CustomerServiceMethodDescriptorSupplier("GetCustomerById"))
              .build();
        }
      }
    }
    return getGetCustomerByIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.customer.grpc.ListCustomersRequest,
      com.customer.grpc.ListCustomersResponse> getListCustomersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListCustomers",
      requestType = com.customer.grpc.ListCustomersRequest.class,
      responseType = com.customer.grpc.ListCustomersResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.customer.grpc.ListCustomersRequest,
      com.customer.grpc.ListCustomersResponse> getListCustomersMethod() {
    io.grpc.MethodDescriptor<com.customer.grpc.ListCustomersRequest, com.customer.grpc.ListCustomersResponse> getListCustomersMethod;
    if ((getListCustomersMethod = CustomerServiceGrpc.getListCustomersMethod) == null) {
      synchronized (CustomerServiceGrpc.class) {
        if ((getListCustomersMethod = CustomerServiceGrpc.getListCustomersMethod) == null) {
          CustomerServiceGrpc.getListCustomersMethod = getListCustomersMethod =
              io.grpc.MethodDescriptor.<com.customer.grpc.ListCustomersRequest, com.customer.grpc.ListCustomersResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListCustomers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customer.grpc.ListCustomersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customer.grpc.ListCustomersResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CustomerServiceMethodDescriptorSupplier("ListCustomers"))
              .build();
        }
      }
    }
    return getListCustomersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.customer.grpc.CreateCustomerRequest,
      com.customer.grpc.CreateCustomerResponse> getCreateCustomerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateCustomer",
      requestType = com.customer.grpc.CreateCustomerRequest.class,
      responseType = com.customer.grpc.CreateCustomerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.customer.grpc.CreateCustomerRequest,
      com.customer.grpc.CreateCustomerResponse> getCreateCustomerMethod() {
    io.grpc.MethodDescriptor<com.customer.grpc.CreateCustomerRequest, com.customer.grpc.CreateCustomerResponse> getCreateCustomerMethod;
    if ((getCreateCustomerMethod = CustomerServiceGrpc.getCreateCustomerMethod) == null) {
      synchronized (CustomerServiceGrpc.class) {
        if ((getCreateCustomerMethod = CustomerServiceGrpc.getCreateCustomerMethod) == null) {
          CustomerServiceGrpc.getCreateCustomerMethod = getCreateCustomerMethod =
              io.grpc.MethodDescriptor.<com.customer.grpc.CreateCustomerRequest, com.customer.grpc.CreateCustomerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateCustomer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customer.grpc.CreateCustomerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.customer.grpc.CreateCustomerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CustomerServiceMethodDescriptorSupplier("CreateCustomer"))
              .build();
        }
      }
    }
    return getCreateCustomerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CustomerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomerServiceStub>() {
        @java.lang.Override
        public CustomerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomerServiceStub(channel, callOptions);
        }
      };
    return CustomerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static CustomerServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomerServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomerServiceBlockingV2Stub>() {
        @java.lang.Override
        public CustomerServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomerServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return CustomerServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CustomerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomerServiceBlockingStub>() {
        @java.lang.Override
        public CustomerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomerServiceBlockingStub(channel, callOptions);
        }
      };
    return CustomerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CustomerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CustomerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CustomerServiceFutureStub>() {
        @java.lang.Override
        public CustomerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CustomerServiceFutureStub(channel, callOptions);
        }
      };
    return CustomerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void ping(com.customer.grpc.PingRequest request,
        io.grpc.stub.StreamObserver<com.customer.grpc.PingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    default void getCustomerById(com.customer.grpc.GetCustomerByIdRequest request,
        io.grpc.stub.StreamObserver<com.customer.grpc.GetCustomerByIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCustomerByIdMethod(), responseObserver);
    }

    /**
     */
    default void listCustomers(com.customer.grpc.ListCustomersRequest request,
        io.grpc.stub.StreamObserver<com.customer.grpc.ListCustomersResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListCustomersMethod(), responseObserver);
    }

    /**
     */
    default void createCustomer(com.customer.grpc.CreateCustomerRequest request,
        io.grpc.stub.StreamObserver<com.customer.grpc.CreateCustomerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateCustomerMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CustomerService.
   */
  public static abstract class CustomerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CustomerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CustomerService.
   */
  public static final class CustomerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CustomerServiceStub> {
    private CustomerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CustomerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomerServiceStub(channel, callOptions);
    }

    /**
     */
    public void ping(com.customer.grpc.PingRequest request,
        io.grpc.stub.StreamObserver<com.customer.grpc.PingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCustomerById(com.customer.grpc.GetCustomerByIdRequest request,
        io.grpc.stub.StreamObserver<com.customer.grpc.GetCustomerByIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCustomerByIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listCustomers(com.customer.grpc.ListCustomersRequest request,
        io.grpc.stub.StreamObserver<com.customer.grpc.ListCustomersResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListCustomersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createCustomer(com.customer.grpc.CreateCustomerRequest request,
        io.grpc.stub.StreamObserver<com.customer.grpc.CreateCustomerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateCustomerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CustomerService.
   */
  public static final class CustomerServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<CustomerServiceBlockingV2Stub> {
    private CustomerServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CustomerServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomerServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.customer.grpc.PingResponse ping(com.customer.grpc.PingRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.customer.grpc.GetCustomerByIdResponse getCustomerById(com.customer.grpc.GetCustomerByIdRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetCustomerByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.customer.grpc.ListCustomersResponse listCustomers(com.customer.grpc.ListCustomersRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getListCustomersMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.customer.grpc.CreateCustomerResponse createCustomer(com.customer.grpc.CreateCustomerRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateCustomerMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service CustomerService.
   */
  public static final class CustomerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CustomerServiceBlockingStub> {
    private CustomerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CustomerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomerServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.customer.grpc.PingResponse ping(com.customer.grpc.PingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.customer.grpc.GetCustomerByIdResponse getCustomerById(com.customer.grpc.GetCustomerByIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCustomerByIdMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.customer.grpc.ListCustomersResponse listCustomers(com.customer.grpc.ListCustomersRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListCustomersMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.customer.grpc.CreateCustomerResponse createCustomer(com.customer.grpc.CreateCustomerRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateCustomerMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CustomerService.
   */
  public static final class CustomerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CustomerServiceFutureStub> {
    private CustomerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CustomerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CustomerServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.customer.grpc.PingResponse> ping(
        com.customer.grpc.PingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.customer.grpc.GetCustomerByIdResponse> getCustomerById(
        com.customer.grpc.GetCustomerByIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCustomerByIdMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.customer.grpc.ListCustomersResponse> listCustomers(
        com.customer.grpc.ListCustomersRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListCustomersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.customer.grpc.CreateCustomerResponse> createCustomer(
        com.customer.grpc.CreateCustomerRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateCustomerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PING = 0;
  private static final int METHODID_GET_CUSTOMER_BY_ID = 1;
  private static final int METHODID_LIST_CUSTOMERS = 2;
  private static final int METHODID_CREATE_CUSTOMER = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PING:
          serviceImpl.ping((com.customer.grpc.PingRequest) request,
              (io.grpc.stub.StreamObserver<com.customer.grpc.PingResponse>) responseObserver);
          break;
        case METHODID_GET_CUSTOMER_BY_ID:
          serviceImpl.getCustomerById((com.customer.grpc.GetCustomerByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.customer.grpc.GetCustomerByIdResponse>) responseObserver);
          break;
        case METHODID_LIST_CUSTOMERS:
          serviceImpl.listCustomers((com.customer.grpc.ListCustomersRequest) request,
              (io.grpc.stub.StreamObserver<com.customer.grpc.ListCustomersResponse>) responseObserver);
          break;
        case METHODID_CREATE_CUSTOMER:
          serviceImpl.createCustomer((com.customer.grpc.CreateCustomerRequest) request,
              (io.grpc.stub.StreamObserver<com.customer.grpc.CreateCustomerResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPingMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.customer.grpc.PingRequest,
              com.customer.grpc.PingResponse>(
                service, METHODID_PING)))
        .addMethod(
          getGetCustomerByIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.customer.grpc.GetCustomerByIdRequest,
              com.customer.grpc.GetCustomerByIdResponse>(
                service, METHODID_GET_CUSTOMER_BY_ID)))
        .addMethod(
          getListCustomersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.customer.grpc.ListCustomersRequest,
              com.customer.grpc.ListCustomersResponse>(
                service, METHODID_LIST_CUSTOMERS)))
        .addMethod(
          getCreateCustomerMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.customer.grpc.CreateCustomerRequest,
              com.customer.grpc.CreateCustomerResponse>(
                service, METHODID_CREATE_CUSTOMER)))
        .build();
  }

  private static abstract class CustomerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CustomerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.customer.grpc.CustomerOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CustomerService");
    }
  }

  private static final class CustomerServiceFileDescriptorSupplier
      extends CustomerServiceBaseDescriptorSupplier {
    CustomerServiceFileDescriptorSupplier() {}
  }

  private static final class CustomerServiceMethodDescriptorSupplier
      extends CustomerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CustomerServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CustomerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CustomerServiceFileDescriptorSupplier())
              .addMethod(getPingMethod())
              .addMethod(getGetCustomerByIdMethod())
              .addMethod(getListCustomersMethod())
              .addMethod(getCreateCustomerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
