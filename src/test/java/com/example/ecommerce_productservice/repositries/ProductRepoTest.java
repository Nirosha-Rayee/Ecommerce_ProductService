package com.example.ecommerce_productservice.repositries;

import com.example.ecommerce_productservice.models.Categories;
import com.example.ecommerce_productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    void saveProductsAndCategory() {
    //create a category
        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories = categoryRepo.save(categories);

        //create a product
        Product product = new Product();
        product.setTitle("Laptop");
        product.setPrice(1000.00);
        product.setCategory(categories);
        product.setDescription("Laptop");
        productRepo.save(product);

        Categories categories1 = categoryRepo.findById(categories.getId()).get();
        List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }



    @Test
    @Transactional
    void saveProductsAndCategory1() {
        Product product = new Product();
        product.setTitle("Laptop");
        product.setDescription("Laptop");
        productRepo.save(product);

        Categories categories = new Categories();
        categories.setName("Electronics");
        categories.setDescription("Electronics");
        categories.setProductList(List.of(product));
        categories = categoryRepo.save(categories);

        Categories categories1 = categoryRepo.findById(categories.getId()).get();
        List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }

    @Test
        //@Transactional
    void saveProductsAndCategory2() {
        Categories categories = new Categories();
        categories.setName("Fashion");
        categories.setDescription("Fashion");
        //categories = categoryRepo.save(categories);

        Product product = new Product();
        product.setTitle("Tshirt");
        product.setDescription("Tshirt");
        product.setPrice(100.00);
        product.setCategory(categories);
        productRepo.save(product);

        //Categories categories1 = categoryRepo.findById(categories.getId()).get();
        //List<Product> productList = categories1.getProductList();
        System.out.println("Debug");

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void saveProductsAndCategory3() {

        Categories category = categoryRepo.findById(2L);
        List<Product> productList = category.getProductList();
          for (Product product : productList) {
            System.out.println(product.getPrice());
        }
        //System.out.println("Debug");

//        Product product = new Product();
//        product.setPrice(1012.00);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        Product savedProduct = productRepo.save(product);
//
//        product = new Product();
//        product.setPrice(103.00);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        productRepo.save(product);
//
//        System.out.println("Debug");


    }

    @Test
    @Transactional
    //@Rollback(value = false)
    void saveProductsAndCategory4() {

//        Categories category = categoryRepo.findById(2L);
//        List<Product> productList = category.getProductList();
//        for (Product product : productList) {
//            System.out.println(product.getPrice());
//        }
        //Product product = productRepo.findProductById(3L);// i put 1L, its given details becoz, i was in product table. 3L not there in product table,so it will give null.


       //Product product = productRepo.findByPriceBetween(100, 1012);
        //List<Product> productList = productRepo.findByAll();
        //List<Product> productList = productRepo.findAllByOOrderByPriceAsc();
        //List<Product> productList = productRepo.findAllByIsPublicFalse();

       List<Product> productList = productRepo.findByIdIsNotNullOrderByPrice(); // it will give all the products in the table


        System.out.println("Debug");

//        Product product = new Product();
//        product.setPrice(1012.00);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        Product savedProduct = productRepo.save(product);
//
//        product = new Product();
//        product.setPrice(103.00);
//        product.setImageUrl("hiii");
//        product.setCategory(category);
//        productRepo.save(product);
//
//        System.out.println("Debug");


    }


}