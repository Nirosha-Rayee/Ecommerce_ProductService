package com.example.ecommerce_productservice.controllers;

import com.example.ecommerce_productservice.clients.fakestore.client.FakeStoreClient;
import com.example.ecommerce_productservice.dtos.ProductDto;
import com.example.ecommerce_productservice.models.Product;
import com.example.ecommerce_productservice.services.FakeStoreProductService;
import com.example.ecommerce_productservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {
    @MockBean
//    IProductService productService;
    FakeStoreProductService productService;
    @Autowired
    ProductController productController;

    @Autowired
    @MockBean
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    @MockBean
    FakeStoreClient fakeStoreClient;


    @Captor
    private ArgumentCaptor<Long> idCptor;

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

        //ResponseEntity<Product> productResponseEntity = productController.getSingleProduct(2l);

        ResponseEntity<ProductDto> productResponseEntity = productController.getSingleProduct(2l); //change the return type to ProductDto from Product in redis class

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


    @Test
    void test_productControllerCallsProductServiceWithSameId() { //this is using ArgumentCaptor and verify
        Long id = 2L;
        // Arrange
        when(productService.getSingleProduct(id)).thenCallRealMethod();
        //when(restTemplateBuilder.build()).thenReturn(new RestTemplate());

        // Act
        productController.getSingleProduct(id);
        // capture the id, after capturing the id, we can assert the id
        verify(productService).getSingleProduct(idCptor.capture());//for this statement we have to change the (productId) to  (productId+1) in the getSingleProduct method in product controller
        verify(productService, times(1)).getSingleProduct(any()); // for this statement we have remove the (productId+1) in the getSingleProduct method in product controller

        // Assert
        assertEquals(id, idCptor.getValue());
    }
//after run error came, so, changed IproductService to fakeStoreProductService and FSPS put @Primary
    // commentout the @Service in SelfProductService
    //added @Autowired , @MockBean on RestTemplateBuilder and FakeStore, becoz, we are using it in FakeStoreProductService

}


//    @Test
//    void test_productControllerCallsProdcutServiceWithSameId2() {
//        Long id = 2l;
//        when(productService.getSingleProduct(id)).thenCallRealMethod();
//
//        productController.getSingleProduct(id);
//        assertEquals(id, idCaptor.getValue());
//
//
//        verify(productService).getSingleProduct(idCaptor.capture());
//        verify(productService, times(1)).getSingleProduct(any());
//
//    }