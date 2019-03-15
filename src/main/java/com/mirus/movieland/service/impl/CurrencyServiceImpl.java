package com.mirus.movieland.service.impl;

import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.CurrencyRate;
import com.mirus.movieland.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate;

    private Map<Currency, CurrencyRate> currencyRates;

    @Value("${currency.nbu.url}")
    private String url;

    private static final Predicate<CurrencyRate> isEUR = c -> Currency.EUR.name().equals(c.getCode());
    private static final Predicate<CurrencyRate> isUSD = c -> Currency.USD.name().equals(c.getCode());

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    @Override
    @PostConstruct
    @Scheduled(cron = "${currency.update.cron.expression}")
    public void updateRates() {

        try {
            writeLock.lock();
            this.currencyRates = new HashMap<>();

            Stream.of(restTemplate.getForObject(url, CurrencyRate[].class))
                    .filter(isEUR.or(isUSD))
                    .forEach(currencyRate -> currencyRates.put(Currency.fromValue(currencyRate.getCode()), currencyRate));
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public Double getRateByCurrency(Currency currency) {
        try {
            readLock.lock();
            return this.currencyRates.get(currency).getRate();
        } finally {
            readLock.unlock();
        }
    }
}



