package com.abnamro.packageservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ShippingOrder {
    @NotBlank(message = "Package name is mandatory")
    private String packageName;
    @Pattern(regexp = "^[0-9]{4}[A-Z]{2}$", message = "Postal code must be in the format 1234AB")
    private String postalCode;
    @NotBlank(message = "Street name is mandatory")
    private String streetName;
    @NotBlank(message = "Receiver name is mandatory")
    private String receiverName;
    @Pattern(regexp = "S|M|L|XL", message = "Package size must be one of S, M, L, XL")
    private String packageSize;
}
