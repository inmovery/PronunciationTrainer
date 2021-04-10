package ru.contesta.pronunciationtrainer.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {
    @SerializedName("image_url")
    @Expose()
    private String imageUrl;

    @SerializedName("count_days")
    @Expose()
    private int countDaysInRow;

    @SerializedName("rate_value")
    @Expose()
    private double rateValue;

    @SerializedName("countAttempts")
    @Expose()
    private int countAttempts;

    public String getImageUrl() {
        return imageUrl;
    }

    public int getCountDaysInRow() {
        return countDaysInRow;
    }

    public double getRateValue() {
        return rateValue;
    }

    public int getCountAttempts() {
        return countAttempts;
    }
}
