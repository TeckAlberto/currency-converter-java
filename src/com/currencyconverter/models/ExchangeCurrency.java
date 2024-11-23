package com.currencyconverter.models;

import java.util.Map;

public record ExchangeCurrency(String base_code, Map<String, Double> conversion_rates) {

}
