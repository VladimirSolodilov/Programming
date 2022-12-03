package com.example.bankingsystem.domain.JuridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;

import java.util.List;

public interface JuridicalPersonService {
    List<JuridicalPerson> getPersonList(String personName);
    List<JuridicalPerson> getPersonBySurnameLike(String surname);

    int setPersonList(int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    int deletePersonList(String surname);

    int addSum(String personName, int sum);
    int transfer(String leftPerson, String rightPerson, int sum);

    boolean saveClient(JuridicalPerson juridicalPerson);
}
