package com.example.ecommerce_productservice.controllers;

import com.example.ecommerce_productservice.dtos.ProductDto;
import com.example.ecommerce_productservice.dtos.SearchRequestDto;
import com.example.ecommerce_productservice.models.Product;
import com.example.ecommerce_productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;

    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @PostMapping


    /*public List<ProductDto> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        //List<Product> result = searchService.searchProducts(searchRequestDto.getQuery()); // this line is used to search for products by their title

        List<Product> result = searchService.searchProducts(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage()); // this line is used to implement pagination & shows list of products by their title

        List<ProductDto> shareableResult = new LinkedList<>();
        for(Product product : result) {
            shareableResult.add(getProduct(product));
        }
        return shareableResult;
//        List<Product> result = searchService.searchProducts(searchRequestDto.getQuery(),
//                searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage());
//        return result;
    }*/




    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {

    Page<Product> result = searchService.searchProducts(searchRequestDto.getQuery(),
            searchRequestDto.getPageNumber(), searchRequestDto.getSizeOfPage());
    return result;
    } // this will return the result in pages


    private ProductDto getProduct(Product p) {
        ProductDto product = new ProductDto();
        product.setId(p.getId());
        product.setTitle(p.getTitle());
        product.setPrice(p.getPrice());
        return product;
    }

}
