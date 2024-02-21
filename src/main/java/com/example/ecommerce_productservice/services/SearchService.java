package com.example.ecommerce_productservice.services;

import com.example.ecommerce_productservice.models.Product;
import com.example.ecommerce_productservice.models.SortParam;
import com.example.ecommerce_productservice.repositries.ProductElasticSearchRepo;
import com.example.ecommerce_productservice.repositries.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class SearchService {
    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
//    private ProductElasticSearchRepo productRepo;
//
//    public SearchService(ProductElasticSearchRepo productRepo) {
//        this.productRepo = productRepo;
//    }


    /*public List<Product> searchProducts(String query) {
        return productRepo.findByTitleEquals(query);

    }*/ // this method is used to search for products by their title

    //public List<Product> searchProducts(String query, int pageNumber, int sizeOfPage) {
        //return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage));
    //} // this method is used to implement pagination and shows list of products by their title


    //instead of list of products, we want to return a  page of products
    /*public Page<Product> searchProducts(String query, int pageNumber, int sizeOfPage) {
        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage));
    }*/


    //sorting , we have Hardcoded the sorting parameters but parameters can be changed according to the requirement

    /*public List <Product> searchProducts(String query, int pageNumber, int sizeOfPage) {
        Sort sort = Sort.by("title").descending()
                .and(Sort.by("price").descending());

        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage, sort));
    }*/


    //parameters can be changed according to the requirement,so, we have a sortParam class in the models package
    //go through all the parameters in the sortParam class and create a loop
    public List <Product> searchProducts(String query, int pageNumber, int sizeOfPage, List<SortParam> sortParamList) {
        Sort sort;
        if(sortParamList.get(0).getSortType().equals("ASC")) {
            sort = Sort.by(sortParamList.get(0).getParamName());
        } else {
            sort = Sort.by(sortParamList.get(0).getParamName()).descending();
        }

        for(int i = 1; i< sortParamList.size(); i++) {
            if(sortParamList.get(i).getSortType().equals("ASC")) {
                sort = sort.and(Sort.by(sortParamList.get(i).getParamName()));
            } else {
                sort = sort.and(Sort.by(sortParamList.get(i).getParamName())
                        .descending());
            }
        }

        return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber, sizeOfPage, sort));
        //return  productRepo.findAllByTitleContaining(query);


    }

}
