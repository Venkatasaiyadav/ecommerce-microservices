package com.orthoproducts.product_service.service;

import com.orthoproducts.product_service.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendProductCreatedWebhook(Product product) {

        String url = "http://localhost:8081/webhook/product";

        Map<String, Object> payload = new HashMap<>();
        payload.put("event", "PRODUCT_CREATED");
        payload.put("productId", product.getId());
        payload.put("name", product.getName());

        try {
            restTemplate.postForEntity(url, payload, String.class);
        } catch (Exception e) {
            System.out.println("Webhook failed: " + e.getMessage());
        }
    }
}