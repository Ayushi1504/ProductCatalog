package com.example.ProductService.Repository;

import com.example.ProductService.Model.ProductCatalog;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductCatalogRepository extends ElasticsearchRepository<ProductCatalog, String> {
    List<ProductCatalog> findByCategory(String category);

    List<ProductCatalog> findByCategoryAndPrice(String category, Integer price);

    List<ProductCatalog> findByCategoryAndPriceSorted(String category, Integer price, Sort sort);
}