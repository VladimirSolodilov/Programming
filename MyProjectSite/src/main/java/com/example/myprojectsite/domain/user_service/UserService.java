package com.example.myprojectsite.domain.user_service;

import com.example.myprojectsite.domain.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    List<User> getUserByNickNameLike(String nickName);
    int setUserList(String firstName, String lastName, String
                    nickName, String login, String password);
    int deleteUserList(String nickName);
}
