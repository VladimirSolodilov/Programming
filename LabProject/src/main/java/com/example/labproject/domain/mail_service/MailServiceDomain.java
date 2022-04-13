package com.example.labproject.domain.mail_service;

import com.example.labproject.data.mail.MailRepository;
import com.example.labproject.data.mail.MailStorage;
import com.example.labproject.domain.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailServiceDomain implements MailService {

    @Autowired
    private MailStorage storage;

    @Autowired
    MailRepository mailRepository;

    @Override
    public List<Mail> getMailList() {
        return storage.getAllMailModels(null);
    }

    @Override
    public List<Mail> getMailByMailNameLike(String name) {
        return storage.getAllMailModels(name);
    }

    @Override
    public boolean isMailExist(String name) {
        return mailRepository.countByMailName(name) != 0 ? true : false;
    }

    @Override
    public int setMailList(String mailName, String mailDescription) {
        return storage.setMail(mailName, mailDescription);
    }

    @Override
    public int deleteMailList(String mailName) {
        return storage.deleteMail(mailName);
    }

    @Override
    public int changeMail(String oldMailName, String newMailName) {
        return storage.changeMail(oldMailName, newMailName);
    }
}
