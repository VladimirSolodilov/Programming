package com.example.labproject.data.mail;

import com.example.labproject.domain.model.Mail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MailRowMapper implements RowMapper<Mail> {

    @Override
    public Mail mapRow(ResultSet resultSet, int i) throws SQLException {
        Mail mail = new Mail();
        mail.setMailId(resultSet.getInt("mail_id"));
        mail.setMailName(resultSet.getString("mail_name"));
        mail.setMailDescription(resultSet.getString("mail_description"));

        return mail;
    }
}
