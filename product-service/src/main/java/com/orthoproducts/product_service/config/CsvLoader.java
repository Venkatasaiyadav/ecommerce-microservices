package com.orthoproducts.product_service.config;

import com.opencsv.CSVReader;
import com.orthoproducts.product_service.model.Product;
import com.orthoproducts.product_service.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStreamReader;

@Configuration
public class CsvLoader {

    @Bean
    CommandLineRunner loadCSV(ProductRepository repository) {

        System.out.println("Started the LoadCSVMethod");
        return args -> {

            if (repository.count() > 0) return;

            try (CSVReader reader = new CSVReader(
                    new InputStreamReader(
                            getClass().getResourceAsStream("/data/products.csv")))) {

                String[] line;
                boolean firstLine = true;

                while ((line = reader.readNext()) != null) {

                    if (firstLine) {
                        firstLine = false;
                        continue;
                    }

                    try {
                        Product product = new Product();

                        // Mapping based on your dataset
                        product.setId(line[0]); // uniq_id
                        product.setName(line[1]); // product_name

                        // description fallback
                        String desc = line[9];
                        if (desc == null || desc.isEmpty()) {
                            desc = "No description";
                        }
                        product.setDescription(desc);

                        product.setCategory(line[8]);

                        // Clean price
                        String priceStr = line[3].replaceAll("[^0-9.]", "");
                        product.setPrice(priceStr.isEmpty() ? 0 : Double.parseDouble(priceStr));

// Rating
                        String ratingStr = line[7].replaceAll("[^0-9.]", "");
                        product.setRating(ratingStr.isEmpty() ? 0 : Double.parseDouble(ratingStr));

                        repository.save(product);

                    } catch (Exception e) {
                        System.out.println("⚠️ Skipping bad row");
                    }
                }

                System.out.println("✅ CSV dataset loaded successfully");

            } catch (Exception e) {
                System.out.println("❌ Error reading CSV: " + e.getMessage());
            }
        };
    }
}