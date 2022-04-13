package com.example.myprojectsite.domain.model;
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String nickName;
    private String login;
    private String password;

    public User(int userId, String firstName, String lastName, String nickName, String login, String password) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.login = login;
        this.password = password;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
