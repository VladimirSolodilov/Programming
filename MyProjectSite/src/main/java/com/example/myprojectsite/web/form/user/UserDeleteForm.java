package com.example.myprojectsite.web.form.user;
public class UserDeleteForm {
    private String nickName;

    public UserDeleteForm(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
