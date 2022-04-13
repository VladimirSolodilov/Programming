package com.example.myprojectsite.data.user;

import com.example.myprojectsite.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public class UserStorageMariaDb implements UserStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUser(String pattern) {
        StringBuffer sqlQuery = new StringBuffer("select * from user ");
        String sqlPattern = null;

        if (pattern != null) {
            sqlQuery.append(" where user.user_name like ? ");
            sqlPattern = "%" + pattern + "%";
            return jdbcTemplate.query(sqlQuery.toString(), new UserRowMapper(), sqlPattern);
        } else {
            return jdbcTemplate.query(sqlQuery.toString(), new UserRowMapper());
        }
    }

    @Override
    public int setUser(String login, String firstName, String lastName, String nickName, String password) {
        String sqlQuery = "insert into user values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sqlQuery, login, firstName, lastName, nickName, password);
    }

    @Override
    public int deleteUser(String nickName) {
        String sqlQuery = "DELETE from user where user_nickname = ?";
        return jdbcTemplate.update(sqlQuery, nickName);
    }
}
