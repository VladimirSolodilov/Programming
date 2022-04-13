package com.example.myprojectsite.domain.user_service;

import com.example.myprojectsite.data.user.UserStorage;
import com.example.myprojectsite.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceDomain implements UserService {
    @Autowired
    private UserStorage userStorage;


    @Override
    public List<User> getUserList() {
        return userStorage.getAllUser(null);
    }

    @Override
    public List<User> getUserByNickNameLike(String nickName) {
        return userStorage.getAllUser(nickName);
    }

    @Override
    public int setUserList(String firstName, String lastName, String nickName, String login, String password) {
        return userStorage.setUser(firstName, lastName, nickName, login, password);
    }

    @Override
    public int deleteUserList(String nickName) {
        return userStorage.deleteUser(nickName);
    }
}
