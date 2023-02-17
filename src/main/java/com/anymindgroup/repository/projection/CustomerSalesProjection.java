package com.anymindgroup.repository.projection;

import java.math.BigDecimal;

public interface CustomerSalesProjection {
    String getCustomerId();
    BigDecimal pointsEarned();
    BigDecimal price();
    String eventTimestamp();
}
