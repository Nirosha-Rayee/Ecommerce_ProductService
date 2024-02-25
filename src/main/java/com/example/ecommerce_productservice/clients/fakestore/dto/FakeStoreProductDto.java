package com.example.ecommerce_productservice.clients.fakestore.dto;

import com.example.ecommerce_productservice.clients.IClientProductDto;
import com.example.ecommerce_productservice.dtos.RatingDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FakeStoreProductDto implements IClientProductDto , Serializable { //added serializable for redis cache
    private Long id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

    private RatingDto rating;
}
