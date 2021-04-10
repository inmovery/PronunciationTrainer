package ru.contesta.pronunciationtrainer.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import ru.contesta.pronunciationtrainer.models.AudioDetails;

public class AudioResponse {
    @SerializedName("results")
    @Expose()
    private AudioDetails audioDetails;

    @SerializedName("percentage")
    @Expose()
    private double Percentage;

    public AudioDetails getAudioDetails() {
        return audioDetails;
    }

    public double getPercentage() {
        return Percentage;
    }
}