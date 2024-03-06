package com.example.currency.controllers;

import com.example.currency.etities.CurrencyEntity;
import com.example.currency.services.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/")
public class CurrencyController<CurrencyService> {

    @Autowired
    private CurrencyRateService currencyService;

    @GetMapping
    public List<CurrencyEntity> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/currenciesByDate")
    public List<CurrencyEntity> getAllCurrenciesByDate() {
        return currencyService.getCurrenciesByDate();
    }

    @GetMapping("/history")
    public List<CurrencyEntity>  getHistory(@RequestParam String currencyCode) {
        List<CurrencyEntity>  history = currencyService.getHistoryByCurrency(currencyCode);
        return history;
    }

}
