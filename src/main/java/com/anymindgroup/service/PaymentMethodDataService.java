package com.anymindgroup.service;

import com.anymindgroup.dto.PaymentRequest;
import com.anymindgroup.repository.PaymentMethodRepository;
import com.anymindgroup.repository.SalesHistoryRepository;
import com.anymindgroup.repository.entity.PaymentMethodEntity;
import com.anymindgroup.repository.entity.SalesHistoryEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class PaymentMethodDataService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final SalesHistoryRepository salesHistoryRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PaymentMethodDataService(PaymentMethodRepository paymentMethodRepository, SalesHistoryRepository salesHistoryRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.salesHistoryRepository = salesHistoryRepository;
    }

    public PaymentMethodEntity getByPaymentMethodName(String paymentMethodName) {
        return paymentMethodRepository.findByPaymentMethodName(paymentMethodName);
    }

    /**
     * Save the payment request to the database
     */
    @Transactional(readOnly = false)
    public void saveCustomerRequest(PaymentRequest paymentRequest, BigDecimal finalPrice, BigDecimal pointsEarned) {
        try {
            String additionalItemJson = objectMapper.writeValueAsString(paymentRequest.getAdditionalItem());
            var sale = new SalesHistoryEntity(
                    paymentRequest.getDatetime(),
                    LocalDateTime.now(),
                    paymentRequest.getCustomerId(),
                    paymentRequest.getPaymentMethod(),
                    finalPrice,
                    pointsEarned,
                    additionalItemJson);
            salesHistoryRepository.save(sale);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
