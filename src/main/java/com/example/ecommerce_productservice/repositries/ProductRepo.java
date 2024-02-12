package com.example.ecommerce_productservice.repositries;

import com.example.ecommerce_productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save (Product product); //save is a method in JpaRepository(interface)that is used to save an entity to the database
    Product findProductById(Long id); //findProductBy is a method in JpaRepository(interface) that is used to find a product by its id

    //Product findByPriceBetween(double greaterthan, double lessthan);

   // List<Product> findByAll();

    //Product findByProductName(String productName);

    // String findTitleById(Long id);

    List<Product> findByIdIsNotNullOrderByPrice();
    //List<Product> findAllByOOrderByPriceAsc(); error

    List<Product> findAllByIsPublicFalse();

    //List<Product> findAllByIsPublicTrue();

//    List<Product> findByTitleEquals(String title, Pageable pageable);
}
