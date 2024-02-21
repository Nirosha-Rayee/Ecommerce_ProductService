package com.example.ecommerce_productservice.repositries;

import com.example.ecommerce_productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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



    //this query related to search by title in the database and return the result , related to elastic search class
    //List<Product> findByTitleEquals(String title);

    //after the search by title, we have implemented pagination to return the result in pages

    //List<Product> findByTitleEquals(String title, PageRequest pageable); //this query is shows list of products by their title

    //instead of list of products, we want to return a  page of products
    //Page<Product> findByTitleEquals(String title, PageRequest pageable);

    //Page<Product> findByTitleEqualsOrderBy(String title, PageRequest pageable); // we can do sorting by adding OrderBy in the query

    List <Product> findByTitleEquals(String title, PageRequest pageable);



}



