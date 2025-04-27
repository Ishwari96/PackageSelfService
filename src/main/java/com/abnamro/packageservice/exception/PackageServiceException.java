package com.abnamro.packageservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageServiceException extends RuntimeException {
    private String status;
    private List<PackageServiceError> errors;
    private String message;

    public PackageServiceException(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public PackageServiceException(String message, String status, List<PackageServiceError> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }
}