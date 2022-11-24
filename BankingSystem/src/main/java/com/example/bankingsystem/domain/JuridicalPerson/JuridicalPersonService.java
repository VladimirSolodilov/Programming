package com.example.bankingsystem.domain.JuridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;

import java.util.List;

public interface JuridicalPersonService {
    List<JuridicalPerson> getPersonList();
    List<JuridicalPerson> getPersonBySurnameLike(String surname);

    int setPersonList(int juridicalPersonId, int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    int deletePersonList(String surname);


    boolean saveClient(JuridicalPerson juridicalPerson);
}