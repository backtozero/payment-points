package com.anymindgroup.controller;

import com.anymindgroup.dto.ErrorInfo;
import com.anymindgroup.dto.PaymentRequest;
import com.anymindgroup.dto.PaymentResponse;
import com.anymindgroup.dto.BasicRestError;
import com.anymindgroup.service.PaymentMethodCalcService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/${api.version}/payment")
public class PaymentController {

    private final PaymentMethodCalcService paymentMethodCalcService;

    public PaymentController(PaymentMethodCalcService paymentMethodCalcService) {
        this.paymentMethodCalcService = paymentMethodCalcService;
    }

    @PostMapping
    @Operation(summary = "Calculate final price and save points for a payment",
            description = "Calculates the final price and points based on the payment request and returns the result.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully calculated the final price and points",
                            content = @Content(schema = @Schema(implementation = PaymentResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid request")
            })
    @Validated
    public ResponseEntity<PaymentResponse> calculatePayment(@Valid @RequestBody PaymentRequest request) {
        Pair<BigDecimal, Integer> priceAndEarnedPoints = paymentMethodCalcService.calculateFinalPriceAndEarnedPoints(request);

        return ResponseEntity.ok(new PaymentResponse(priceAndEarnedPoints));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<BasicRestError> handleValidationException(Exception ex) {
        BasicRestError errorInfo = new BasicRestError(new ErrorInfo("COMMON_CODE", ex.getMessage()));
        return ResponseEntity.badRequest().body(errorInfo);
    }

}