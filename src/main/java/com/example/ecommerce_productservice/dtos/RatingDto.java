package com.example.ecommerce_productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString

public class RatingDto implements Serializable { //added serializable for redis cache
    private Double rate;
    private Double count;

}
