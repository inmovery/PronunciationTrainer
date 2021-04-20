package ru.contesta.pronunciationtrainer.models;

public class Profile {
    private String Login;
    private String Password;
    private int Score;
    private int CountOfAttempts;
    private int CountOfHardDays;

    public Profile(String login, String password, int score, int countOfAttempts, int countOfHardDays) {
        this.Login = login;
        this.Password = password;
        this.Score = score;
        this.CountOfAttempts = countOfAttempts;
        this.CountOfHardDays = countOfHardDays;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getCountOfAttempts() {
        return CountOfAttempts;
    }

    public void setCountOfAttempts(int countOfAttempts) {
        CountOfAttempts = countOfAttempts;
    }

    public int getCountOfHardDays() {
        return CountOfHardDays;
    }

    public void setCountOfHardDays(int countOfHardDays) {
        CountOfHardDays = countOfHardDays;
    }
}