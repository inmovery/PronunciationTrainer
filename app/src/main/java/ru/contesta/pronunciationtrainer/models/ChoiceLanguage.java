package ru.contesta.pronunciationtrainer.models;

public class ChoiceLanguage {

    private boolean isSelected = false;
    private String languageTitle;

    public ChoiceLanguage(String languageTitle) {
        this.languageTitle = languageTitle;
    }

    public void setLanguageTitle(String languageTitle) {
        this.languageTitle = languageTitle;
    }

    public String getLanguageTitle() {
        return languageTitle;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelectedState(boolean isSelectedState) {
        this.isSelected = isSelectedState;
    }
}
