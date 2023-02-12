package com.anymindgroup.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "payment_method_points")
public class PaymentMethodPoints {
    @Id
    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "price_modifier")
    private String priceModifier;

    @Column(name = "points")
    private BigDecimal points;

    @Column(name = "additional_item")
    private String additionalItem;

    public PaymentMethodPoints() {}

    public PaymentMethodPoints(String paymentMethod, String priceModifier, BigDecimal points, String additionalItem) {
        this.paymentMethod = paymentMethod;
        this.priceModifier = priceModifier;
        this.points = points;
        this.additionalItem = additionalItem;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(String priceModifier) {
        this.priceModifier = priceModifier;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public String getAdditionalItem() {
        return additionalItem;
    }

    public void setAdditionalItem(String additionalItem) {
        this.additionalItem = additionalItem;
    }
}
