package com.example.labproject.data.user;

import com.example.labproject.domain.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User userModel = new User();

        userModel.setUserId(resultSet.getInt("user_id"));
        userModel.setLogin(resultSet.getString("user_login"));
        userModel.setFirstName(resultSet.getString("user_first_name"));
        userModel.setLastName(resultSet.getString("user_last_name"));

        return userModel;
    }
}
