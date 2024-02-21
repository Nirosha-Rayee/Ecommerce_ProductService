package com.example.ecommerce_productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String query; // search query first used it to search for products by their title
    private int pageNumber;
    private int sizeOfPage; //  pageNumber & sizeOfPage attributes are for ,the number of products to be displayed on a page
//    private List<SortParam> sortParamList;
}
