package com.example.labproject.data.user;

import com.example.labproject.domain.model.User;

import java.util.List;

public interface UserStorage {
    List<User> getAllUser(String pattern);
    int setUser (String mailName, String login, String firstName, String lastName);
    int deleteUser (String login);
}
