package com.abnamro.packageservice.service;


import com.abnamro.packageservice.exception.PackageServiceException;
import com.abnamro.packageservice.model.*;

import java.util.List;
import java.util.Optional;

/**
 *  Package service
 */
public interface PackageSelfService {

    /**
     *  Get all users or employees
     * @return list of available employee
     */
    List<Users> getAllUsers();

    /**
     * Creates the order and returns the order created.
     *
     * @param order ShippingOrder the shipping order
     * @return the ShippingOrderSuccessResponse
     */
    ShippingOrderSuccessResponse createShippingOrder(ShippingOrder order)  throws PackageServiceException;

    /**
     * return all orders based on order criteria
     * @param orderCriteria OrderCriteria
     * @return ShippingOrderDetailResponse
     */
    ShippingOrderDetailResponse listOrders(OrderCriteria orderCriteria);

    /**
     * Find by id.
     *
     * @param orderId the order id
     * @return the optional
     */
    Optional<ShippingOrderDetail> findOrderById(long orderId);

}
