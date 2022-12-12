package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;

import java.util.List;

public interface JuridicalPersonStorage {
    List<JuridicalPerson> getAllPerson(String pattern);
    List<JuridicalPerson> getIdByPersonName(String personName);
    boolean createJuridicalPerson (int branchId, int roleId, String surname, String name, String patronymic, String organizationName, String juridicalPersonName, String password, int sum);
    boolean deleteJuridicalPerson (String personName);
    boolean personChange(String surname, String name, String patronymic, String organizationName, String juridicalPersonName);
    boolean addSum(String personName, int sum);
    boolean transfer(String leftPerson, String rightPerson, int sum);
}
