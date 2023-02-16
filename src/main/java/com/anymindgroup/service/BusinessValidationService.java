package com.anymindgroup.service;

import com.anymindgroup.dto.BasicRequest;
import com.anymindgroup.dto.PaymentRequest;
import com.anymindgroup.repository.entity.PaymentMethodEntity;
import org.springframework.stereotype.Service;

@Service
public class BusinessValidationService {

    private final PaymentMethodDataService paymentMethodDataService;

    public BusinessValidationService(PaymentMethodDataService paymentMethodDataService) {
        this.paymentMethodDataService = paymentMethodDataService;
    }

    /**
     * 1) priceModifier should be between priceModifierStart and priceModifierEnd
     * 2) paymentMethod should be one of the available payment methods
     * @param request
     * @return
     */
    public boolean validateRequest(PaymentRequest request) {
        PaymentMethodEntity paymentMethodEntity = paymentMethodDataService.getByPaymentMethodName(request.getPaymentMethod());
        return request.getPriceModifier().compareTo(paymentMethodEntity.getPriceModifierStart()) > 0 &&
                request.getPriceModifier().compareTo(paymentMethodEntity.getPriceModifierEnd()) < 0;
    }
}
