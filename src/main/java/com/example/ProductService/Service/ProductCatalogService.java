package com.example.ProductService.Service;

import com.example.ProductService.Model.ProductCatalog;
import com.example.ProductService.Repository.ProductCatalogRepository;
import com.example.ProductService.Util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class ProductCatalogService {

    @Autowired
    private ProductCatalogRepository productCatalogRepository;

    LoggerUtil productCatalogServiceLogger = new LoggerUtil(ProductCatalog.class);

    public List<ProductCatalog> getProductCatalog(String category, Integer price, Sort sort) {

        if (category != null && sort!=null) {
            return productCatalogRepository.findByCategoryAndPriceSorted(category, price,sort);
        }else if (category != null ) {
            return productCatalogRepository.findByCategoryAndPrice(category, price);
        }
        else if (category != null) {
            return productCatalogRepository.findByCategory(category);
        }

        return  StreamSupport.stream(productCatalogRepository.findAll().spliterator(), false)
                .toList();
    }

    public ProductCatalog getSurveyById(Long id) {
        return productCatalogRepository.findById(String.valueOf(id)).orElse(null);
    }

    public ProductCatalog getProductCatalogById(String id) {
        Optional<ProductCatalog> prdt = productCatalogRepository.findById(id);

        if(prdt.get()!= null){
            productCatalogServiceLogger.info("The product details are retrieved!");
            return prdt.get();
        }
        productCatalogServiceLogger.warn("No such product with the mentioned Id exists");
        return null;
    }

    public ProductCatalog saveProductCatalog(ProductCatalog prd){

        if(validateString(prd.getProductType()) && validateString((prd.getInventory()))){
            prd.setProductId( UUID. randomUUID().toString());
            productCatalogRepository.save(prd);

            productCatalogServiceLogger.info("Product Catalog saved!");
            return prd;
        }

        productCatalogServiceLogger.warn("Product Details insufficient!");
        return null;
    }

    public boolean validateString(String input){
        if(input!=null && !input.isBlank() && !input.isEmpty()) return true;
        return false;
    }
}