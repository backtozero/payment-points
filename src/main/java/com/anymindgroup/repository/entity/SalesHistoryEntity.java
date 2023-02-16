package com.anymindgroup.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sales_history")
@Data
public class SalesHistoryEntity {
    @Id
    @Column(name = "event_timestamp")
    private LocalDateTime eventTimestamp;
    @Column(name = "request_timestamp")
    private LocalDateTime requestTimestamp;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "points_earned")
    private BigDecimal pointsEarned;
    @Column(name = "additional_info")
    private byte[] additionalInfo;
}
