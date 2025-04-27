package com.abnamro.packageservice.client;

import com.abnamro.packageservice.client.config.ShippingServiceFeignClientConfig;
import com.abnamro.packageservice.model.ShippingOrder;
import com.abnamro.packageservice.model.ShippingOrderDetail;
import com.abnamro.packageservice.model.ShippingOrderDetailResponse;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/** Interface feign client to call url **/
@FeignClient(name = "packageShipping", url = "${shippingServiceURL}",
              configuration = ShippingServiceFeignClientConfig.class)
public interface PackageShippingFeignClient {

    /**
     * create shipping order
     * @param order shipping order
     * @return order response (mocked)
     */
    @RequestMapping (
            method = RequestMethod.POST,
            value = "/shippingOrders/OrderPackage",
            consumes = "application/json",
            produces = "application/json"
    )
    Response createShippingOrder(@RequestBody ShippingOrder order);

    /**
     * list of orders
     * @param status status
     * @param offset offset
     * @param limit limit
     * @return shipping order response
     */
    @RequestMapping (
            method = RequestMethod.GET,
            value = "/shippingOrders/ListOrders",
            produces = "application/json"
    )
    ShippingOrderDetailResponse listOrders(@RequestParam String status, @RequestParam Integer offset, @RequestParam Integer limit);

    /**
     * Find order by order id
     * @param orderId the order id
     * @return shipping order detail
     */
    @RequestMapping (
            method = RequestMethod.GET,
            value = "/shippingOrders/{orderId}",
            produces = "application/json"
    )
    Optional<ShippingOrderDetail> findOrderById(@PathVariable long orderId);

}
