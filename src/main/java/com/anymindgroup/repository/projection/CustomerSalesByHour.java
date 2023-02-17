package com.anymindgroup.repository.projection;

import java.math.BigDecimal;

public interface CustomerSalesByHour {
    Integer getHour();
    BigDecimal totalSales();
}
