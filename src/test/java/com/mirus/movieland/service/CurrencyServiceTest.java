package com.mirus.movieland.service;

import com.mirus.movieland.entity.CurrencyRate;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class CurrencyServiceTest {

    @Test
    public void test() {
        String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date=20190314&json";

        RestTemplate restTemplate = new RestTemplate();

        CurrencyRate[] currencyRates = restTemplate.getForObject(url, CurrencyRate[].class);
        for(CurrencyRate currencyRate : currencyRates) {
            System.out.println(currencyRate.getCode());
            if (currencyRate.getCode().equals("EUR") || currencyRate.getCode().equals("USD") ) {
                System.out.println(currencyRate.getRate());
            }
        }
    }
}