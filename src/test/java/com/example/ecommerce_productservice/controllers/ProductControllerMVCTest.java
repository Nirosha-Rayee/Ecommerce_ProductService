package com.example.ecommerce_productservice.controllers;

import com.example.ecommerce_productservice.models.Product;
import com.example.ecommerce_productservice.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;



@WebMvcTest(ProductController.class) //we are calling directly APIs
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    IProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getALlProducts() throws Exception {
        //how to return the list of products, so we have to add a new products

        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        when(productService.getAllProducts()).thenReturn(products);


        //mockMvc.perform(get("/products")).andExpect(status().isOk()).andExpect(content().string("[]")); // it will return list of products in ArrayList but we need to convert it to JSON
        //so, jockson library will convert it to JSON format, by using ObjectMapper

        mockMvc.perform(get("/products"))
           .andExpect(status().isOk())
              .andExpect(content().string(objectMapper.writeValueAsString(products)));

    // right now, whatever we perform from the postman, we are doing the same thing here, but we are not calling the actual API, we are calling the mock API
        //we are trying to mimick it here,so, it proves to hits the end point and returns the data
    }


    //lets test the getSingleProduct, so, try to create a product
    @Test
    void createProduct()throws Exception {
        //Arrange part

        //we have identified which product we want to create

        Product productToCreate = new Product();
        productToCreate.setTitle("iPhone 15 Pro Max");
        productToCreate.setImageUrl("some image");
        productToCreate.setDescription("Best iPhone Ever");

        //what is the expected product from the service
        Product expectedProduct = new Product();
        expectedProduct.setId(1L);
        expectedProduct.setTitle("iPhone 15 Pro Max");
        expectedProduct.setImageUrl("some image");
        expectedProduct.setDescription("Best iPhone Ever");

        when(productService.addNewProduct(any(Product.class))).thenReturn(expectedProduct);

        //Act part
        //your client will call the post method call on /products, your endpoints will be called, and it will return the expected product

        mockMvc.perform(
                post("/products") // it will hit the end point
                        .contentType(MediaType.APPLICATION_JSON) // we are sending the JSON data
                        .content(objectMapper.writeValueAsString(productToCreate))) // we are sending the productToCreate
                .andExpect(status().isOk())// we are expecting the status to be OK
                .andExpect(content().string(objectMapper.writeValueAsString(expectedProduct)));// we are expecting the content to be the expected product
//                .andExpect(jsonPath("$.student.name", is("Nikhil")))
//                .andExpect(jsonPath("$.length()", is(2)));

    }
    //this is how unit testing is done for client perspective

}
