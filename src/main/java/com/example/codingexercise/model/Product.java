package com.example.codingexercise.model;

import java.math.BigDecimal;
import com.example.codingexercise.model.Package;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private String name;
    private BigDecimal price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package productPackage;

    // Constructors
    public Product() {}

    public Product(Long id, String productId, String name, BigDecimal price, Package productPackage) {
        this.id = id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.productPackage = productPackage;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Package getPackage() {
        return productPackage;
    }

    public void setPackage(Package productPackage) {
        this.productPackage = productPackage;
    }
} 