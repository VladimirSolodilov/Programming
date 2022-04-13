package com.example.myprojectsite.data.user;

import com.example.myprojectsite.domain.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User userModel = new User();

        userModel.setUserId(rs.getInt("user_id"));
        userModel.setFirstName(rs.getString("user_first_name"));
        userModel.setLastName(rs.getString("user_last_name"));
        userModel.setLogin(rs.getString("user_login"));
        userModel.setPassword(rs.getString("user_password"));

        return userModel;
    }
}
