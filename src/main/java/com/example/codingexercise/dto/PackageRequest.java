package com.example.codingexercise.dto;

import jakarta.persistence.*;


import java.util.List;

public class PackageRequest {

    public String name;
    public String description;

    public List<String> products;

    //  Constructors
    public PackageRequest() {}

    public PackageRequest(String name, String description, List<String> products) {
        this.name = name;
        this.description = description;
        this.products = products;
    }

    // Getters and Setters

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

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public List<String> getProducts() {
        return products;
    }



}
