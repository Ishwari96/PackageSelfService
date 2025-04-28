package com.abnamro.packageservice.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrderDetail {
    @Id
    private long packageId;
    private String packageName;
    private String packageSize;
    private String postalCode;
    private String streetName;
    private String receiverName;
    private String orderStatus;
    private String expectedDeliveryDate;
    private String actualDeliveryDateTime;
}
