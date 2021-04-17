package ru.contesta.pronunciationtrainer.exception;

public class FailedToRestoreRecord extends AppException {
    @Override
    public int getType() {
        return AppException.FAILED_TO_RESTORE;
    }
}
