package ru.contesta.pronunciationtrainer.api.models;

public class AuthorizationRequest {
    private String Username;
    private String Password;

    public AuthorizationRequest(String username, String password) {
        this.Username = username;
        this.Password = password;
    }
}