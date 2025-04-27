package com.abnamro.packageservice.service;


import com.abnamro.packageservice.model.ShippingOrder;
import com.abnamro.packageservice.model.Users;
import com.abnamro.packageservice.model.ShippingOrderSuccessResponse;

import java.util.List;

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
    public ShippingOrderSuccessResponse createShippingOrder(ShippingOrder order)  throws Exception;

}
