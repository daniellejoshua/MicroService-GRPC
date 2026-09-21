package com.billing.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class BillServiceGrpc {

  private BillServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "billing.v1.BillService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.billing.grpc.CreateBillRequest,
      com.billing.grpc.CreateBillResponse> getCreateBillMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateBill",
      requestType = com.billing.grpc.CreateBillRequest.class,
      responseType = com.billing.grpc.CreateBillResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.billing.grpc.CreateBillRequest,
      com.billing.grpc.CreateBillResponse> getCreateBillMethod() {
    io.grpc.MethodDescriptor<com.billing.grpc.CreateBillRequest, com.billing.grpc.CreateBillResponse> getCreateBillMethod;
    if ((getCreateBillMethod = BillServiceGrpc.getCreateBillMethod) == null) {
      synchronized (BillServiceGrpc.class) {
        if ((getCreateBillMethod = BillServiceGrpc.getCreateBillMethod) == null) {
          BillServiceGrpc.getCreateBillMethod = getCreateBillMethod =
              io.grpc.MethodDescriptor.<com.billing.grpc.CreateBillRequest, com.billing.grpc.CreateBillResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateBill"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.billing.grpc.CreateBillRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.billing.grpc.CreateBillResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BillServiceMethodDescriptorSupplier("CreateBill"))
              .build();
        }
      }
    }
    return getCreateBillMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.billing.grpc.GetBillRequest,
      com.billing.grpc.GetBillResponse> getGetBillMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBill",
      requestType = com.billing.grpc.GetBillRequest.class,
      responseType = com.billing.grpc.GetBillResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.billing.grpc.GetBillRequest,
      com.billing.grpc.GetBillResponse> getGetBillMethod() {
    io.grpc.MethodDescriptor<com.billing.grpc.GetBillRequest, com.billing.grpc.GetBillResponse> getGetBillMethod;
    if ((getGetBillMethod = BillServiceGrpc.getGetBillMethod) == null) {
      synchronized (BillServiceGrpc.class) {
        if ((getGetBillMethod = BillServiceGrpc.getGetBillMethod) == null) {
          BillServiceGrpc.getGetBillMethod = getGetBillMethod =
              io.grpc.MethodDescriptor.<com.billing.grpc.GetBillRequest, com.billing.grpc.GetBillResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBill"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.billing.grpc.GetBillRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.billing.grpc.GetBillResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BillServiceMethodDescriptorSupplier("GetBill"))
              .build();
        }
      }
    }
    return getGetBillMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.billing.grpc.ListBillsRequest,
      com.billing.grpc.ListBillsResponse> getListBillsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListBills",
      requestType = com.billing.grpc.ListBillsRequest.class,
      responseType = com.billing.grpc.ListBillsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.billing.grpc.ListBillsRequest,
      com.billing.grpc.ListBillsResponse> getListBillsMethod() {
    io.grpc.MethodDescriptor<com.billing.grpc.ListBillsRequest, com.billing.grpc.ListBillsResponse> getListBillsMethod;
    if ((getListBillsMethod = BillServiceGrpc.getListBillsMethod) == null) {
      synchronized (BillServiceGrpc.class) {
        if ((getListBillsMethod = BillServiceGrpc.getListBillsMethod) == null) {
          BillServiceGrpc.getListBillsMethod = getListBillsMethod =
              io.grpc.MethodDescriptor.<com.billing.grpc.ListBillsRequest, com.billing.grpc.ListBillsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListBills"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.billing.grpc.ListBillsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.billing.grpc.ListBillsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BillServiceMethodDescriptorSupplier("ListBills"))
              .build();
        }
      }
    }
    return getListBillsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.billing.grpc.PayBillRequest,
      com.billing.grpc.PayBillResponse> getPayBillMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PayBill",
      requestType = com.billing.grpc.PayBillRequest.class,
      responseType = com.billing.grpc.PayBillResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.billing.grpc.PayBillRequest,
      com.billing.grpc.PayBillResponse> getPayBillMethod() {
    io.grpc.MethodDescriptor<com.billing.grpc.PayBillRequest, com.billing.grpc.PayBillResponse> getPayBillMethod;
    if ((getPayBillMethod = BillServiceGrpc.getPayBillMethod) == null) {
      synchronized (BillServiceGrpc.class) {
        if ((getPayBillMethod = BillServiceGrpc.getPayBillMethod) == null) {
          BillServiceGrpc.getPayBillMethod = getPayBillMethod =
              io.grpc.MethodDescriptor.<com.billing.grpc.PayBillRequest, com.billing.grpc.PayBillResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PayBill"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.billing.grpc.PayBillRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.billing.grpc.PayBillResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BillServiceMethodDescriptorSupplier("PayBill"))
              .build();
        }
      }
    }
    return getPayBillMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BillServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BillServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BillServiceStub>() {
        @java.lang.Override
        public BillServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BillServiceStub(channel, callOptions);
        }
      };
    return BillServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static BillServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BillServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BillServiceBlockingV2Stub>() {
        @java.lang.Override
        public BillServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BillServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return BillServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BillServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BillServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BillServiceBlockingStub>() {
        @java.lang.Override
        public BillServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BillServiceBlockingStub(channel, callOptions);
        }
      };
    return BillServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BillServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BillServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BillServiceFutureStub>() {
        @java.lang.Override
        public BillServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BillServiceFutureStub(channel, callOptions);
        }
      };
    return BillServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createBill(com.billing.grpc.CreateBillRequest request,
        io.grpc.stub.StreamObserver<com.billing.grpc.CreateBillResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBillMethod(), responseObserver);
    }

    /**
     */
    default void getBill(com.billing.grpc.GetBillRequest request,
        io.grpc.stub.StreamObserver<com.billing.grpc.GetBillResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBillMethod(), responseObserver);
    }

    /**
     */
    default void listBills(com.billing.grpc.ListBillsRequest request,
        io.grpc.stub.StreamObserver<com.billing.grpc.ListBillsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListBillsMethod(), responseObserver);
    }

    /**
     */
    default void payBill(com.billing.grpc.PayBillRequest request,
        io.grpc.stub.StreamObserver<com.billing.grpc.PayBillResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPayBillMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BillService.
   */
  public static abstract class BillServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BillServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BillService.
   */
  public static final class BillServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BillServiceStub> {
    private BillServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BillServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BillServiceStub(channel, callOptions);
    }

    /**
     */
    public void createBill(com.billing.grpc.CreateBillRequest request,
        io.grpc.stub.StreamObserver<com.billing.grpc.CreateBillResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateBillMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBill(com.billing.grpc.GetBillRequest request,
        io.grpc.stub.StreamObserver<com.billing.grpc.GetBillResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBillMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listBills(com.billing.grpc.ListBillsRequest request,
        io.grpc.stub.StreamObserver<com.billing.grpc.ListBillsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListBillsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void payBill(com.billing.grpc.PayBillRequest request,
        io.grpc.stub.StreamObserver<com.billing.grpc.PayBillResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPayBillMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BillService.
   */
  public static final class BillServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<BillServiceBlockingV2Stub> {
    private BillServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BillServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BillServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public com.billing.grpc.CreateBillResponse createBill(com.billing.grpc.CreateBillRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateBillMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.billing.grpc.GetBillResponse getBill(com.billing.grpc.GetBillRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetBillMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.billing.grpc.ListBillsResponse listBills(com.billing.grpc.ListBillsRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getListBillsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.billing.grpc.PayBillResponse payBill(com.billing.grpc.PayBillRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getPayBillMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service BillService.
   */
  public static final class BillServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BillServiceBlockingStub> {
    private BillServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BillServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BillServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.billing.grpc.CreateBillResponse createBill(com.billing.grpc.CreateBillRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateBillMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.billing.grpc.GetBillResponse getBill(com.billing.grpc.GetBillRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBillMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.billing.grpc.ListBillsResponse listBills(com.billing.grpc.ListBillsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListBillsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.billing.grpc.PayBillResponse payBill(com.billing.grpc.PayBillRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPayBillMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BillService.
   */
  public static final class BillServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BillServiceFutureStub> {
    private BillServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BillServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BillServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.billing.grpc.CreateBillResponse> createBill(
        com.billing.grpc.CreateBillRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateBillMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.billing.grpc.GetBillResponse> getBill(
        com.billing.grpc.GetBillRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBillMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.billing.grpc.ListBillsResponse> listBills(
        com.billing.grpc.ListBillsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListBillsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.billing.grpc.PayBillResponse> payBill(
        com.billing.grpc.PayBillRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPayBillMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_BILL = 0;
  private static final int METHODID_GET_BILL = 1;
  private static final int METHODID_LIST_BILLS = 2;
  private static final int METHODID_PAY_BILL = 3;

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
        case METHODID_CREATE_BILL:
          serviceImpl.createBill((com.billing.grpc.CreateBillRequest) request,
              (io.grpc.stub.StreamObserver<com.billing.grpc.CreateBillResponse>) responseObserver);
          break;
        case METHODID_GET_BILL:
          serviceImpl.getBill((com.billing.grpc.GetBillRequest) request,
              (io.grpc.stub.StreamObserver<com.billing.grpc.GetBillResponse>) responseObserver);
          break;
        case METHODID_LIST_BILLS:
          serviceImpl.listBills((com.billing.grpc.ListBillsRequest) request,
              (io.grpc.stub.StreamObserver<com.billing.grpc.ListBillsResponse>) responseObserver);
          break;
        case METHODID_PAY_BILL:
          serviceImpl.payBill((com.billing.grpc.PayBillRequest) request,
              (io.grpc.stub.StreamObserver<com.billing.grpc.PayBillResponse>) responseObserver);
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
          getCreateBillMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.billing.grpc.CreateBillRequest,
              com.billing.grpc.CreateBillResponse>(
                service, METHODID_CREATE_BILL)))
        .addMethod(
          getGetBillMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.billing.grpc.GetBillRequest,
              com.billing.grpc.GetBillResponse>(
                service, METHODID_GET_BILL)))
        .addMethod(
          getListBillsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.billing.grpc.ListBillsRequest,
              com.billing.grpc.ListBillsResponse>(
                service, METHODID_LIST_BILLS)))
        .addMethod(
          getPayBillMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.billing.grpc.PayBillRequest,
              com.billing.grpc.PayBillResponse>(
                service, METHODID_PAY_BILL)))
        .build();
  }

  private static abstract class BillServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BillServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.billing.grpc.BillOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BillService");
    }
  }

  private static final class BillServiceFileDescriptorSupplier
      extends BillServiceBaseDescriptorSupplier {
    BillServiceFileDescriptorSupplier() {}
  }

  private static final class BillServiceMethodDescriptorSupplier
      extends BillServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BillServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (BillServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BillServiceFileDescriptorSupplier())
              .addMethod(getCreateBillMethod())
              .addMethod(getGetBillMethod())
              .addMethod(getListBillsMethod())
              .addMethod(getPayBillMethod())
              .build();
        }
      }
    }
    return result;
  }
}
