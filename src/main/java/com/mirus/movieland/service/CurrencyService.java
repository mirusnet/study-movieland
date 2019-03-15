package com.mirus.movieland.service;

import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.CurrencyRate;

public interface CurrencyService {
    void updateRates();

    CurrencyRate getRateByCurrency(Currency currency);
}
