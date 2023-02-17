package com.anymindgroup.dto;

import com.anymindgroup.validate.ValidatePaymentMethod;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@ValidatePaymentMethod
public class PaymentRequest {
    @Schema(description = "Id of the customer", example = "12345")
    @NotBlank
    private String customerId;

    @Schema(description = "Base price", example = "100.00")
    @NotNull
    private BigDecimal price;

    @Schema(description = "Price modifier", example = "0.95")
    @NotNull
    private BigDecimal priceModifier;

    @Schema(description = "Payment method", example = "MASTERCARD")
    @NotBlank
    private String paymentMethod;

    @Schema(description = "Datetime of payment", example = "2022-09-01T00:00:00Z")
    @NotNull
    private LocalDateTime datetime;

    @Schema(description = "Additional information")
    private Map<String, String> additionalItem;
}

