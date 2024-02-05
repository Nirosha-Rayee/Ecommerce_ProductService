package com.example.ecommerce_productservice.services;

import com.example.ecommerce_productservice.dtos.ProductDto;
import com.example.ecommerce_productservice.models.Categories;
import com.example.ecommerce_productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class SelfProductService implements IProductService{
    private RestTemplateBuilder restTemplateBuilder;

    public SelfProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }


    @Override
    public String getAllProducts() {
        return null;
    }

    @Override
    public Product getSingleProduct(Long productId) {  //String method is changed to Product method,
        // becoz, we hgot product dto from fakestoreapi. this we should not expose to the client. so, we need to convert it to our product.
        //so, we need to convert product dto to product, we need conversion logic.

        RestTemplate restTemplate = restTemplateBuilder.build();
        ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",ProductDto.class,productId).getBody();
        //return productDto.toString();

        //instead of the above lines, if we put map.class, we get a list of products in key value pairs
        //Map productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",Map.class,productId).getBody();
        ////return productDto.toString();
        //if any changes in object class, we need to change the code in the above line, like this below lines.so, we use json to convert the object to string
        //ProductDto productDto1 = new ProductDto();
        //productDto1.setId(Long.parseLong(productDto.get("id").toString()));

       // return productDto.toString(); //put a breakpoint here(debug point) and run the application in debug mode(click on debug sysmbol)


        //THIS IS CONVERSION LOGIC, BUT WE NEED TO PUT THIS IN A SEPARATE METHOD
//        Product product = new Product();
//        product.setId(productDto.getId());
//        product.setTitle(productDto.getTitle());
//        product.setPrice(productDto.getPrice());
//        Categories category = new Categories();
//        category.setName(productDto.getCategory());
//        product.setCategory(category);
//        product.setImageUrl(productDto.getImage());
//        product.setDescription(productDto.getDescription());
//        return product;

        Product product = getProduct(productDto);
        return product;
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Categories category = new Categories();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        product.setImageUrl(productDto.getImage());
        product.setDescription(productDto.getDescription());
        return product;
    }

    @Override
    public String addNewProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public String updateProduct(Long productId) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
