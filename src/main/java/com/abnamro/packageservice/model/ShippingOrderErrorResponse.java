package com.abnamro.packageservice.model;

import com.abnamro.packageselfservice.exception.PackageServiceError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrderErrorResponse {
    private String message;
    private String status;
    private List<PackageServiceError> errors;
}
