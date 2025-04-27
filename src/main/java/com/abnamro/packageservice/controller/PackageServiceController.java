package com.abnamro.packageservice.controller;


import com.abnamro.packageservice.model.ShippingOrder;
import com.abnamro.packageservice.model.ShippingOrderSuccessResponse;
import com.abnamro.packageservice.model.Users;
import com.abnamro.packageservice.service.PackageSelfService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PackageServiceController", description = "Create, update, package shipping")
@RestController
@RequestMapping("/shippingOrders")
public class PackageServiceController {

    @Autowired
    PackageSelfService packageSelfService;

    @GetMapping("/employees")
    public List<Users> getAllUsers() {
            return packageSelfService.getAllUsers();
        }


    /**
     * This method creates order with given request body
     *
     * @param shippingOrder the shipping request
     * @return the shipping order details
     */
    @PostMapping
    public ResponseEntity<ShippingOrderSuccessResponse> createOrder(@Valid @RequestBody ShippingOrder shippingOrder) {

        return ResponseEntity.status(HttpStatus.CREATED).body(packageSelfService.createShippingOrder(shippingOrder));

    }
}
