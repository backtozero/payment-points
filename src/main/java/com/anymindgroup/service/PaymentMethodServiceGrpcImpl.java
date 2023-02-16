package com.anymindgroup.service;

import com.anymindgroup.mapper.GeneralMapper;
import com.anymindgroup.proto.ErrorInfo;
import com.anymindgroup.proto.PaymentRequest;
import com.anymindgroup.proto.PaymentResponse;
import com.google.protobuf.Any;
import com.google.rpc.Code;
import io.grpc.Status;
import io.grpc.protobuf.StatusProto;
import io.grpc.stub.StreamObserver;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentMethodServiceGrpcImpl extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final PaymentMethodCalcService paymentMethodCalcService;
    private final GeneralMapper generalMapper;

    public PaymentMethodServiceGrpcImpl(PaymentMethodCalcService paymentMethodCalcService, GeneralMapper generalMapper) {
        this.paymentMethodCalcService = paymentMethodCalcService;
        this.generalMapper = generalMapper;
    }

    @Override
    @Transactional
    public void calculatePayment(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        try {
            var finalPriceAndEarnedPoints =
                    paymentMethodCalcService.calculateFinalPriceAndEarnedPoints(generalMapper.map(request));

            var paymentResponse = PaymentResponse.newBuilder()
                    .setFinalPrice(finalPriceAndEarnedPoints.getFirst().doubleValue())
                    .setPoints(finalPriceAndEarnedPoints.getSecond())
                    .build();
            responseObserver.onNext(paymentResponse);
            responseObserver.onCompleted();
        } catch (ConstraintViolationException e) {
            var errorInfo = ErrorInfo.newBuilder().build();

            var status =
                    com.google.rpc.Status.newBuilder()
                            .setCode(Code.INVALID_ARGUMENT.getNumber())
                            .setMessage("Invalid data supplied")
                            .addDetails(Any.pack(errorInfo))
                            .build();

            responseObserver.onError(StatusProto.toStatusRuntimeException(status));
        } catch (Exception e) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid request")
                    .asRuntimeException());
        }
    }
}
