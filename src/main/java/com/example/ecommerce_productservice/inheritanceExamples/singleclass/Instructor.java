package com.example.ecommerce_productservice.inheritanceExamples.singleclass;

//import com.example.ecommerce_productservice.inheritanceExamples.tableperclass.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "sc_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String company;
}
