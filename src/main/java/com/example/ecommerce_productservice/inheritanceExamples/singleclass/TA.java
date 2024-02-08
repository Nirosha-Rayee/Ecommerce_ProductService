package com.example.ecommerce_productservice.inheritanceExamples.singleclass;

//import com.example.ecommerce_productservice.inheritanceExamples.tableperclass.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "sc_ta")
@DiscriminatorValue(value = "1")
public class TA extends User {
    private double rating;
}
