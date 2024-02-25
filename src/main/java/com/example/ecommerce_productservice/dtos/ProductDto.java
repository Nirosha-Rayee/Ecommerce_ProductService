package com.example.ecommerce_productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ProductDto implements Serializable { //for redis cache
    //public class ProductDto{
    private Long id;
    private String title;
    private Double price;
    private String description;
    private String image;
    private String category;
    //private String rating;
    private RatingDto rating;
}
