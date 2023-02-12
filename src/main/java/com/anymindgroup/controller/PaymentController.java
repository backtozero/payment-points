package com.anymindgroup.controller;

import com.anymindgroup.dto.PaymentRequest;
import com.anymindgroup.dto.PaymentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping
    @Operation(summary = "Calculate final price and points for a payment",
            description = "Calculates the final price and points based on the payment request and returns the result.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully calculated the final price and points",
                            content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request")
            })
    public ResponseEntity<PaymentResponse> calculatePayment(@Valid @RequestBody PaymentRequest request) {
        BigDecimal price = request.getPrice();
        BigDecimal priceModifier = request.getPriceModifier();
        BigDecimal finalPrice = price.multiply(priceModifier);
        BigDecimal points = price.multiply(BigDecimal.valueOf(0.05));

        PaymentResponse response = new PaymentResponse();
        response.setFinalPrice(finalPrice);
        response.setPoints(points.intValue());

        return ResponseEntity.ok(response);
    }
}