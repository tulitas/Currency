package com.example.currency.items;

import lombok.Data;

@Data
public class CurrencyRate {
    private String currency;
    private double rate;

    public CurrencyRate(String currency, double rate) {
        this.currency = currency;
        this.rate = rate;
    }
}
