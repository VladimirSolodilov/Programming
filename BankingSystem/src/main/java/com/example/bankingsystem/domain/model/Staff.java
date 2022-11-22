package com.example.bankingsystem.domain.model;

public class Staff {

    private Bank bank;
    private int staffId;
    private String name;
    private String roleId;

    public Staff() {}

    public Staff(Bank bank, int staffId, String name, String roleId) {
        this.bank = bank;
        this.staffId = staffId;
        this.name = name;
        this.roleId = roleId;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
