package com.example.labproject.data.both;

import com.example.labproject.domain.model.Both;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BothStorageMysqlDb implements BothStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Both> getAll(String pattern) {
        List<Both> model = null;
        StringBuilder sqlQuery = new StringBuilder("SELECT * from user left join mail on user.user_id = mail.mail_id ");
        String sqlPattern = null;

        if (pattern != null) {
            sqlQuery.append(" WHERE user.user_first_name LIKE ? ");
            System.out.println(sqlQuery);
            sqlPattern = "%" + pattern + "%";
            model = jdbcTemplate.query(sqlQuery.toString(), new BothRowMapper(), sqlPattern);
            System.out.println(model);
        } else {
            model = jdbcTemplate.query(sqlQuery.toString(), new BothRowMapper());
        }

        return model;
    }

    @Override
    public List<Both> getAll1(String pattern) {
        List<Both> model = null;
        StringBuilder sqlQuery = new StringBuilder("SELECT * from mail left join user on mail.mail_id = user.user_id ");
        String sqlPattern = null;

        if (pattern != null) {
            sqlQuery.append(" WHERE user.user_first_name LIKE ? ");
            System.out.println(sqlQuery);
            sqlPattern = "%" + pattern + "%";
            model = jdbcTemplate.query(sqlQuery.toString(), new BothRowMapper(), sqlPattern);
            System.out.println(model);
        } else {
            model = jdbcTemplate.query(sqlQuery.toString(), new BothRowMapper());
        }

        return model;
    }
}
