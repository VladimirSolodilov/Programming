package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.data.account.AccountRowMapper;
import com.example.bankingsystem.data.payment.PaymentIdRowMapper;
import com.example.bankingsystem.data.transfer.TransferStorage;
import com.example.bankingsystem.domain.model.Account;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@Slf4j
public class JuridicalPersonStorageDB implements JuridicalPersonStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TransferStorage transferStorage;
    String createPerson = "INSERT into JuridicalPerson VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String createAccountForPerson = "Insert into Account Values (?, ?, ?)";
    String createAccountRequisitesForPerson = "Insert into AccountRequisites Values (?, ?, ?)";
    String getAccountByAccountId = "Select * From Account Where Account.PersonId = ?";
    String deletePerson = "Delete from JuridicalPerson Where JuridicalPerson.PersonName Like ?";
    String deleteAccount = "Delete from Account Where Account.PersonId = ?";
    String deleteAccountRequisites = "Delete from AccountRequisites Where AccountRequisites.AccountId = ?";
    String getAccountByPersonId = "Select * from Account Where Account.PersonId = ?";
    String getPersonIdByPerson = "Select * From JuridicalPerson Where JuridicalPerson.PersonName = ?";
    String changePerson = "Update JuridicalPerson Set Surname = ?, Name = ?, Patronymic = ?, OrganizationName = ?, PersonName = ? " +
            "Where JuridicalPerson.PersonId = ?";
    String addSum = "Update Account Set Account.Sum = Account.Sum + ? Where Account.PersonId = ?";
    String getPersonByPersonName = "Select * from JuridicalPerson Join Account on JuridicalPerson.PersonId = Account.PersonId join AccountRequisites on Account.AccountId = AccountRequisites.AccountId join Branch on Branch.BranchId = JuridicalPerson.BranchId Where JuridicalPerson.PersonName Like ?";
    String transferPersonLeft = "Update Account Set Account.Sum = Account.Sum - ? Where Account.PersonId = ?";
    String transferPersonRight = "Update Account Set Account.Sum = Account.Sum + ? Where Account.PersonId = ?";
    @Override
    public List<JuridicalPerson> getAllPerson(String pattern) {
        StringBuilder getAllPerson = new StringBuilder("SELECT * from JuridicalPerson Join Account on JuridicalPerson.PersonId = Account.PersonId " +
                "join AccountRequisites on Account.AccountId = AccountRequisites.AccountId join Branch on JuridicalPerson.BranchId = Branch.BranchId");
        List<JuridicalPerson> juridicalPeoples;

        if (!Objects.equals(pattern, "admin")) {
            getAllPerson.append(" WHERE JuridicalPerson.PersonName LIKE ?");
            juridicalPeoples = jdbcTemplate.query(getAllPerson.toString(), new JuridicalPersonRowMapper(), pattern);
        } else {
            juridicalPeoples = jdbcTemplate.query(getAllPerson.toString(), new JuridicalPersonRowMapper());
        }

        return juridicalPeoples;
    }

    @Override
    public boolean createJuridicalPerson(int branchId, int roleId, String surname, String name, String patronymic, String organizationName, String juridicalPersonName, String password, int sum) {
        String getPersonByPersonName = "Select * from JuridicalPerson Where JuridicalPerson.PersonName LIKE ?";

        jdbcTemplate.update(createPerson, branchId, roleId, surname, name, patronymic, organizationName, juridicalPersonName, password);

        List<JuridicalPerson> juridicalPeoples = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonCreateRowMapper(), juridicalPersonName);

        jdbcTemplate.update(createAccountForPerson, juridicalPeoples.get(0).getJuridicalPersonId(), null, sum);

        List<Account> accounts = jdbcTemplate.query(getAccountByAccountId, new AccountRowMapper(), juridicalPeoples.get(0).getJuridicalPersonId());

        jdbcTemplate.update(createAccountRequisitesForPerson, accounts.get(0).getAccountId(), null, (int) (Math.random() * 100000));

        log.info("Create Person with Surname: " + surname + ", Name: " + name + ", Patronymic: " + patronymic +
                ", OrganizationName: " +  organizationName + ", Login: " + juridicalPersonName);

        return true;
    }

    @Override
    public boolean deleteJuridicalPerson(String personName) {
        List<JuridicalPerson> peoples = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonRowMapper(), personName);
        List<Account> accounts = jdbcTemplate.query(getAccountByPersonId, new AccountRowMapper(), peoples.get(0).getJuridicalPersonId());

        jdbcTemplate.update(deleteAccountRequisites, accounts.get(0).getAccountId());
        jdbcTemplate.update(deleteAccount, peoples.get(0).getJuridicalPersonId());
        jdbcTemplate.update(deletePerson, personName);

        log.info("Delete Person with Surname: " + peoples.get(0).getSurname() + ", " +
                "Name: " + peoples.get(0).getName() + ", Patronymic: " + peoples.get(0).getPatronymic() +
                ", OrganizationName" + peoples.get(0).getOrganizationName() + ", Login: " + peoples.get(0).getJuridicalPersonName());

        return true;
    }

    @Override
    public boolean personChange(String surname, String name, String patronymic, String organizationName, String juridicalPersonName) {
        int personId = jdbcTemplate.query(getPersonIdByPerson, new JuridicalPersonCreateRowMapper(), juridicalPersonName).get(0).getJuridicalPersonId();
        jdbcTemplate.update(changePerson, surname, name, patronymic, organizationName, juridicalPersonName, personId);
        return true;
    }

    @Override
    public boolean addSum(String personName, int sum) {
        List<JuridicalPerson> peoples = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonRowMapper(), personName);

        jdbcTemplate.update(addSum, sum, peoples.get(0).getJuridicalPersonId());
        transferStorage.setTransferInfo(personName, personName, sum);

        log.info("Person " + personName + " add " + sum + " to your account");

        return true;
    }

    @Override
    public boolean transfer(String leftPerson, String rightPerson, int sum) {
        List<JuridicalPerson> peopleLeft = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonRowMapper(), leftPerson);
        List<JuridicalPerson> peopleRight = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonRowMapper(), rightPerson);

        jdbcTemplate.update(transferPersonLeft, sum, peopleLeft.get(0).getJuridicalPersonId());
        jdbcTemplate.update(transferPersonRight, sum, peopleRight.get(0).getJuridicalPersonId());
        transferStorage.setTransferInfo(leftPerson, rightPerson, sum);

        log.info("Person " + leftPerson + " transfer " + sum + " to " + rightPerson + " person");

        return true;
    }

    @Override
    public List<JuridicalPerson> getIdByPersonName(String personName) {
        String getIdByPersonName = "Select * from JuridicalPerson Where PersonName Like ?";
        return jdbcTemplate.query(getIdByPersonName, new JuridicalPersonRowMapper(), personName);
    }

    @Override
    public List<JuridicalPerson> getPersonByPaymentName(String paymentName) {
        String getPayment = "Select * from Payment Where Payment.Name Like ?";
        String getPersonByClientName = "Select * From JuridicalPerson Where JuridicalPerson.PersonId = ?";
        List<Payment> payments = jdbcTemplate.query(getPayment, new PaymentIdRowMapper(), paymentName);
        return jdbcTemplate.query(getPersonByClientName, new JuridicalPersonCreateRowMapper(), payments.get(0).getPersonId());
    }

}
