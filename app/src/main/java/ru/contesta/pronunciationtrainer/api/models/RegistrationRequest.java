package ru.contesta.pronunciationtrainer.api.models;

public class RegistrationRequest {
    private String Username;
    private String Password;

    public RegistrationRequest(String username, String password) {
        this.Username = username;
        this.Password = password;
    }
}