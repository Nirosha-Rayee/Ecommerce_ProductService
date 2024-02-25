package com.example.ecommerce_productservice.services;

import com.example.ecommerce_productservice.dtos.ProductDto;
import com.example.ecommerce_productservice.dtos.UserDto;
import com.example.ecommerce_productservice.models.Product;
//import com.example.ecommerce_productservice.repositries.ProductElasticSearchRepo;
import com.example.ecommerce_productservice.repositries.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Primary
@Service
public class SelfProductService implements IProductService{

    ProductRepo productRepo;
    //ProductElasticSearchRepo productElasticSearchRepo;

    private RestTemplate restTemplate;

    public SelfProductService(ProductRepo productRepo, RestTemplate restTemplate) {
        this.productRepo = productRepo;
       // this.productElasticSearchRepo = productElasticSearchRepo;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
        //return null;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        // RestTemplate restTemplate = new RestTemplate();
        //ResponseEntity<UserDto> response = restTemplate.getForEntity("http://localhost:9002/users/1", UserDto.class); //these two lines are used before creating the RT Config class.
        //return new Product(); // return for to get call from user service


        ResponseEntity<UserDto> response = restTemplate.getForEntity("http://UserService/users/1", UserDto.class);

        return new Product();


       // return productRepo.getReferenceById(productId);



        //return null;
    }

    @Override
    public Product addNewProduct(Product product) {
//        this.productRepo.save(product);
//        return product;

        //after elastic search added,  first save in DB and we have to save the product in the elastic search repo as well
        this.productRepo.save(product);
       // this.productElasticSearchRepo.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
