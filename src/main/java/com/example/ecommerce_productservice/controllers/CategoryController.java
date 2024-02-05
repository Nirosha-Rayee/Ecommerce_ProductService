package com.example.ecommerce_productservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products/categories")
//@RequestMapping("/users/categories")
public class CategoryController {
    public String getAllCategories(){
        return "getting  all categories";
    }
    public String getProductsInCategory(Long categoryId){
        return "getting products in category with id " + categoryId;
    }

}
