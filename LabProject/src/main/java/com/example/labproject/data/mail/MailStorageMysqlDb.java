package com.example.labproject.data.mail;

import com.example.labproject.domain.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MailStorageMysqlDb implements MailStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Mail> getAllMailModels(String pattern) {
        List<Mail> mailList = null;
        StringBuilder sqlQuery = new StringBuilder("SELECT * from mail ");
        String sqlPattern = null;

        if (pattern != null) {
            sqlQuery.append(" WHERE mail.mail_name LIKE ? ");
            System.out.println(sqlQuery);
            sqlPattern = "%" + pattern + "%";
            mailList = jdbcTemplate.query(sqlQuery.toString(), new MailRowMapper(), sqlPattern);
            System.out.println(mailList);
        } else {
            mailList = jdbcTemplate.query(sqlQuery.toString(), new MailRowMapper());
        }

        return mailList;
    }

    @Override
    public int setMail(String mailName, String mailDescription) {
        int mailList;
        StringBuilder sqlQuery = new StringBuilder("INSERT INTO mail(mail_name, mail_description) VALUES (?, ?)");
        mailList = jdbcTemplate.update(sqlQuery.toString(), mailName, mailDescription);

        return mailList;
    }

    @Override
    public int deleteMail(String mailName) {
        int mailList;

        String sqlQuery = new String("SELECT mail_id from mail where mail.mail_name = ?");
        String mailId = (String) jdbcTemplate.queryForObject(sqlQuery, new Object[] { mailName }, String.class);
        String sqlQuery2 = new String("DELETE from user where user.user_id = ?");
        mailList = jdbcTemplate.update(sqlQuery2.toString(), mailId.toString());
        String sqlQuery3 = new String("DELETE from mail where mail.mail_id = ?");
        mailList = jdbcTemplate.update(sqlQuery3.toString(), mailId);

        return mailList;
    }

    @Override
    public int changeMail(String oldMailName, String newMailName) {
        int mailList;

        String sqlQuery = new String("UPDATE mail SET mail_name = ? WHERE mail_name = ?");
        mailList = jdbcTemplate.update(sqlQuery.toString(), newMailName, oldMailName);

        return mailList;
    }
}
