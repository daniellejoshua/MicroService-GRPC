package com.billing.service;

import com.billing.client.CustomerClient;
import com.billing.entity.BillEntity;
import com.billing.grpc.*;
import com.billing.repository.BillRepository;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;
import java.util.Optional;

@GrpcService
public class GrpcBillService extends BillServiceGrpc.BillServiceImplBase {

    private final BillRepository billRepository;
    private final CustomerClient customerClient;


    public GrpcBillService(BillRepository billRepository,CustomerClient customerClient) {
        this.billRepository = billRepository;
        this.customerClient = customerClient;
    }

    @Override
    public void createBill(CreateBillRequest request, StreamObserver<CreateBillResponse> responseObserver) {
        try {
            customerClient.verifyCustomerExists(request.getCustomerId());
        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.NOT_FOUND) {
                responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription("Customer Not Found:" + request.getCustomerId())));
                return;
            }
            responseObserver.onError(e);
            return;
        }

        BillEntity bill = new BillEntity();
        bill.setCustomerId(request.getCustomerId());
        bill.setAmount(new java.math.BigDecimal(request.getAmount()));

        BillEntity saved = billRepository.save(bill);
        CreateBillResponse reply = CreateBillResponse.newBuilder()
                .setBill(saved.toProto())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }


    @Override
    public void listBills(ListBillsRequest request, StreamObserver<ListBillsResponse> responseObserver) {
        List<BillEntity> billEntityList = billRepository.findAll();
        List<Bill> proto = billEntityList.stream().map(BillEntity::toProto).toList();
        ListBillsResponse reply = ListBillsResponse.newBuilder().addAllBills(proto).build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getBill(GetBillRequest request, StreamObserver<GetBillResponse> responseObserver) {
        Long id = request.getId();
        Optional<BillEntity> opt = billRepository.findById(id);

        if (opt.isEmpty()) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription("Bill not Found with the id: " + id)));
            return;
        }
        BillEntity bill = opt.get();
        GetBillResponse reply = GetBillResponse.newBuilder().setBill(bill.toProto()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void payBill(PayBillRequest request, StreamObserver<PayBillResponse> responseObserver) {
        Long id = request.getId();
        Optional<BillEntity> opt = billRepository.findById(id);
        if (opt.isEmpty()) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND.withDescription("Bill not Found with the id: " + id)));
            return;
        }

        BillEntity bill = opt.get();
        bill.setPaid(true);
        BillEntity saved = billRepository.save(bill);
        PayBillResponse reply = PayBillResponse.newBuilder().setBill(saved.toProto()).build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();

    }
}