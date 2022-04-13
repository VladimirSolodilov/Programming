package com.example.labproject.web.form;

public class MailDeleteForm {

    private String mailName;

    public MailDeleteForm(String mailName) {
        this.mailName = mailName;
    }

    public MailDeleteForm() {}

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }
}
