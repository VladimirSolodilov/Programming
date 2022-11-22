package com.example.bankingsystem.data.juridicalPerson;

public class JuridicalPersonStorageDB implements JuridicalPersonStorage {

    @Override
    public List<JuridicalPerson> getAllPerson(String pattern) {
        return null;
    }

    @Override
    public int setJuridicalPerson(int clientId, int branchId, int roleId, String surname, String name, String patronymic, String JuridicalPersonName, String password, int sum) {
        return 0;
    }

    @Override
    public int deleteJuridicalPerson(String surname) {
        return 0;
    }

}
