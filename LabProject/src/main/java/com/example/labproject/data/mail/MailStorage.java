package com.example.labproject.data.mail;

import com.example.labproject.domain.model.Mail;

import java.util.List;

public interface MailStorage {

    List<Mail> getAllMailModels(String pattern);
    int setMail (String mailName, String mailDescription);
    int deleteMail (String mailName);
    int changeMail (String oldMailName, String newMailName);
}
