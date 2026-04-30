package com.orthoproducts.product_service.service;

import com.netflix.discovery.converters.Auto;
import com.orthoproducts.product_service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProductService {


    @Autowired
    private WebhookService webhookService;


    @Autowired
    MongoTemplate mongoTemplate;


    public Product createProduct(Product product){
        Product saved=mongoTemplate.save(product);
        webhookService.sendProductCreatedWebhook(saved);
        return saved;

    }
}
