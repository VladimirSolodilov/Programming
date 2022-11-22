package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;

import java.util.List;

public interface JuridicalPersonStorage {

    List<JuridicalPerson> getAllPerson(String pattern);
    int setJuridicalPerson (int clientId, int branchId, int roleId, String surname, String name, String patronymic, String JuridicalPersonName, String password, int sum);
    int deleteJuridicalPerson (String surname);

}
