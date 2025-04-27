package com.abnamro.packageservice.controller;


import com.abnamro.packageservice.exception.PackageServiceException;
import com.abnamro.packageservice.model.*;
import com.abnamro.packageservice.service.PackageSelfService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/** controller for package shipping order service **/
@Tag(name = "PackageServiceController", description = "Create, update, package shipping")
@RestController
@RequestMapping("/shippingOrders")
public class PackageServiceController {

    @Autowired
    PackageSelfService packageSelfService;

    /**
     * Get all available employees for now
     * @return list of users
     */
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
    public ResponseEntity<ShippingOrderSuccessResponse> createOrder(@Valid @RequestBody ShippingOrder shippingOrder) throws PackageServiceException{

        return ResponseEntity.status(HttpStatus.CREATED).body(packageSelfService.createShippingOrder(shippingOrder));

    }

    /**
     * Retrieves a list of all shipping-order-details
     * @param status status
     * @param offset offset
     * @param limit limit
     * @return List of shipping-order-details
     */
    @GetMapping
    public ResponseEntity<ShippingOrderDetailResponse> getAllOrders(@RequestParam(required = false) String status,
                                                                    @RequestParam(required = false) Integer offset,
                                                                    @RequestParam(required = false) Integer limit) {

        return ResponseEntity.ok(packageSelfService.listOrders(new OrderCriteria(status, offset, limit)));
    }

    /**
     * Retrieves the details for an order
     * @param orderId
     * @return Order Details
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<ShippingOrderDetail> getOrderDetails(@Valid @PathVariable long orderId) {
        Optional<ShippingOrderDetail> orderDetails = packageSelfService.findOrderById(orderId);
        if (orderDetails.isEmpty()) {
            throw new PackageServiceException("Package is not found for orderId=" + orderId, HttpStatus.NOT_FOUND.toString());
        }
        return ResponseEntity.ok(orderDetails.get());
    }

}
