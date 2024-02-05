package com.example.ecommerce_productservice.controllers;

import com.example.ecommerce_productservice.dtos.ProductDto;
import com.example.ecommerce_productservice.models.Product;
import com.example.ecommerce_productservice.services.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    //    @GetMapping("/{id}")
//    public Product getSingleProduct(@PathVariable("id") Long productId){ // change return type to Product from String
//
//        //productService.getSingleProduct(productId);
//        //String product = productService.getSingleProduct(productId);
//        Product product = productService.getSingleProduct(productId);
//
//        //return "Returing single product with id " + productId;
//        return product;
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable("id") Long productId) {
        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            headers.add("Accept", "application/json"); // instead of json data, we can use xml
            headers.add("Content-Type", "application/json");
            headers.add("auth-token", "heyaccess");
            Product product = productService.getSingleProduct(productId);
            if (productId < 1) {
                throw new IllegalArgumentException("something went wrong");
            }
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, headers, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            ResponseEntity<Product> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
            // return new  ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 error
        }

    }

    @GetMapping("")
    //public String getAllProducts(){// change return type to List<Product> from String
        //return "Returning all products";
    //}
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts(); // this is a shortcut, we can also use the below code
    }

//    @GetMapping("")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        return new ResponseEntity<>(this.productService.getAllProducts(), HttpStatus.OK);
//    } // like we done for getSingleProduct method, we can also do exception handling here


    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductDto productDto){// change return type to Product from String
        Product product = productService.addNewProduct(productDto);
        ResponseEntity<Product> responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        //save the product data for the database. but fake store api is not allowing to save the data after adding the product
        return responseEntity;
        //return "Adding new product" + productDto;
    }

    @PutMapping("/{productId}")
    public String updateProduct(@PathVariable("productId") Long productId) {
        return "Updating product with id " + productId;
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        return "Deleting product with id " + productId;
    }




}
