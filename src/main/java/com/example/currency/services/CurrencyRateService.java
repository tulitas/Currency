package com.example.currency.services;

import com.example.currency.etities.CurrencyEntity;
import com.example.currency.repositories.CurrencyRepository;
import com.example.currency.utils.CcyAmt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CurrencyRateService {

    private final CurrencyRepository currencyRepository;
    private static final Logger logger = LoggerFactory.getLogger(CurrencyRateService.class);
    @Autowired
    public CurrencyRateService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }
    @Transactional
    public void saveToDb(CcyAmt currencyRate, String currencyDate) {
        CurrencyEntity currencyRateEntity = new CurrencyEntity();
        currencyRateEntity.setCurrency(currencyRate.getCcy());
        currencyRateEntity.setRate(currencyRate.getAmt());
        currencyRateEntity.setDate(currencyDate);
        currencyRepository.save(currencyRateEntity);
    }

    public List<CurrencyEntity> getAllCurrencies() {
        return (List<CurrencyEntity>) currencyRepository.findAll();
    }

    public List<CurrencyEntity> getCurrenciesByDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currencyDate = currentDate.format(formatter);

        return (List<CurrencyEntity>) currencyRepository.findByDate(currencyDate) ;
    }

    public List<CurrencyEntity>  getHistoryByCurrency(String currencyCode) {
        return currencyRepository.findByCurrency(currencyCode);
    }
}
