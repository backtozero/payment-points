package com.anymindgroup.mapper;

import com.anymindgroup.proto.PaymentRequest;
import com.anymindgroup.proto.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Service
public interface GeneralMapper {
    com.anymindgroup.dto.PaymentRequest map(PaymentRequest paymentRequest);

    PaymentRequest map(com.anymindgroup.dto.PaymentRequest paymentRequest);

    com.anymindgroup.dto.PaymentResponse map(PaymentResponse paymentRequest);

    PaymentResponse map(com.anymindgroup.dto.PaymentResponse paymentRequest);
}
