package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.data.client.ClientIdRowMapper;
import com.example.bankingsystem.data.client.ClientRowMapper;
import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonRowMapper;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class PaymentStorageDB implements PaymentStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Date date = new Date(2022);

    @Override
    public int createPayment(int personId, String clientName,String paymentName, int paymentSum, String purposeName) {
        String sqlQuery = "INSERT Payment VALUES(?, ?, ?, ?, ?)";
        String sqlQuery2 = "INSERT Purpose VALUES(?, ?)";
        String sqlQuery3 = "SELECT PaymentId from Payment WHERE Name Like ?";
        String sqlQuery4 = "SELECT * from Client Where Client.ClientName Like ?";

        List<Client> clientList = jdbcTemplate.query(sqlQuery4, new ClientRowMapper(), clientName);




        jdbcTemplate.update(sqlQuery, personId, clientList.get(0).getClientId(), paymentName, date.toString(), paymentSum);

        List<Payment> paymentList = jdbcTemplate.query(sqlQuery3, new PaymentIdRowMapper(), paymentName);

        jdbcTemplate.update(sqlQuery2, paymentList.get(0).getPaymentId(), purposeName);

        return 1;
    }

    @Override
    public List<Payment> getPaymentList(String personName, String clientName) {
        String sqlQuery = "SELECT * from JuridicalPerson Where PersonName Like ?";
        String sqlQuery4 = "SELECT ClientId from Client";
        String sqlQuery1 = "SELECT * from JuridicalPerson";
        String sqlQuery2 = "select Payment.Name, Payment.Date, Payment.Sum, Client.ClientName, Purpose.PurposeName from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId join Client on Payment.ClientId = Client.ClientId \n" +
                "where Payment.PersonId = ?";

        //String sqlQuery2 = "select Name, Date, Sum, PurposeName from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId where Payment.PersonId = ?";
        //String sqlQuery3 = "select Name, Date, Sum, PurposeName from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId";
        String sqlQuery3 = "select Payment.Name, Payment.Date, Payment.Sum, Client.ClientName, Purpose.PurposeName from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId join Client on Payment.ClientId = Client.ClientId Where Payment.ClientId = ?";
        String sqlQuery5 = "Select * from Client Where ClientName Like ?";

        List<JuridicalPerson> juridicalPersonList;
        List<Client> clientList = null;

        if (Objects.equals(personName, null)) {
            clientList = jdbcTemplate.query(sqlQuery5, new ClientRowMapper(), clientName);
            return jdbcTemplate.query(sqlQuery3, new PaymentRowMapper(), clientList.get(0).getClientId());
        } else {
            juridicalPersonList = jdbcTemplate.query(sqlQuery, new JuridicalPersonRowMapper(), personName);
            return jdbcTemplate.query(sqlQuery2, new PaymentRowMapper(), juridicalPersonList.get(0).getJuridicalPersonId());

            /*for (Client client : clientList) {
                return jdbcTemplate.query(sqlQuery2, new PaymentRowMapper(), juridicalPersonList.get(0).getJuridicalPersonId(), client.getClientId());
            }*/
            //return jdbcTemplate.query(sqlQuery2, new PaymentRowMapper(), juridicalPersonList.get(0).getJuridicalPersonId());
        }
    }

    @Override
    public int doPayment(String clientName, String personName, String paymentName, int sum, String purposeName) {
        String sqlQuery0 = "Select PersonId from Payment where Payment.Name LIKE ?";

        String sqlQuery = "Update JuridicalPerson Set JuridicalPerson.Sum = JuridicalPerson.Sum + ? Where JuridicalPerson.PersonId = ?";
        String sqlQuery1 = "Update Client set Client.Sum = Client.Sum - ? Where Client.ClientName Like ?";
        String sqlQuery2 = "Delete from Purpose Where PurposeName Like ?";
        String sqlQuery3 = "Select Payment.Name, Payment.Date, Payment.Sum, Purpose.PurposeName from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId where PurposeName Like ?";
        String sqlQuery4 = "Delete from Payment Where Name Like ?";

        List<Payment> paymentList = jdbcTemplate.query(sqlQuery0, new PaymentPersonIdRowMapper(), paymentName);

        System.out.println("PersonId = " + paymentList.get(0).getPersonId());

        jdbcTemplate.update(sqlQuery, sum, paymentList.get(0).getPersonId());
        jdbcTemplate.update(sqlQuery1, sum, clientName);
        jdbcTemplate.update(sqlQuery2, purposeName);

        List<Payment> paymentList1 = jdbcTemplate.query(sqlQuery3, new PaymentRowMapper(), purposeName);

        if (paymentList1.size() == 0) {
            jdbcTemplate.update(sqlQuery4, paymentName);
        }

        return 1;
    }
}
