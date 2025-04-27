package com.abnamro.packageservice.client;

import com.abnamro.packageservice.client.config.ShippingServiceFeignClientConfig;
import com.abnamro.packageservice.model.ShippingOrder;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "packageShipping", url = "${shippingServiceURL}",
              configuration = ShippingServiceFeignClientConfig.class)
public interface PackageShippingFeignClient {

    @RequestMapping (
            method = RequestMethod.POST,
            value = "/shippingOrders/OrderPackage",
            consumes = "application/json",
            produces = "application/json"
    )
    Response createShippingOrder(@RequestBody ShippingOrder order);


}
