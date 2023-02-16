package com.anymindgroup.repository;

import com.anymindgroup.repository.entity.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Integer> {
    PaymentMethodEntity findByPaymentMethodName(String paymentMethodName);
}