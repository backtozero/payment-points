package com.anymindgroup.repository.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "sales_history")
@NoArgsConstructor
@Data
public class SalesHistoryEntity {
    @Id
    @Column(name = "event_timestamp")
    private LocalDateTime eventTimestamp;
    @Column(name = "request_timestamp")
    private LocalDateTime requestTimestamp;
    @Column(name = "customer_id")
    private String customerId;
    @Column(name = "payment_method_name")
    private String paymentMethodName;
    @Column(name = "final_price")
    private BigDecimal finalPrice;
    @Column(name = "points_earned")
    private BigDecimal pointsEarned;
    @Column(name = "additional_item", columnDefinition = "TEXT")
    private String additionalItemJson;
    @Transient
    private Map<String, String> additionalItemMap;

    public SalesHistoryEntity(LocalDateTime eventTimestamp, LocalDateTime requestTimestamp, String customerId, String paymentMethodName, BigDecimal finalPrice, BigDecimal pointsEarned, String additionalItemJson) {
        this.eventTimestamp = eventTimestamp;
        this.requestTimestamp = requestTimestamp;
        this.customerId = customerId;
        this.paymentMethodName = paymentMethodName;
        this.finalPrice = finalPrice;
        this.pointsEarned = pointsEarned;
        this.additionalItemJson = additionalItemJson;
    }

    @PostLoad
    private void loadMapFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        additionalItemMap = objectMapper.readValue(additionalItemJson, new TypeReference<Map<String, String>>(){});
    }

    @PrePersist
    private void saveMapAsJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        additionalItemJson = objectMapper.writeValueAsString(additionalItemMap);
    }
}
