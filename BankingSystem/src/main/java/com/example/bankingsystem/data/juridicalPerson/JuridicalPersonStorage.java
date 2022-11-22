package com.example.bankingsystem.data.juridicalPerson;

public interface JuridicalPersonStorage {

    List<JuridicalPerson> getAllPerson(String pattern);
    int setJuridicalPerson (int clientId, int branchId, int roleId, String surname, String name, String patronymic, String JuridicalPersonName, String password, int sum);
    int deleteJuridicalPerson (String surname);

}
