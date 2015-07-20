package com.soo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author KHS
 */
@Data
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;

    private String message;
}
