package com.cmlcz.cryptomonitor;

import com.cmlcz.cryptomonitor.model.CoinData;
import com.cmlcz.cryptomonitor.model.CoinDataList;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class CryptoAPI {

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String COINCAP_ASSETS_BASE_API = "https://api.coincap.io/v2/assets?";
    private static final DecimalFormat PRICE_FORMAT = new DecimalFormat("$#,###.##");

    public static final List<String> COIN_TYPES = Arrays.asList(
            "bitcoin",
            "ethereum",
            "dogecoin",
            "xrp",
            "tether",
            "solana",
            "avalanche"
    );

    public String getCurrentCoinPrice(String coin){

        try{
            String searchQueryParam = "search=" + coin;
            String limitQueryParam = "limit=1";
            URI uri = URI.create(COINCAP_ASSETS_BASE_API + String.join("&", Arrays.asList(searchQueryParam, limitQueryParam)));
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .build();

            HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

            String responseBody = response.body();

            CoinDataList coinDataList = MAPPER.readValue(responseBody, CoinDataList.class);

            CoinData firstCoin = coinDataList.getData().get(0);
            Timestamp time = new Timestamp(coinDataList.getTimestamp());

            double priceUsd = Double.parseDouble(firstCoin.getPriceUsd());
            System.out.println(time);
            return PRICE_FORMAT.format(priceUsd);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}

