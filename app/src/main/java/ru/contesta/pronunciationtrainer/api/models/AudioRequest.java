package ru.contesta.pronunciationtrainer.api.models;

import java.io.File;

public class AudioRequest {
    private File RecordedFile;
    private String Word;

    public AudioRequest(File file, String word) {
        this.RecordedFile = file;
        this.Word = word;
    }

    public File getRecordedFile() {
        return RecordedFile;
    }

    public void setRecordedFile(File recordedFile) {
        RecordedFile = recordedFile;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }
}