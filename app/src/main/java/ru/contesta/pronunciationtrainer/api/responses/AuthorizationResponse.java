package ru.contesta.pronunciationtrainer.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizationResponse {
    @SerializedName("status")
    @Expose()
    private String Status;

    @SerializedName("token")
    @Expose()
    private String AccessToken;

    public String getStatus() {
        return Status;
    }

    public String getAccessToken() {
        return AccessToken;
    }
}