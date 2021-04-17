package ru.contesta.pronunciationtrainer.exception;

public class PlayerInitException extends AppException {
    @Override
    public int getType() {
        return AppException.PLAYER_INIT_EXCEPTION;
    }
}
