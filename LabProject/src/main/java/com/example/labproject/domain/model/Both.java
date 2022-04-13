package com.example.labproject.domain.model;

public class Both {

    private Mail mail;
    private int userId;
    private String login;
    private String firstName;
    private String lastName;

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Both (Mail mail, int userId, String login, String firstName, String lastName) {
        super();
        this.mail = mail;
        this.userId = userId;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Both() {
        super();
    }

    @Override
    public String toString() {
        return "BothModel{" +
                "mailModel=" + mail +
                ", userId=" + userId +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
