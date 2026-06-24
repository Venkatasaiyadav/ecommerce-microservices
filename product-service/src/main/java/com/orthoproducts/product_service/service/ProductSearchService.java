    package com.orthoproducts.product_service.service;

    import com.orthoproducts.product_service.model.Product;
    import com.orthoproducts.product_service.model.ProductDocument;
    import com.orthoproducts.product_service.repo.ProductRepository;
    import com.orthoproducts.product_service.repo.ProductSearchRepository;
    import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
    import org.springframework.data.elasticsearch.core.query.Query;
    import org.springframework.data.elasticsearch.core.query.Query;
    import org.springframework.stereotype.Service;
    import org.springframework.data.elasticsearch.core.query.StringQuery;
    import org.springframework.data.elasticsearch.core.SearchHit;

    import java.util.List;
    import java.util.stream.Collectors;

    import static org.springframework.data.elasticsearch.client.elc.Queries.matchQuery;

    @Service
    public class ProductSearchService {

        private final ElasticsearchOperations elasticsearchOperations;


        private final ProductRepository productRepository;
        private final ProductSearchRepository searchRepository;

        public ProductSearchService(ProductRepository productRepository,
                                    ProductSearchRepository searchRepository,
                                    ElasticsearchOperations elasticsearchOperations) {
            this.productRepository = productRepository;
            this.searchRepository = searchRepository;
            this.elasticsearchOperations=elasticsearchOperations;
        }


        public void syncToElasticSearch(){
            List<ProductDocument> documentList = productRepository.findAll()
                    .stream()
                    .map(this::convertToDocument)
                    .filter(doc -> doc != null) // ✅ remove bad records
                    .collect(Collectors.toList());

            searchRepository.saveAll(documentList);
            System.out.println("🔥 Data indexed into Elasticsearch");
        }




        public List<ProductDocument> search(String keyword) {

            String queryJson = """
        {
          "match": {
            "name": {
              "query": "%s",
              "fuzziness": "AUTO"
            }
          }
        }
        """.formatted(keyword);

            StringQuery query = new StringQuery(queryJson);

            return elasticsearchOperations.search(query, ProductDocument.class)
                    .stream()
                    .map(SearchHit::getContent)
                    .toList();
        }


            private ProductDocument convertToDocument(Product product) {

            if (product.getId() == null || product.getId().isEmpty()) {
                return null; // ❌ skip invalid data
            }

            ProductDocument doc = new ProductDocument();
            doc.setId(product.getId());
            doc.setName(product.getName());
            doc.setDescription(product.getDescription());
            doc.setCategory(product.getCategory());
            doc.setPrice(product.getPrice());
            doc.setRating(product.getRating());
            return doc;
        }
    }
