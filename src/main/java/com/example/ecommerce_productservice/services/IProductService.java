package com.example.ecommerce_productservice.services;

import com.example.ecommerce_productservice.dtos.ProductDto;
import com.example.ecommerce_productservice.models.Product;

import java.util.List;

public interface IProductService {
    //String getAllProducts(); // change return type to List<Product> from String
    List<Product> getAllProducts();

    //String getSingleProduct(Long productId); // change return type to Product from String
    Product getSingleProduct(Long productId);

    Product addNewProduct(ProductDto productDto);

    String updateProduct(Long productId);

    String deleteProduct(Long productId);

}
