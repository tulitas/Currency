package com.example.currency.services;

import com.example.currency.proceses.XmlParser;
import com.example.currency.utils.FxRate;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
//            System.out.println(response);
           XmlParser.parseXml(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

