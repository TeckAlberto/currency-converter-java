package com.currencyconverter.connections;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeConnection {

    private final String url;

    public ExchangeConnection(String currency) {
        this.url = "https://v6.exchangerate-api.com/v6/14db9bcdb410455487300a10/latest/" + currency;
    }

    public String getResponse() throws InterruptedException, IOException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.valueOf(url)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();

    }


}
