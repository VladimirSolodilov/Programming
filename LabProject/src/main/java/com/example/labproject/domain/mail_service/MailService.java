package com.example.labproject.domain.mail_service;

import com.example.labproject.domain.model.Mail;

import java.util.List;

public interface MailService {

    List<Mail> getMailList();
    List<Mail> getMailByMailNameLike(String name);
    boolean isMailExist(String name);
    int setMailList(String mailName, String mailDescription);
    int deleteMailList(String mailName);
    int changeMail(String oldMailName, String newMailName);

}
