package ru.contesta.pronunciationtrainer.exception;

public class InvalidOutputFile extends AppException {
    @Override
    public int getType() {
        return AppException.INVALID_OUTPUT_FILE;
    }
}
