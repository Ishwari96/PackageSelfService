package com.abnamro.packageservice.service;

import com.abnamro.packageservice.client.PackageShippingFeignClient;
import com.abnamro.packageservice.model.*;
        import feign.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import java.util.*;

        import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
        import static org.mockito.MockitoAnnotations.openMocks;

class PackageSelfServiceImplTests {

    @Mock
    private PackageShippingFeignClient feignClient;

    @InjectMocks
    private PackageSelfServiceImpl packageShippingServiceImpl;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void testForInvocationOfFindOrderByIdEndpoint() {
        long orderId = 123;
        ShippingOrderDetail orderDetail = new ShippingOrderDetail();
        orderDetail.setPackageId(12345);
        Optional<ShippingOrderDetail> optional = Optional.of(orderDetail);
        when(feignClient.findOrderById(12345)).thenReturn(optional);
        Optional<ShippingOrderDetail> result = packageShippingServiceImpl.findOrderById(orderId);
        assertNotNull(result);
        assert (optional.get().getPackageId() == 12345);
        verify(feignClient, times(1)).findOrderById(anyLong());
    }


    @Test
    void testForSuccessfulInvocationOfListOrdersEndpoint() {
        ShippingOrderDetailResponse response = new ShippingOrderDetailResponse();
        OrderCriteria oc = new OrderCriteria("IN_PROGRESS", 1, 10);
        when(feignClient.listOrders(anyString(), anyInt(), anyInt())).thenReturn(response);
        ShippingOrderDetailResponse result = packageShippingServiceImpl.listOrders(oc);
        assertNotNull(result);
        verify(feignClient, times(1)).listOrders(anyString(), anyInt(), anyInt());
    }


    @Test
    void testCreateOrderForSuccessScenario() {
        ShippingOrder order = new ShippingOrder();
        Response response = mock(Response.class);
        when(response.status()).thenReturn(201);
        Response.Body body = mock(Response.Body.class);
        when(response.body()).thenReturn(body);
        Map<String, Collection<String>> headers =  new HashMap<>();
        List<String> urls = new ArrayList<>();
        urls.add("sampleURL");
        headers.put("Location",urls);
        when(response.headers()).thenReturn(headers);
        when(feignClient.createShippingOrder(order)).thenReturn(response);
        packageShippingServiceImpl.createShippingOrder(order);
        verify(feignClient, times(1)).createShippingOrder(any(ShippingOrder.class));
    }

}