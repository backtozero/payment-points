package com.anymindgroup.dto;

import com.anymindgroup.dto.ErrorInfo;
import lombok.Data;

@Data
public class BasicRestError {
    private final ErrorInfo error;
}
