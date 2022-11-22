package com.example.labproject.data.user;

import com.example.labproject.data.mail.MailRowMapper;
import com.example.labproject.domain.model.Mail;
import com.example.labproject.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserStorageMysqlDb implements UserStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUser(String pattern) {
        List<User> userList = null;
        StringBuilder sqlQuery = new StringBuilder("SELECT * from user ");
        String sqlPattern = null;

        if (pattern != null) {
            sqlQuery.append(" WHERE user.user_first_name LIKE ? ");
            System.out.println(sqlQuery);
            sqlPattern = "%" + pattern + "%";
            userList = jdbcTemplate.query(sqlQuery.toString(), new UserRowMapper(), sqlPattern);
            System.out.println(userList);
        } else {
            userList = jdbcTemplate.query(sqlQuery.toString(), new UserRowMapper());
        }

        return userList;
    }

    @Override
    public int setUser(String mailName, String login, String firstName, String lastName) {
        int userList;

        String sqlQuery1 = "SELECT mail_id from mail where mail.mail_name = ?";
        String sqlQuery2 = "insert into user values (?, ?, ?, ?)";
        String mailId = (String) jdbcTemplate.queryForObject(sqlQuery1, new Object[] { mailName }, String.class);
        userList = jdbcTemplate.update(sqlQuery2.toString(), mailId, login, firstName, lastName);

        return userList;
    }

    @Override
    public int deleteUser(String login) {
        int userList;

        String sqlQuery = new String("DELETE from user where user_login = ?");
        userList = jdbcTemplate.update(sqlQuery, login);

        return userList;
    }
}
