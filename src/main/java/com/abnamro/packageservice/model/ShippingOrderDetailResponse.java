package com.abnamro.packageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingOrderDetailResponse {
    List<ShippingOrderDetail> orderDetails;
}
