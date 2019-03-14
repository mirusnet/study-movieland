package com.mirus.movieland.repository;

import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.CurrencyRate;

import java.util.List;

public interface CurrencyRateRepository {

    CurrencyRate findByCurrency(Currency currency);

    List<CurrencyRate> findAll();

    void updateRate(CurrencyRate currencyRate);
}
