package com.example.bankingsystem.domain.model;

public class Logs {

    private int logId;
    private String log;

    public Logs() {}

    public Logs(int logId, String log) {
        this.logId = logId;
        this.log = log;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
