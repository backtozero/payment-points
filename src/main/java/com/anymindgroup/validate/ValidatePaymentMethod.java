package com.anymindgroup.validate;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PaymentValidator.class)
public @interface ValidatePaymentMethod {
    String message() default "Payment data is not valid";
    Class[] groups() default {};
    Class[] payload() default {};
}
