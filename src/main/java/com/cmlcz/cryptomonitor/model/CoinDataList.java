package com.cmlcz.cryptomonitor.model;

import java.util.ArrayList;

public class CoinDataList {

    public ArrayList<CoinData> data;
    private Long timestamp;

    public ArrayList<CoinData> getData() {
        return data;
    }
    public void setData(ArrayList<CoinData> data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}

