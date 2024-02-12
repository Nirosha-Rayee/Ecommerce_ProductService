package com.example.ecommerce_productservice.services;

import com.example.ecommerce_productservice.clients.fakestore.client.FakeStoreClient;
import com.example.ecommerce_productservice.clients.fakestore.dto.FakeStoreProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FakeStoreProductServiceTest {
    @Autowired
    private FakeStoreClient fakeStoreClient; // arrange
    @Test
    public void Test_FakeStoreClient() {

        List<FakeStoreProductDto> result =  fakeStoreClient.getAllProducts(); // act, returning list of products

        assertNotNull(result); // assert
    }



}