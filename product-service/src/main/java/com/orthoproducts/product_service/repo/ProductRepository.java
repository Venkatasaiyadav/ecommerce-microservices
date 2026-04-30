package com.orthoproducts.product_service.repo;

import com.orthoproducts.product_service.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
