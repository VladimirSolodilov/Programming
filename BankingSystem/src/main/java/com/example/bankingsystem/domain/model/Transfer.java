package com.example.bankingsystem.domain.model;

public class Transfer {
    private String leftUser;
    private String rightUser;
    private int sum;
    public Transfer() {}

    public Transfer(String leftUser, String rightUser, int sum) {
        this.leftUser = leftUser;
        this.rightUser = rightUser;
        this.sum = sum;
    }

    public String getLeftUser() {
        return leftUser;
    }

    public void setLeftUser(String leftUser) {
        this.leftUser = leftUser;
    }

    public String getRightUser() {
        return rightUser;
    }

    public void setRightUser(String rightUser) {
        this.rightUser = rightUser;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
