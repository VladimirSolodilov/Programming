package com.example.labproject.domain.user_service;

import com.example.labproject.data.user.UserStorage;
import com.example.labproject.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceDomain implements UserService {

    @Autowired
    private UserStorage storage;

    @Override
    public List<User> getUserList() {
        return storage.getAllUser(null);
    }

    @Override
    public List<User> getUserByMailNameLike(String name) {
        return storage.getAllUser(name);
    }

    @Override
    public int setUserList(String mailName, String login, String firstName, String lastName) {
        return storage.setUser(mailName, login, firstName, lastName);
    }

    @Override
    public int deleteUserList(String login) {
        return storage.deleteUser(login);
    }
}
