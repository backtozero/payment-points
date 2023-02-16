package com.anymindgroup.validate;


import com.anymindgroup.dto.PaymentRequest;
import com.anymindgroup.service.PaymentMethodDataService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Service;

/**
 * Validates PaymentRequest using data from database
 */

@Service
public class PaymentValidator implements ConstraintValidator<ValidatePaymentMethod, PaymentRequest> {

    private final PaymentMethodDataService paymentMethodDataService;

    public PaymentValidator(PaymentMethodDataService paymentMethodDataService) {
        this.paymentMethodDataService = paymentMethodDataService;
    }

    /**
     * Validates:
     * PaymentRequest.paymentMethod should be one of the available payment methods
     * PaymentRequest.priceModifier should be between PaymentMethodEntity.priceModifierStart and PaymentMethodEntity.priceModifierEnd
     * PaymentRequest.additionalItem.courier should be one of PaymentMethodEntity.couriersAllowed if the latter contains any values
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return true if the object is valid, false otherwise
     */
    @Override
    public boolean isValid(PaymentRequest value, ConstraintValidatorContext context) {
        var paymentMethod = paymentMethodDataService.getByPaymentMethodName(value.getPaymentMethod());
        if (paymentMethod == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Payment method is not valid")
                    .addPropertyNode("paymentMethod")
                    .addConstraintViolation();
            return false;
        }

        if (value.getPriceModifier().compareTo(paymentMethod.getPriceModifierStart()) < 0
            || value.getPriceModifier().compareTo(paymentMethod.getPriceModifierEnd()) > 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Price modifier is not valid")
                    .addPropertyNode("priceModifier")
                    .addConstraintViolation();
            return false;
        }

        if (paymentMethod.getCouriersAllowed() != null && !paymentMethod.getCouriersAllowed().isEmpty()) {
            var courier = value.getAdditionalItem().get("courier");
            if (courier == null || !paymentMethod.getCouriersAllowed().contains(courier)) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Courier is not valid")
                        .addPropertyNode("additionalItem")
                        .addConstraintViolation();
                return false;
            }
        }

        return true;
    }
}
