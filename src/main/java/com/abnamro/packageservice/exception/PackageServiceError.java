package com.abnamro.packageservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** package service error class **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageServiceError {
    private String field;
    private String message;
}
