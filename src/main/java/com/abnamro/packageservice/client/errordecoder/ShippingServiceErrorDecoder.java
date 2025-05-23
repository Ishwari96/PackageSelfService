package com.abnamro.packageservice.client.errordecoder;

import com.abnamro.packageservice.exception.PackageServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/** Shipping service error decoder class **/
public class ShippingServiceErrorDecoder implements ErrorDecoder {

    /**
     * Package service exception decoder which will take exception from service and send the response
     * @param methodKey with applicable key
     * @param response from the client
     * @return exception
     */
    @Override
    public PackageServiceException decode(String methodKey, Response response) {
        PackageServiceException exception = new PackageServiceException(response.reason(), String.valueOf(response.status()));
        switch (response.status()) {
            case 400:
            case 401:
            case 404:
            case 409:
            case 500 :  {
                String responseDetails;
                try{
                    if(response.body() != null){
                        //then take the message, status and errors from the body.
                        responseDetails = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
                        ObjectMapper mapper = new ObjectMapper();
                        exception = mapper.readValue(responseDetails, PackageServiceException.class);
                    }
                } catch(IOException ioException){
                    //log
                }
            }
        }
        return exception;
    }
}

