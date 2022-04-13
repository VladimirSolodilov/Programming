package com.example.labproject.web.form;

public class UserRegistrationForm {

    private String mailName;
    private String login;
    private String firstName;
    private String lastName;

    public UserRegistrationForm() {}

    public UserRegistrationForm(String mailName, String login, String firstName, String lastName) {
        this.mailName = mailName;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
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
}
