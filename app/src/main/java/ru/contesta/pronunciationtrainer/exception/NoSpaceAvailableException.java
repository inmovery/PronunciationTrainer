package ru.contesta.pronunciationtrainer.exception;

public class NoSpaceAvailableException extends AppException {
    @Override
    public int getType() {
        return AppException.NO_SPACE_AVAILABLE;
    }
}
