package com.example.ProductService.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "product_catalog")
public class ProductCatalog {

    @Id
    private String productId;
    @Field(type = FieldType.Keyword, name = "product_type")
    private String productType;

    @Field(type = FieldType.Integer, name = "price")
    private Integer price;
    @Field(type = FieldType.Integer, name = "category")
    private Integer category;

    @Field(type = FieldType.Text, name = "inventory")
    private String inventory;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }
}