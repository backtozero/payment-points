package com.anymindgroup.service;

import com.anymindgroup.dto.PaymentRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Service
public class PaymentMethodCalcService {
    private final PaymentMethodDataService paymentMethodDataService;

    public PaymentMethodCalcService(PaymentMethodDataService paymentMethodDataService) {
        this.paymentMethodDataService = paymentMethodDataService;
    }

    /**
     * Calculates final price and earned points
     * Saves request to database
     *
     * @param request Payment request
     * @return Pair of final price and earned points
     */
    @Transactional
    public Pair<BigDecimal, Integer> calculateFinalPriceAndEarnedPoints(@Validated PaymentRequest request) {
        var paymentMethod = paymentMethodDataService.getByPaymentMethodName(request.getPaymentMethod());
        var priceModifier = request.getPriceModifier();

        var finalPrice = calculateFinalPrice(request.getPrice(), priceModifier);
        var earnedPoints = calculateEarnedPoints(request.getPrice(), paymentMethod.getPointsCoefficient());

        paymentMethodDataService.saveCustomerRequest(request, finalPrice, earnedPoints);
        return Pair.of(finalPrice, earnedPoints.intValue());
    }

    private BigDecimal calculateFinalPrice(BigDecimal originalPrice, BigDecimal priceModifier) {
        return originalPrice.multiply(priceModifier);
    }

    private BigDecimal calculateEarnedPoints(BigDecimal originalPrice, BigDecimal points) {
        return originalPrice.multiply(points);
    }
}
