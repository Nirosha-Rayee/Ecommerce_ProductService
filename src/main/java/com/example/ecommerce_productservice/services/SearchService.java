package com.example.ecommerce_productservice.services;

import com.example.ecommerce_productservice.models.Product;
import com.example.ecommerce_productservice.repositries.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class SearchService {
    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /*public List<Product> searchProducts(String query) {
        return productRepo.findByTitleEquals(query);

    }*/ // this method is used to search for products by their title

    //public List<Product> searchProducts(String query, int pageNumber, int sizeOfPage) {
        //return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage));
    //} // this method is used to implement pagination and shows list of products by their title


    //instead of list of products, we want to return a  page of products
    public Page<Product> searchProducts(String query, int pageNumber, int sizeOfPage) {
        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage));
    }
}
