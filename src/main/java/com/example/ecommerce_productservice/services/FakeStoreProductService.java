package com.example.ecommerce_productservice.services;

import com.example.ecommerce_productservice.clients.IClientProductDto;
import com.example.ecommerce_productservice.clients.fakestore.client.FakeStoreClient;
import com.example.ecommerce_productservice.clients.fakestore.dto.FakeStoreProductDto;
import com.example.ecommerce_productservice.dtos.ProductDto;
import com.example.ecommerce_productservice.models.Categories;
import com.example.ecommerce_productservice.models.Product;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpMethod;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;


import java.util.ArrayList;
import java.util.List;

//@Service
//@Primary
@Service
public class FakeStoreProductService implements IProductService{

    //add RestTemplate bean which is created in RestTemplateConfig class

    private RestTemplate restTemplate;
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;

    private RedisTemplate<String, Object> redisTemplate;


   // @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreClient fakeStoreClient, RedisTemplate<String, Object> redisTemplate, RestTemplate restTemplate) {

        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
        this.redisTemplate = redisTemplate;
        this.restTemplate = restTemplate;
    }

    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback =restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }// this is copied from RestTemplate class, for patch.


//    @Override
//    public String getAllProducts() {//change return type to List<Product> from String
//        return null; // Run the main2 method here and see the output, list of String & list of Integer, getting ListN as name.
//        //we can't differentiate between list of string and list of integer, that is the issue with Generics
//    }


 @Override
    public List<Product> getAllProducts() {

//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//        ResponseEntity<ProductDto[]> productDtos = restTemplate
//                .getForEntity("https://fakestoreapi.com/products",ProductDto[].class);

    List<Product> answer = new ArrayList<>();
     List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();

     for (FakeStoreProductDto productDto: fakeStoreProductDtos) { // changed from ProductDto to FakeStoreProductDto & removed .getBody()
         Product product = new Product();
         product.setId(productDto.getId());
         product.setTitle(productDto.getTitle());
         product.setPrice(productDto.getPrice());
         Categories category = new Categories();
         category.setName(productDto.getCategory());
         product.setCategory(category);
         product.setImageUrl(productDto.getImage());
         answer.add(product);
     }
     return answer;



    }

    @Override
    public Product getSingleProduct(Long productId) {  //String method is changed to Product method,
        // becoz, we hgot product dto from fakestoreapi. this we should not expose to the client. so, we need to convert it to our product.
        //so, we need to convert product dto to product, we need conversion logic.

//        RestTemplate restTemplate = restTemplateBuilder.build();
////      //ProductDto productDto = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",ProductDto.class,productId).getBody();//here we are getting product dto from fakestoreapi
//        //here when we are getting request(requestEntity), we have to return the responseEntity
//
//        ResponseEntity<FakeStoreProductDto> productDto = restTemplate.
//                getForEntity("https://fakestoreapi.com/products/{id}",FakeStoreProductDto.class,productId);
//        Product product = getProduct(productDto.getBody());
//        return product;

        //FakeStoreProductDto fs = fakeStoreClient.getSingleProduct(productId);
        //return new Product();





        FakeStoreProductDto fs = (FakeStoreProductDto) redisTemplate.opsForHash().get("products",productId);

        if(fs == null){

        //FakeStoreProductDto fsCached = (FakeStoreProductDto) redisTemplate.opsForHash().get("products",productId);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> productDto = restTemplate.
                getForEntity("https://fakestoreapi.com/products/{id}",FakeStoreProductDto.class,productId);
        fs = productDto.getBody();
        redisTemplate.opsForHash().put("products",productId,fs);
        }
        return getProduct(fs); // these lines are for redis cache


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

//        Product product = getProduct(productDto);
//        return product;
    }

    private Product getProduct(FakeStoreProductDto productDto) {
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

//    @Override
//    public Product addNewProduct(IClientProductDto productDto) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        restTemplate.postForEntity("https://fakestoreapi.com/products",productDto,ProductDto.class);
//        Product product = getProduct((FakeStoreProductDto) productDto);
//        return product;
//
//        //return null;
//    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long productId,Product product) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());


        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity
                = requestForEntity(HttpMethod.PATCH,
                "https://fakestoreapi.com/products/{id}",fakeStoreProductDto,FakeStoreProductDto.class,productId);

        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreProductDtoResponseEntity.getBody();
        return getProduct(fakeStoreProductDto1);

        //return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}
