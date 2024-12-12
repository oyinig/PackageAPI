package com.example.codingexercise.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "productPackage", cascade = CascadeType.ALL)
    private List<Product> products;

    private BigDecimal price;

    // Constructors
    public Package() {}

    public Package(Long id, String name, String description, List<Product> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.products = products;
        this.price = calculateTotalPrice(products);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        this.price = calculateTotalPrice(products);
    }

    public BigDecimal getPrice() {
        return price;
    }

    private BigDecimal calculateTotalPrice(List<Product> products) {
        return products.stream()
                       .map(Product::getPrice)
                       .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}