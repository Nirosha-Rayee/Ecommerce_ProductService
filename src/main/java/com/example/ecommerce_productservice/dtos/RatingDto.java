package com.example.ecommerce_productservice.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class RatingDto {
    private Double rate;
    private Double count;

}
