package com.example.codingexercise.dto;

import java.util.List;

public record PackageDTO(
    Long id,
    String name,
    String description,
    List<ProductDTO> products
) {}