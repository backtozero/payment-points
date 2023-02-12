package com.anymindgroup.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class PaymentRequest {
    @Schema(description = "Id of the customer", example = "12345")
    private String customerId;

    @Schema(description = "Base price", example = "100.00")
    private BigDecimal price;

    @Schema(description = "Price modifier", example = "0.95")
    private BigDecimal priceModifier;

    @Schema(description = "Payment method", example = "MASTERCARD")
    private String paymentMethod;

    @Schema(description = "Datetime of payment", example = "2022-09-01T00:00:00Z")
    private ZonedDateTime datetime;

    @Schema(description = "Additional information for the payment method")
    private AdditionalItem additionalItem;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceModifier() {
        return priceModifier;
    }

    public void setPriceModifier(BigDecimal priceModifier) {
        this.priceModifier = priceModifier;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ZonedDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(ZonedDateTime datetime) {
        this.datetime = datetime;
    }

    public AdditionalItem getAdditionalItem() {
        return additionalItem;
    }

    public void setAdditionalItem(AdditionalItem additionalItem) {
        this.additionalItem = additionalItem;
    }

    public static class AdditionalItem {
        @Schema(description = "Last 4 digits of the card", example = "1234")
        private String last4;

        public String getLast4() {
            return last4;
        }

        public void setLast4(String last4) {
            this.last4 = last4;
        }
    }
}
