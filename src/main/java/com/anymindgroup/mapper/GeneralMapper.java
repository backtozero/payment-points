package com.anymindgroup.mapper;

import com.anymindgroup.proto.PaymentRequest;
import com.anymindgroup.proto.PaymentResponse;
import com.google.protobuf.Timestamp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@Service
public interface GeneralMapper {
    com.anymindgroup.dto.PaymentRequest map(PaymentRequest paymentRequest);

    PaymentRequest map(com.anymindgroup.dto.PaymentRequest paymentRequest);

    com.anymindgroup.dto.PaymentResponse map(PaymentResponse paymentRequest);

    PaymentResponse map(com.anymindgroup.dto.PaymentResponse paymentRequest);

    default LocalDateTime map(Timestamp value) {
        return LocalDateTime.ofEpochSecond(value.getSeconds(), value.getNanos(), ZoneOffset.UTC);
    }

    default Timestamp map(LocalDateTime value) {
        Instant instant = value.toInstant(ZoneOffset.UTC);
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
