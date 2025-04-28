package com.abnamro.packageservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** package service custom exception**/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageServiceException extends RuntimeException {
    private String status;
    private List<PackageServiceError> errors;
    private String message;

    /**
     * constructor package service exception with message
     * @param message the message
     * @param status the status
     */
    public PackageServiceException(String message, String status) {
        this.message = message;
        this.status = status;
    }

    /**
     * constructor package service exception for params
     * @param message the message
     * @param status the status
     * @param errors the errors
     */
    public PackageServiceException(String message, String status, List<PackageServiceError> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }
}