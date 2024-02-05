package com.example.ecommerce_productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
    //private String rating;
    private RatingDto rating;
}
