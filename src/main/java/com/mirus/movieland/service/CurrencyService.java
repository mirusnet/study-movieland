package com.mirus.movieland.service;

import com.mirus.movieland.entity.Currency;

public interface CurrencyService {

    void updateRates();

    Double getRateByCurrency(Currency currency);
}
