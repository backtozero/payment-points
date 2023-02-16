package com.anymindgroup.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorInfo {
    final String errorCode;
    //Error message
    final private String message;
    // Additional metadata associated with the Error
    private Map<String,String> metadata;
}
