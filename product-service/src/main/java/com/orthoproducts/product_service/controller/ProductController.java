package com.orthoproducts.product_service.controller;

import com.orthoproducts.product_service.client.UserClient;
import com.orthoproducts.product_service.model.Product;
import com.orthoproducts.product_service.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final UserClient userClient;

    private ProductService productService;

    public ProductController(UserClient userClient, ProductService productService){
        this.userClient = userClient;
        this.productService=productService;

    }

    @PostMapping("/create")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }


    @GetMapping("/with-users")
    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackUsers")
    public String getProductsWithUsers() {
        String users = userClient.getUsers();
        return "Products + " + users;
    }


    @GetMapping("/getUsers")
    public String getUsers() {
        return "Response from port: " + System.getProperty("server.port");
    }


    public String fallbackUsers(Exception ex){

        return "Users service is dowen";
    }
}
