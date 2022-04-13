package com.example.labproject.data.both;

import com.example.labproject.domain.model.Both;
import com.example.labproject.domain.model.Mail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BothRowMapper implements RowMapper<Both> {

    @Override
    public Both mapRow(ResultSet resultSet, int i) throws SQLException {
        Both both = new Both();
        Mail mail = new Mail();

        mail.setMailId(resultSet.getInt("mail_id"));
        mail.setMailName(resultSet.getString("mail_name"));
        mail.setMailDescription(resultSet.getString("mail_description"));

        both.setMail(mail);
        both.setUserId(resultSet.getInt("user_id"));
        both.setLogin(resultSet.getString("user_login"));
        both.setFirstName(resultSet.getString("user_first_name"));
        both.setLastName(resultSet.getString("user_last_name"));

        return both;
    }

}
