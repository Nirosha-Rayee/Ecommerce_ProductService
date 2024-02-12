package com.example.ecommerce_productservice.clients.fakestore.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FakeStoreClientTest { // It is   Integration testing
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Test
    void testNonExistingProductReturnsNull() {

//        RestTemplate restTemplate = restTemplateBuilder.build();

//        ResponseEntity<FakeStoreProductDto> response =
//                restTemplate.getForEntity(specificProductRequestUrl, FakeStoreProductDto.class, id);
//
//        FakeStoreProductDto fakeStoreProductDto = response.getBody();
//
//        assertNull(fakeStoreProductDto);
    }


}