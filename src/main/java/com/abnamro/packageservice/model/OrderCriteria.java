package com.abnamro.packageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCriteria {
    private String status;
    private Integer offset;
    private Integer limit;
}
