package com.example.labproject.domain.user_service;

import com.example.labproject.domain.model.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();
    List<User> getUserByMailNameLike(String name);
    int setUserList (String mailName, String login, String firstName, String lastName);
    int deleteUserList(String login);

}
