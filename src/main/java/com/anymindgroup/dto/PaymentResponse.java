package com.anymindgroup.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;

@Data
public class PaymentResponse {
    @Schema(description = "Final price", example = "95.00")
    private final BigDecimal finalPrice;

    @Schema(description = "Earned points", example = "5")
    private final int points;

    public PaymentResponse(Pair<BigDecimal, Integer> priceAndEarnedPoints) {
        this.finalPrice = priceAndEarnedPoints.getFirst();
        this.points = priceAndEarnedPoints.getSecond();
    }
}
