package com.abnamro.packageservice.controller;

import com.abnamro.packageservice.handler.PackageServiceHandler;
import com.abnamro.packageservice.model.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "PackageServiceController", description = "Create, update, package shipping")
@RestController
@RequestMapping("/shippingOrders")
public class PackageServiceController {

    @Autowired
    PackageServiceHandler packageServiceHandler;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        try {
            return ResponseEntity.ok(packageServiceHandler.getAllUsers());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
