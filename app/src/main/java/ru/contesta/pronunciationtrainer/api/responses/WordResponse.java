package ru.contesta.pronunciationtrainer.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.contesta.pronunciationtrainer.api.models.Symbol;

public class WordResponse {
    @SerializedName("word")
    @Expose()
    private String word;

    @SerializedName("phonemes")
    @Expose()
    private String phonemes;

    public String getPhonemes(){
        return phonemes;
    }

    public String getWord() {
        return word;
    }
}
