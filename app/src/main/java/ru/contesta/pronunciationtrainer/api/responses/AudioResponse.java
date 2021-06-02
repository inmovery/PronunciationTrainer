package ru.contesta.pronunciationtrainer.api.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import ru.contesta.pronunciationtrainer.api.models.Symbol;
import ru.contesta.pronunciationtrainer.models.AudioDetails;

public class AudioResponse {

    @SerializedName("result")
    @Expose()
    private List<Symbol> symbols;

    public List<Symbol> getSymbols() {
        return symbols;
    }
}