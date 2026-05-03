package com.orthoproducts.product_service.controller;

import com.orthoproducts.product_service.model.ProductDocument;
import com.orthoproducts.product_service.repo.ProductSearchRepository;
import com.orthoproducts.product_service.service.ProductSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class SearchController {

    private final ProductSearchRepository repository;

    private final ProductSearchService productSearchService;

    public SearchController(ProductSearchRepository repository,ProductSearchService productSearchService) {
        this.repository = repository;
        this.productSearchService=productSearchService;
    }

    @GetMapping("/search")
    public List<ProductDocument> search(@RequestParam String keyword) {
        System.out.println("Entered with the KeyWord:--->"+keyword);
        return productSearchService.search(keyword);
    }
}