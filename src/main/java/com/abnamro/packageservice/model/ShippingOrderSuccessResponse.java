package com.abnamro.packageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrderSuccessResponse {
    private String message;
    private String orderPath;
}
