package com.anymindgroup.repository;

import com.anymindgroup.repository.entity.SalesHistoryEntity;
import com.anymindgroup.repository.projection.CustomerSalesByHour;
import com.anymindgroup.repository.projection.CustomerSalesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesHistoryRepository extends JpaRepository<SalesHistoryEntity, LocalDateTime> {

    /**
     *  How much sales were made within a date range broken down into hours
     */
    @Query("""
            SELECT date_trunc('hour', she.eventTimestamp) as hour, SUM(she.price) as total_sales
            FROM SalesHistoryEntity she
            WHERE she.eventTimestamp BETWEEN :from AND :to
            GROUP BY date_trunc('hour', she.eventTimestamp)
            """)
    List<CustomerSalesByHour> findByEventTimestampBetween(LocalDateTime from, LocalDateTime to);


    /**
     * List of sales and the points given out to the customer
     */
    @Query("""
            SELECT she.customerId, she.pointsEarned, she.price, she.eventTimestamp
            FROM SalesHistoryEntity she
            WHERE she.customerId = :customerId
            """)
    List<CustomerSalesProjection> findAllByCustomerId(String customerId);
}