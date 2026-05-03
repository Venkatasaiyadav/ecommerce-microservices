package com.orthoproducts.product_service.config;

import com.orthoproducts.product_service.service.ProductSearchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DataSyncRunner {




    @Bean
    CommandLineRunner syncData(ProductSearchService service) {
        return args -> {
            service.syncToElasticSearch();
        };
    }
}
