package com.example.ecommerce_productservice.controllers;

import com.example.ecommerce_productservice.dtos.ProductDto;
import com.example.ecommerce_productservice.models.Product;
import com.example.ecommerce_productservice.services.IProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId){ // change return type to Product from String

        //productService.getSingleProduct(productId);
        //String product = productService.getSingleProduct(productId);
        Product product = productService.getSingleProduct(productId);

        //return "Returing single product with id " + productId;
        return product;
    }

    @GetMapping("")
    public String getAllProducts(){
        return "Returning all products";
    }
    @PostMapping()
    public String addNewProduct(@RequestBody ProductDto productDto){
        return "Adding new product" + productDto;
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
