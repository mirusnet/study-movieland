package com.mirus.movieland.repository.jdbc;

import com.mirus.movieland.entity.Currency;
import com.mirus.movieland.entity.CurrencyRate;
import com.mirus.movieland.repository.CurrencyRateRepository;
import com.mirus.movieland.repository.jdbc.mapper.CurrencyRateRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CurrencyRateRepositoryImpl implements CurrencyRateRepository {

    private static final CurrencyRateRowMapper CURRENCY_RATE_ROW_MAPPER = new CurrencyRateRowMapper();

    private static final String GET_ALL_CURRENCY_RATES_SQL = "select id, currency, rate from rates";
    private static final String GET_ONE_BY_CURRENCY = "select id, currency, rate from rates where currency = ?";
    private static final String INSERT_CURRENCY_RATE = "update rates set rate = ?  where currency = ?";

    private final JdbcTemplate jdbcTemplate;

    @Override
    public CurrencyRate findByCurrency(Currency currency) {
        return jdbcTemplate.queryForObject(GET_ONE_BY_CURRENCY, CURRENCY_RATE_ROW_MAPPER, currency.name());
    }

    @Override
    public List<CurrencyRate> findAll() {
        return jdbcTemplate.query(GET_ALL_CURRENCY_RATES_SQL, CURRENCY_RATE_ROW_MAPPER);
    }

    @Override
    public void updateRate(CurrencyRate currencyRate) {
        jdbcTemplate.update(INSERT_CURRENCY_RATE, currencyRate.getRate(), currencyRate.getCode());
    }
}
