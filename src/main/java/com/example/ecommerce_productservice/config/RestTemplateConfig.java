package com.example.ecommerce_productservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }  // after adding this config class, we can autowire RestTemplate in any class and use it to make REST calls
    //now go to SelfProductService.java and replace RestTemplate restTemplate = new RestTemplate(); with @Autowired RestTemplate restTemplate;
    //same like we did in FakeStoreProductService.java
}
