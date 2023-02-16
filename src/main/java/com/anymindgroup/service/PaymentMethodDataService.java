package com.anymindgroup.service;

import com.anymindgroup.proto.PaymentRequest;
import com.anymindgroup.repository.PaymentMethodRepository;
import com.anymindgroup.repository.SalesHistoryRepository;
import com.anymindgroup.repository.entity.PaymentMethodEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PaymentMethodDataService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final SalesHistoryRepository salesHistoryRepository;

    public PaymentMethodDataService(PaymentMethodRepository paymentMethodRepository, SalesHistoryRepository salesHistoryRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.salesHistoryRepository = salesHistoryRepository;
    }

    public PaymentMethodEntity getByPaymentMethodName(String paymentMethodName) {
        return paymentMethodRepository.findByPaymentMethodName(paymentMethodName);
    }

    @Transactional(readOnly = false)
    public void saveCustomerRequest(PaymentRequest paymentRequest) {


    }
}
