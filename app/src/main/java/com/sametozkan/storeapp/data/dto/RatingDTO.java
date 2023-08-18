package com.sametozkan.storeapp.data.dto;

import com.google.gson.annotations.SerializedName;

public class RatingDTO {
    @SerializedName("rate")
    private double rate;

    @SerializedName("count")
    private int count;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
