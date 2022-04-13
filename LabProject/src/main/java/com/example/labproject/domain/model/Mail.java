package com.example.labproject.domain.model;

import org.springframework.boot.context.properties.bind.Name;

public class Mail {

    private int mailId;
    private String mailName;
    private String mailDescription;

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getMailDescription() {
        return mailDescription;
    }

    public void setMailDescription(String mailDescription) {
        this.mailDescription = mailDescription;
    }

    public Mail() {
        super();
    }

    public Mail(int mailId, String mailName, String mailDescription) {
        this.mailId = mailId;
        this.mailName = mailName;
        this.mailDescription = mailDescription;
    }

    @Override
    public String toString() {
        return "MailModel{" +
                "mailId=" + mailId +
                ", mailName='" + mailName + '\'' +
                ", mailDescription='" + mailDescription + '\'' +
                '}';
    }
}
