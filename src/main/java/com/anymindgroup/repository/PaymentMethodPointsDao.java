package com.anymindgroup.repository;

import java.util.List;

import com.anymindgroup.repository.entity.PaymentMethodPoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodPointsDao extends JpaRepository<PaymentMethodPoints, Integer> {
    List<PaymentMethodPoints> findByPaymentMethod(String paymentMethod);
}