package com.example.bankingsystem.data.juridicalPerson;

import com.example.bankingsystem.data.account.AccountRowMapper;
import com.example.bankingsystem.data.client.ClientRowMapper;
import com.example.bankingsystem.data.transfer.TransferStorage;
import com.example.bankingsystem.domain.model.Account;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class JuridicalPersonStorageDB implements JuridicalPersonStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransferStorage transferStorage;

    @Override
    public List<JuridicalPerson> getAllPerson(String pattern) {
        StringBuilder getAllPerson = new StringBuilder("SELECT * from JuridicalPerson Join Account on JuridicalPerson.PersonId = Account.PersonId ");
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
        String createPerson = "INSERT into JuridicalPerson VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String getPersonByPersonName = "Select * from JuridicalPerson Where JuridicalPerson.PersonName LIKE ?";
        String createAccountForPerson = "Insert into Account Values (?, ?, ?)";
        String createAccountRequisitesForPerson = "Insert into AccountRequisites Values (?, ?, ?)";
        String getAccountByAccountId = "Select * From Account Where Account.PersonId = ?";

        jdbcTemplate.update(createPerson, branchId, roleId, surname, name, patronymic, organizationName, juridicalPersonName, password);

        List<JuridicalPerson> juridicalPeoples = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonCreateRowMapper(), juridicalPersonName);

        jdbcTemplate.update(createAccountForPerson, juridicalPeoples.get(0).getJuridicalPersonId(), null, sum);

        List<Account> accounts = jdbcTemplate.query(getAccountByAccountId, new AccountRowMapper(), juridicalPeoples.get(0).getJuridicalPersonId());

        jdbcTemplate.update(createAccountRequisitesForPerson, accounts.get(0).getAccountId(), null, (int) (Math.random() * 100000));

        return true;
    }

    @Override
    public boolean deleteJuridicalPerson(String personName) {
        String deletePerson = "Delete from JuridicalPerson Where JuridicalPerson.PersonName Like ?";
        String deleteAccount = "Delete from Account Where Account.ClientId = ?";
        String deleteAccountRequisites = "Delete from AccountRequisites Where AccountRequisites.AccountId = ?";
        String getPersonByPersonName = "Select * from JuridicalPerson Where JuridicalPerson.PersonName LIKE ?";
        String getAccountByPersonId = "Select * from Account Where Account.PersonId = ?";

        List<JuridicalPerson> peoples = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonRowMapper(), personName);
        List<Account> accounts = jdbcTemplate.query(getAccountByPersonId, new AccountRowMapper(), peoples.get(0).getJuridicalPersonId());

        jdbcTemplate.update(deleteAccountRequisites, accounts.get(0).getAccountId());
        jdbcTemplate.update(deleteAccount, peoples.get(0).getJuridicalPersonId());
        jdbcTemplate.update(deletePerson, personName);

        return true;
    }

    @Override
    public boolean addSum(String personName, int sum) {
        String addSum = "Update Account Set Account.Sum = Account.Sum + ? Where Account.PersonId = ?";
        String getPersonByPersonName = "Select * from JuridicalPerson Join Account on JuridicalPerson.PersonId = Account.PersonId Where JuridicalPerson.PersonName Like ?";
        List<JuridicalPerson> peoples = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonRowMapper(), personName);

        jdbcTemplate.update(addSum, sum, peoples.get(0).getJuridicalPersonId());
        transferStorage.setTransferInfo(personName, personName, sum);

        return true;
    }

    @Override
    public boolean transfer(String leftPerson, String rightPerson, int sum) {
        String transferPersonLeft = "Update Account Set Account.Sum = Account.Sum - ? Where Account.ClientId = ?";
        String transferPersonRight = "Update Account Set Account.Sum = Account.Sum + ? Where Account.ClientId = ?";
        String getPersonByPersonName = "Select * from JuridicalPerson Join Account on JuridicalPerson.PersonId = Account.PersonId Where JuridicalPerson.PersonName Like ?";

        List<JuridicalPerson> peopleLeft = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonRowMapper(), leftPerson);
        List<JuridicalPerson> peopleRight = jdbcTemplate.query(getPersonByPersonName, new JuridicalPersonRowMapper(), rightPerson);

        jdbcTemplate.update(transferPersonLeft, sum, peopleLeft.get(0).getJuridicalPersonId());
        jdbcTemplate.update(transferPersonRight, sum, peopleRight.get(0).getJuridicalPersonId());
        transferStorage.setTransferInfo(leftPerson, rightPerson, sum);

        return true;
    }

    @Override
    public List<JuridicalPerson> getIdByPersonName(String personName) {
        String getIdByPersonName = "Select * from JuridicalPerson Where PersonName Like ?";
        return jdbcTemplate.query(getIdByPersonName, new JuridicalPersonRowMapper(), personName);
    }

}
