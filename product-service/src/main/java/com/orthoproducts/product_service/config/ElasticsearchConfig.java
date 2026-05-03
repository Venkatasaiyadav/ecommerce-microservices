//package com.orthoproducts.product_service.config;
//
//import org.springframework.data.elasticsearch.client.ClientConfiguration;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
//
//public class ElasticsearchConfig extends ElasticsearchConfiguration {
//
//
//    @Override
//    public RestClientConfiguration clientConfiguration(){
//        return RestClientConfiguration.builder()
//                .connectedTo("localhost:9200")
//                .usingSsl() // VERY IMPORTANT
//                .withBasicAuth("elastic", "YOUR_PASSWORD")
//                .build();
//
//    }
//
//}
