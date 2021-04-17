package ru.contesta.pronunciationtrainer.exception;

public class CantProcessRecord extends AppException {
    @Override
    public int getType() {
        return AppException.CANT_PROCESS_RECORD;
    }
}
