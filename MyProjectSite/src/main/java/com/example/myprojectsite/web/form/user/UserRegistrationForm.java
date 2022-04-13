package com.example.myprojectsite.web.form.user;
public class UserRegistrationForm {
    private String mailName;
    private String firstName;
    private String lastName;
    private String nickName;
    private String login;
    private String password;

    public UserRegistrationForm() {}

    public UserRegistrationForm(String mailName, String firstName, String lastName, String nickName, String login, String password) {
        this.mailName = mailName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.login = login;
        this.password = password;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
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
