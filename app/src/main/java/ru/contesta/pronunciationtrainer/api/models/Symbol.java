package ru.contesta.pronunciationtrainer.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Symbol {
    @SerializedName("symbol")
    @Expose()
    private String symbol;

    @SerializedName("is_correct")
    @Expose()
    private boolean isCorrect;

    public Symbol(String symbol, boolean isCorrect){
        this.symbol = symbol;
        this.isCorrect = isCorrect;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public boolean getIsCorrect(){
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect){
        this.isCorrect = isCorrect;
    }
}
