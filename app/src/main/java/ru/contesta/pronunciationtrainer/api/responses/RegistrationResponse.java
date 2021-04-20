package ru.contesta.pronunciationtrainer.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.contesta.pronunciationtrainer.models.Profile;

public class RegistrationResponse {
    @SerializedName("status")
    @Expose()
    private String Status;

    @SerializedName("profile")
    @Expose()
    public Profile Profile;

    @SerializedName("token")
    @Expose()
    private String AccessToken;

    public String getStatus() {
        return Status;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public Profile getProfile() {
        return Profile;
    }
}