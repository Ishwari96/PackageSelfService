package com.abnamro.packageservice.client.config;

import com.abnamro.packageservice.client.errordecoder.ShippingServiceErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** feign client as we used in BE to FE applications **/
@Configuration
public class ShippingServiceFeignClientConfig {

    /**
     * error decoder bean
     * @return service error decoder
     */
    @Bean
    public ErrorDecoder errorDecoder(){
        return new ShippingServiceErrorDecoder();
    }

}
