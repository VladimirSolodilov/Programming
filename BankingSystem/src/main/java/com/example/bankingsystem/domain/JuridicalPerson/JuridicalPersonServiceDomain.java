package com.example.bankingsystem.domain.JuridicalPerson;

import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonStorage;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JuridicalPersonServiceDomain implements JuridicalPersonService {

    @Autowired
    private JuridicalPersonStorage juridicalPersonStorage;

    @Override
    public List<JuridicalPerson> getPersonList() {
        return juridicalPersonStorage.getAllPerson(null);
    }

    @Override
    public List<JuridicalPerson> getPersonBySurnameLike(String surname) {
        return juridicalPersonStorage.getAllPerson(surname);
    }

    @Override
    public int setPersonList(int juridicalPersonId, int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum) {
        return juridicalPersonStorage.setJuridicalPerson(juridicalPersonId, branchId, roleId, surname, name, patronymic, clientName, password, sum);
    }

    @Override
    public int deletePersonList(String surname) {
        return juridicalPersonStorage.deleteJuridicalPerson(surname);
    }
}
