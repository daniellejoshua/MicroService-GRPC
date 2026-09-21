package com.billing.repository;

import com.billing.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BillRepository extends JpaRepository<BillEntity, Long> {
    List<BillEntity> findByCustomerId(Long customerId);
    List<BillEntity> findByPaid(boolean paid);
}