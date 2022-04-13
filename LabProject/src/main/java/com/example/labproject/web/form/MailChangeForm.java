package com.example.labproject.web.form;

public class MailChangeForm {

    private String oldMailName;
    private String newMailName;

    public MailChangeForm() {}

    public MailChangeForm(String oldMailName, String newMailName) {
        this.oldMailName = oldMailName;
        this.newMailName = newMailName;
    }

    public String getOldMailName() {
        return oldMailName;
    }

    public void setOldMailName(String oldMailName) {
        this.oldMailName = oldMailName;
    }

    public String getNewMailName() {
        return newMailName;
    }

    public void setNewMailName(String newMailName) {
        this.newMailName = newMailName;
    }

    @Override
    public String toString() {
        return "MailChangeForm{" +
                "oldMailName='" + oldMailName + '\'' +
                ", newMailName='" + newMailName + '\'' +
                '}';
    }
}
