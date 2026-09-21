package com.billing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bill", indexes = {
    @Index(name = "idx_bill_customer_id", columnList = "customer_id"),
    @Index(name = "idx_bill_paid", columnList = "paid"),
    @Index(name = "idx_bill_created_at", columnList = "created_at")
})
@Getter
@Setter
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private boolean paid = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public com.billing.grpc.Bill toProto() {
        return com.billing.grpc.Bill.newBuilder()
                .setId(id)
                .setCustomerId(customerId)
                .setAmount(amount.toPlainString())
                .setPaid(paid)
                .setCreatedAt(createdAt.toString())
                .build();
    }
}