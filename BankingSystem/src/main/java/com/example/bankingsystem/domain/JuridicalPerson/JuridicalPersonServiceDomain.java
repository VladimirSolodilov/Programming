package com.example.bankingsystem.domain.JuridicalPerson;

import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonStorage;
import com.example.bankingsystem.data.transfer.TransferStorage;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JuridicalPersonServiceDomain implements JuridicalPersonService {
    @Autowired
    @Lazy
    protected PasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JuridicalPersonStorage juridicalPersonStorage;
    @Autowired
    private TransferStorage transferStorage;
    @Override
    public List<JuridicalPerson> getPersonList(String personName) {
        return juridicalPersonStorage.getAllPerson(personName);
    }

    @Override
    public List<JuridicalPerson> getPersonBySurnameLike(String surname) {
        return juridicalPersonStorage.getAllPerson(surname);
    }

    public boolean setPersonList(int branchId, int roleId, String surname, String name, String patronymic, String organizationName, String personName, String password, int sum) {
        return juridicalPersonStorage.createJuridicalPerson(branchId, roleId, surname, name, patronymic, organizationName, personName, bCryptPasswordEncoder.encode(password), sum);
    }

    @Override
    public boolean deletePersonList(String personName) {
        return juridicalPersonStorage.deleteJuridicalPerson(personName);
    }

    @Override
    public boolean personChange(String surname, String name, String patronymic, String organizationName, String juridicalPersonName) {
        return juridicalPersonStorage.personChange(surname, name, patronymic, organizationName, juridicalPersonName);
    }
    @Override
    public boolean addSum(String personName, int sum) {
        return juridicalPersonStorage.addSum(personName, sum);
    }

    @Override
    public boolean transfer(String leftPerson, String rightPerson, int sum) {
        return juridicalPersonStorage.transfer(leftPerson, rightPerson, sum);
    }
    @Override
    public List<Transfer> transferInfo(String personName) {
        return transferStorage.viewTransferInfo(personName);
    }

    @Override
    public List<JuridicalPerson> getPersonByPaymentName(String paymentName) {
        return juridicalPersonStorage.getPersonByPaymentName(paymentName);
    }
}
