package com.example.bankingsystem.domain.JuridicalPerson;

import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Transfer;

import java.util.List;

public interface JuridicalPersonService {
    List<JuridicalPerson> getPersonList(String personName);
    List<JuridicalPerson> getPersonBySurnameLike(String surname);

    boolean setPersonList(int branchId, int roleId, String surname, String name, String patronymic, String organizationName, String personName, String password, int sum);
    boolean deletePersonList(String personName);

    List<JuridicalPerson> getIdByPersonName(String personName);
    boolean addSum(String personName, int sum);
    boolean transfer(String leftPerson, String rightPerson, int sum);

    List<Transfer> transferInfo(String personName);
    boolean saveClient(JuridicalPerson juridicalPerson);
}
