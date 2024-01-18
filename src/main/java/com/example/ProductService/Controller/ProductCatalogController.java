package com.example.ProductService.Controller;

import com.example.ProductService.Model.ProductCatalog;
import com.example.ProductService.Service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductCatalogController {


    @Autowired
    private ProductCatalogService productCatalogService;

    @GetMapping("/catalog")
    public List<ProductCatalog> getProductCatalog(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false, defaultValue = "asc") String sorting) {


        // Add validation here later
        Sort sort = Sort.by(Sort.Direction.fromString(sorting), "sorting");
        return productCatalogService.getProductCatalog(category,price,sort);
    }

    @GetMapping("/catalog/{id}")
    public ProductCatalog getProductById(@PathVariable String id) {
        return productCatalogService.getProductCatalogById(id);
    }

    @PostMapping("/catalog")
    public ProductCatalog createProductCatalog(@RequestBody ProductCatalog productCatalog) {
            return productCatalogService.saveProductCatalog(productCatalog);
    }
}

