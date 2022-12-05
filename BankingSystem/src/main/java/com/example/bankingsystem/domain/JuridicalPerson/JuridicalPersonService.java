package com.example.bankingsystem.domain.JuridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Transfer;

import java.util.List;

public interface JuridicalPersonService {
    List<JuridicalPerson> getPersonList(String personName);
    List<JuridicalPerson> getPersonBySurnameLike(String surname);

    int setPersonList(int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    int deletePersonList(String surname);

    List<JuridicalPerson> getIdByPersonName(String personName);
    int addSum(String personName, int sum);
    int transfer(String leftPerson, String rightPerson, int sum);

    List<Transfer> transferInfo(String personName);
    boolean saveClient(JuridicalPerson juridicalPerson);
}
