package com.abnamro.packageservice.service;

import com.abnamro.packageservice.client.PackageShippingFeignClient;
import com.abnamro.packageservice.client.errordecoder.ShippingServiceErrorDecoder;
import com.abnamro.packageservice.exception.PackageServiceException;
import com.abnamro.packageservice.model.*;
import com.abnamro.packageservice.repository.PackageServiceRepository;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PackageSelfServiceImpl implements PackageSelfService{


    @Autowired
    private final PackageShippingFeignClient shippingClient;

    /*public PackageSelfServiceImpl(PackageShippingFeignClient shippingClient) {
       this.shippingClient = shippingClient;
   }*/

    PackageServiceRepository packageServiceRepository;

    private static final String ORDER_LOCATION_HEADER = "Location";
    /**
     * Get All
     * @return the all
     */
    public List<Users> getAllUsers() {
        return packageServiceRepository.findAll();
    }

    public ShippingOrderSuccessResponse createShippingOrder(ShippingOrder order) throws PackageServiceException {
        Response response = shippingClient.createShippingOrder(order);
        //manually call the error decoder here as we need to handle response manually; ShippingServiceErrorDecoder is not automatically invoked in this case.
        if(response.status() != 201){
           ShippingServiceErrorDecoder errorDecoder = new ShippingServiceErrorDecoder();
           throw errorDecoder.decode("createShippingOrder", response);
        }
        ShippingOrderSuccessResponse successResponse =   new ShippingOrderSuccessResponse();
        successResponse.setOrderPath(response.headers().get(ORDER_LOCATION_HEADER).iterator().next());
        successResponse.setMessage("Order created successfully");
        return successResponse;
    }

    public Optional<ShippingOrderDetail> findOrderById(long orderId) {
        return shippingClient.findOrderById(orderId);
    }

    public ShippingOrderDetailResponse listOrders(OrderCriteria orderCriteria) {
        return shippingClient.listOrders(orderCriteria.getStatus(), orderCriteria.getOffset(), orderCriteria.getLimit());
    }

}