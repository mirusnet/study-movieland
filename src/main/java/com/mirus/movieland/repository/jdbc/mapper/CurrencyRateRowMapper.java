package com.mirus.movieland.repository.jdbc.mapper;

import com.mirus.movieland.entity.CurrencyRate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CurrencyRateRowMapper implements RowMapper<CurrencyRate> {

    @Override
    public CurrencyRate mapRow(ResultSet rs, int rowNum) throws SQLException {
        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setId(rs.getInt("id"));
        currencyRate.setRate(rs.getDouble("rate"));
        currencyRate.setCode(rs.getString("currency"));
        return currencyRate;
    }
}
