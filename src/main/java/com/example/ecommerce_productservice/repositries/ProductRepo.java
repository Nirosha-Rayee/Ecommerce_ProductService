package com.example.ecommerce_productservice.repositries;

import com.example.ecommerce_productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product save (Product product); //save is a method in JpaRepository(interface)that is used to save an entity to the database
}
