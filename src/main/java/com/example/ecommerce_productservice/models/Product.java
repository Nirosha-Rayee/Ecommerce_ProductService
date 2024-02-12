package com.example.ecommerce_productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Product extends BaseModel{
    private String title;
    private String description;
    private String imageUrl;
    private Double price;
    //@ManyToOne (cascade = CascadeType.MERGE)
    @ManyToOne (cascade = CascadeType.ALL)
    private Categories category;
    private Boolean isPublic;
}
