package com.anymindgroup.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

public class PaymentResponse {
    @Schema(description = "Final price", example = "95.00")
    private BigDecimal finalPrice;

    @Schema(description = "Earned points", example = "5")
    private int points;

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
