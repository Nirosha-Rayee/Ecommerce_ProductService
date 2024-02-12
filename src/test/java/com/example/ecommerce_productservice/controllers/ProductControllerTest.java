package com.example.ecommerce_productservice.controllers;

import com.example.ecommerce_productservice.models.Product;
import com.example.ecommerce_productservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @MockBean
    IProductService productService;
    @Autowired
    ProductController productController;

    @Test
    void test_whenGetProductIsCalled_ReturnProducts() {
//        //we got a product, has set id and title
//        Product product = new Product();
//        product.setId(2L);
//        product.setTitle("test product");
//
//
//        // Arrange
//        when(productService.getSingleProduct(any(Long.class))).thenReturn(product); // when asserting values used this line
//        // Act
//        ResponseEntity<Product> productResponseEntity = productController.getSingleProduct(2L);
//        // Assert
//        assertNotNull(productResponseEntity); // put debug point here & run the test in debug mode
//
//        //how to assert what is the values are there in the product inside the response entity
//       assertEquals("test", productResponseEntity.getBody().getTitle());

        Product product = new Product();
        product.setId(2l);
        product.setTitle("test");
        when(productService.getSingleProduct(any(Long.class))).thenReturn(product);

        ResponseEntity<Product> productResponseEntity = productController.getSingleProduct(2l);

        assertNotNull(productResponseEntity);
        assertEquals("test", productResponseEntity.getBody().getTitle());

    }

    @Test
    void test_whenGetProductIsCalled_ReturnException() {



        // Arrange
        //when(productService.getSingleProduct(any(Long.class))).thenThrow(new RuntimeException("something is wrong")); // rule //1
        // Act
       // ResponseEntity<Product> productResponseEntity = productController.getSingleProduct(2L);//2
        // Assert
       // assertNotNull(productResponseEntity);//2
        //after we run the test, we can see the exception in the console like test case is failed

        //how to assert this function can throw exception,so we can use assertThrows
        //change  2 line and remove 3 line

        when(productService.getSingleProduct(any(Long.class))).thenThrow(new RuntimeException("something is wrong"));
        assertThrows(RuntimeException.class, () -> productController.getSingleProduct(2L));


    }
    // above 2 test cases are related to how to do Mockito testing for the controller


}