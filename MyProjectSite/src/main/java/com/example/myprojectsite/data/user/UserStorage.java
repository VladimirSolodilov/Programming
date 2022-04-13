package com.example.myprojectsite.data.user;

import com.example.myprojectsite.domain.model.User;

import java.util.List;

public interface UserStorage {
    List<User> getAllUser(String pattern);
    int setUser(String nickName, String firstName, String lastName, String login, String password);
    int deleteUser(String nickName);

}
