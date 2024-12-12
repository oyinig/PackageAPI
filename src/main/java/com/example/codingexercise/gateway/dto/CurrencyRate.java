package com.example.codingexercise.gateway.dto;

import java.util.Map;

public record CurrencyRate(
    String base,
    String date,
    Map<String, Double> rates
) {} 