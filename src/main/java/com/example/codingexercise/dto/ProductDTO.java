package com.example.codingexercise.dto;

import java.math.BigDecimal;

public record ProductDTO(
    Long id,
    String productId,
    String name,
    BigDecimal price
) {} 