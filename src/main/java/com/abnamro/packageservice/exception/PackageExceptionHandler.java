package com.abnamro.packageservice.exception;

import com.abnamro.packageservice.model.ShippingOrderErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** Globle package exception handler **/
@ControllerAdvice
public class PackageExceptionHandler {

    /**
     * Package Service Exception
     * @param packageServiceException exe
     * @return response
     */
    @ExceptionHandler(PackageServiceException.class)
    public ResponseEntity<String> handlePackageServiceException(PackageServiceException packageServiceException) {

        return new ResponseEntity<>(getBodyAsString (packageServiceException),  HttpStatusCode.valueOf(Integer.parseInt(packageServiceException.getStatus())));
    }

    /**
     * generic or globle exception
     * @param exception exe
     * @return response
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getBodyAsString(PackageServiceException e) {
        try {
            ShippingOrderErrorResponse response = new ShippingOrderErrorResponse(e.getMessage(), e.getStatus(), e.getErrors());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(response);
        } catch (Exception ex) {
             return "Unable to parse the exception message from the service";
        }
    }
}