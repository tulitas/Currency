package com.example.currency.services;

import com.example.currency.proceses.XmlParser;
import com.example.currency.utils.CcyAmt;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class CurrencyJob implements Job {
    @Autowired
    private CurrencyRateService currencyRateService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            System.out.println("CurrencyJob is executing...");
            String apiUrl = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl, String.class);
            List<CcyAmt> currencies = XmlParser.parseXml(response);
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String currencyDate = currentDate.format(formatter);
            for (CcyAmt currencyRate : currencies) {
                currencyRateService.saveToDb(currencyRate, currencyDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

