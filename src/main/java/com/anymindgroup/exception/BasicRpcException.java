package com.anymindgroup.exception;

import io.grpc.Status;

public class BasicRpcException extends RuntimeException {

    private final Status status;

    public BasicRpcException(Status status) {
        super(status.getCode().name(), status.getCause());
        this.status = status;
    }
}
