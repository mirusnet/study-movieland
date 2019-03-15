package com.mirus.movieland.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRate {

    @JsonProperty("r030")
    private int id;

    @JsonProperty("txt")
    private String fullName;

    @JsonProperty("rate")
    private Double rate;

    @JsonProperty("cc")
    private String code;

    @JsonProperty("exchangedate")
    private String date;
}
