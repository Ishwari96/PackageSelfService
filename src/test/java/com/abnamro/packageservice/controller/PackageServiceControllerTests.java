package com.abnamro.packageservice.controller;

import com.abnamro.packageservice.exception.PackageServiceException;
import com.abnamro.packageservice.model.*;
        import com.abnamro.packageservice.service.PackageSelfServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PackageServiceControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PackageSelfServiceImpl serviceImpl;

    private ShippingOrder shippingOrder;
    private ShippingOrderDetail shippingOrderDetail;


    @BeforeEach
    void setUp() {
        shippingOrder = new ShippingOrder();
        shippingOrder.setPackageName("Birthday Present");
        shippingOrder.setPostalCode("1082PP");
        shippingOrder.setStreetName("Gustav Mahlerlaan 10");
        shippingOrder.setReceiverName("Robert Swaak");
        shippingOrder.setPackageSize("M");

        shippingOrderDetail = new ShippingOrderDetail();
        shippingOrderDetail.setPackageId(1234567);
        shippingOrderDetail.setPackageName("Birthday Present");
        shippingOrderDetail.setPostalCode("1082PP");
        shippingOrderDetail.setStreetName("Gustav Mahlerlaan 10");
        shippingOrderDetail.setReceiverName("Robert Swaak");
        shippingOrderDetail.setPackageSize("M");
        shippingOrderDetail.setExpectedDeliveryDate("");

        shippingOrderDetail.setPackageId(1);
    }

    @Test
    void testCreateShippingOrderForSuccess() throws Exception {

        ShippingOrderSuccessResponse mockResponse = new ShippingOrderSuccessResponse();
        mockResponse.setMessage("Created Successfully");
        mockResponse.setOrderPath("/samplePath/12345");

        when(serviceImpl.createShippingOrder(shippingOrder)).thenReturn(mockResponse);

        MvcResult result = mockMvc.perform(post("/shippingOrders").content(asJsonString(shippingOrder)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201)).andReturn();

        //retrieve the response body and compare with the expected response
        ShippingOrderSuccessResponse responseFromController =  new ObjectMapper().readValue(result.getResponse().getContentAsString(), ShippingOrderSuccessResponse.class);
        assertEquals(responseFromController.getMessage(), mockResponse.getMessage());
        assertEquals(responseFromController.getOrderPath(), mockResponse.getOrderPath());

    }

    @Test
    void testCreateShippingOrderForValidationError() throws Exception {
        when(serviceImpl.createShippingOrder(shippingOrder)).thenThrow(new PackageServiceException("BAD REQUEST", String.valueOf(400)));
        MvcResult result = mockMvc.perform(post("/shippingOrders").content(asJsonString(shippingOrder))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400)).andReturn();

    }


    @Test
    void testCreateShippingOrderForConflictError() throws Exception {
        when(serviceImpl.createShippingOrder(shippingOrder)).thenThrow(new PackageServiceException("Conflict", String.valueOf(409)));
        MvcResult result = mockMvc.perform(post("/shippingOrders").content(asJsonString(shippingOrder))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(409)).andReturn();
    }


    @Test
    void testGetShippingOrdersForSuccess() throws Exception {
        List<ShippingOrderDetail> orders = Collections.singletonList(shippingOrderDetail);
        when(serviceImpl.listOrders(new OrderCriteria("IN_PROGRESS", 1,10)))
                .thenReturn(new ShippingOrderDetailResponse(orders));

        mockMvc.perform(get("/shippingOrders")
                        .param("status", "IN_PROGRESS")
                        .param("offset", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk());
    }


    @Test
    void testGetShippingOrderDetailsForSuccess() throws Exception {

        Optional<ShippingOrderDetail> optional = Optional.of(shippingOrderDetail);

        when(serviceImpl.findOrderById(anyLong()))
                .thenReturn(optional);

        mockMvc.perform(get("/shippingOrders/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.packageName").value("Birthday Present"));
    }


    public String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

