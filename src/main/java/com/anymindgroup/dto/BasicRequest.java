package com.anymindgroup.dto;

import java.math.BigDecimal;

public record BasicRequest(BigDecimal price, String paymentMethod, BigDecimal priceModifier ) {
}
