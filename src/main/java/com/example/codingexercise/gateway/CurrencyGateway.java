package com.example.codingexercise.gateway;

import com.example.codingexercise.gateway.dto.CurrencyRate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyGateway {
    private final RestTemplate restTemplate;

    public CurrencyGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Double getExchangeRate(String targetCurrency) {
        CurrencyRate response = restTemplate.getForObject(
            "https://api.frankfurter.dev/latest?base=USD",
            CurrencyRate.class
        );
        return response.rates().getOrDefault(targetCurrency, 1.0);
    }
} 