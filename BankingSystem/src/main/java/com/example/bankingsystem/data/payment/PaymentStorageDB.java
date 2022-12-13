package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.data.client.ClientRowMapper;
import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonRowMapper;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class PaymentStorageDB implements PaymentStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private final Date date = new Date();
    String createPayment = "INSERT Payment VALUES(?, ?, ?, ?, ?)";
    String createPurpose = "INSERT Purpose VALUES(?, ?)";
    String getPaymentByName = "SELECT * from Payment WHERE Name Like ?";
    String getClientByClientName = "SELECT * from Client Join Account on Client.ClientId = Account.ClientId join AccountRequisites on Account.AccountId = AccountRequisites.AccountId join Branch on Branch.BranchId = Client.BranchId Where Client.ClientName Like ?";
    String getPersonByName = "Select * from JuridicalPerson Join Account on JuridicalPerson.PersonId = Account.PersonId join AccountRequisites on Account.AccountId = AccountRequisites.AccountId join Branch on Branch.BranchId = JuridicalPerson.BranchId Where JuridicalPerson.PersonName Like ?";
    String getPaymentByPersonId = "select * from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId join Client on Payment.ClientId = Client.ClientId where Payment.PersonId = ?";
    String getPayment = "select * from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId join Client on Payment.ClientId = Client.ClientId Where Payment.ClientId = ?";
    String getClientByName = "SELECT * from Client join Account on Client.ClientId = Account.ClientId join AccountRequisites on Account.AccountId = AccountRequisites.AccountId join Branch on Branch.BranchId = Client.BranchId Where ClientName Like ?";
    String updatePersonSum = "Update Account Set Account.Sum = Account.Sum + ? Where Account.PersonId = ?";
    String updateClientSum = "Update Account Set Account.Sum = Account.Sum - ? Where Account.ClientId = ?";
    String deletePurpose = "Delete from Purpose Where PurposeName Like ?";
    String deletePayment = "Delete from Payment Where Name Like ?";

    @Override
    public boolean createPayment(int personId, String clientName,String paymentName, int paymentSum, String purposeName) {
        List<Client> clients = jdbcTemplate.query(getClientByClientName, new ClientRowMapper(), clientName);

        jdbcTemplate.update(createPayment, personId, clients.get(0).getClientId(), paymentName, date, paymentSum);

        List<Payment> payments = jdbcTemplate.query(getPaymentByName, new PaymentIdRowMapper(), paymentName);

        jdbcTemplate.update(createPurpose, payments.get(0).getPaymentId(), purposeName);

        return true;
    }

    @Override
    public List<Payment> getPaymentList(String personName, String clientName) {
        List<JuridicalPerson> juridicalPersons;
        List<Client> clients;

        if (Objects.equals(personName, null)) {
            clients = jdbcTemplate.query(getClientByName, new ClientRowMapper(), clientName);
            return jdbcTemplate.query(getPayment, new PaymentRowMapper(), clients.get(0).getClientId());
        } else {
            juridicalPersons = jdbcTemplate.query(getPersonByName, new JuridicalPersonRowMapper(), personName);
            return jdbcTemplate.query(getPaymentByPersonId, new PaymentRowMapper(), juridicalPersons.get(0).getJuridicalPersonId());
        }
    }

    @Override
    public boolean doPayment(String clientName, String personName, String paymentName, int sum, String purposeName) {
        String getPayment = "Select * from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId where PurposeName Like ?";

        List<JuridicalPerson> juridicalPeoples = jdbcTemplate.query(getPersonByName, new JuridicalPersonRowMapper(), personName);
        List<Client> clients = jdbcTemplate.query(getClientByName, new ClientRowMapper(), clientName);

        jdbcTemplate.update(updateClientSum, sum, clients.get(0).getClientId());
        jdbcTemplate.update(updatePersonSum, sum, juridicalPeoples.get(0).getJuridicalPersonId());
        jdbcTemplate.update(deletePurpose, purposeName);

        List<Payment> payments1 = jdbcTemplate.query(getPayment, new PaymentRowMapper(), purposeName);

        if (payments1.size() == 0) {
            jdbcTemplate.update(deletePayment, paymentName);
        }

        return true;
    }
}
