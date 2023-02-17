package com.anymindgroup.repository.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "paymentMethod")
@Table(name = "payment_methods")
@Data
public class PaymentMethodEntity {
    @Id
    @Column(name = "payment_method_name")
    private String paymentMethodName;

    @Column(name = "price_modifier_start")
    private BigDecimal priceModifierStart;

    @Column(name = "price_modifier_end")
    private BigDecimal priceModifierEnd;

    @Column(name = "points_coefficient")
    private BigDecimal pointsCoefficient;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "payment_method_courier",
            joinColumns = @JoinColumn(name = "payment_method_name"),
            inverseJoinColumns = @JoinColumn(name = "courier_name"))
    private List<Courier> couriersAllowed;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "payment_method_additional_fields_to_save",
            joinColumns = @JoinColumn(name = "payment_method_name"),
            inverseJoinColumns = @JoinColumn(name = "field_name"))
    private List<Field> additionalFieldsToSave;

    @Column(name = "description")
    private String description;
}
