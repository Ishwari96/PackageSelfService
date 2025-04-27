package com.abnamro.packageservice.client.config;

import com.abnamro.packageservice.client.errordecoder.ShippingServiceErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShippingServiceFeignClientConfig {

    @Bean
    public ErrorDecoder errorDecoder(){
        return new ShippingServiceErrorDecoder();
    }

}
