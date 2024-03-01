package com.example.currency.services;

import com.example.currency.items.CurrencyRate;
import com.example.currency.proceses.XmlParser;
import com.example.currency.utils.CcyAmt;
import com.example.currency.utils.FxRate;
import com.example.currency.utils.FxRates;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@Component
public class CurrencyJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            System.out.println("CurrencyJob is executing...");
            String apiUrl = "https://www.lb.lt/webservices/FxRates/FxRates.asmx/getCurrentFxRates?tp=EU";
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(apiUrl, String.class);
            FxRates currencies = XmlParser.parseXml(response);
            List<FxRate> currencyRates = currencies.getFxRateList();
            for (FxRate currencyRate : currencyRates) {
                for (CcyAmt x : currencyRate.getCcyAmts()) {
                    System.out.println("Currency: " + x.getCcy());
                    System.out.println("Rate: " + x.getAmt());
                }
                System.out.println("********");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

