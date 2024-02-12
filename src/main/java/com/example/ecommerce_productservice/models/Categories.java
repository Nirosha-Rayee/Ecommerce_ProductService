package com.example.ecommerce_productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@Entity

public class Categories extends BaseModel{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY) // default is EAGER fetch, so we need to specify LAZY fetch
    //@OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    //@Fetch(FetchMode.SUBSELECT)
    private List<Product> productList;


}
