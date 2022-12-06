package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.data.juridicalPerson.JuridicalPersonRowMapper;
import com.example.bankingsystem.data.purpose.PurposeRowMapper;
import com.example.bankingsystem.domain.model.JuridicalPerson;
import com.example.bankingsystem.domain.model.Payment;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class PaymentStorageDB implements PaymentStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final Date date = new Date(2022);

    @Override
    public int createPayment(int personId, String paymentName, int paymentSum, String purposeName) {
        String sqlQuery = "INSERT Payment VALUES(?, ?, ?, ?)";
        String sqlQuery3 = "SELECT PaymentId from Payment WHERE Name Like ?";
        String sqlQuery2 = "INSERT Purpose VALUES(?, ?)";

        System.out.println(date.toLocalDate() + " - " + personId);

        jdbcTemplate.update(sqlQuery, personId, paymentName, date.toString(), paymentSum);

        List<Payment> paymentList = jdbcTemplate.query(sqlQuery3, new PaymentIdRowMapper(), paymentName);

        jdbcTemplate.update(sqlQuery2, paymentList.get(0).getPaymentId(), purposeName);

        return 1;
    }

    @Override
    public List<Payment> getPaymentList(String personName) {
        String sqlQuery = "SELECT * from JuridicalPerson Where PersonName Like ?";
        String sqlQuery1 = "SELECT * from JuridicalPerson";
        String sqlQuery2 = "select Name, Date, Sum, PurposeName from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId where Payment.PersonId = ?";
        String sqlQuery3 = "select Name, Date, Sum, PurposeName from Payment join Purpose on Payment.PaymentId = Purpose.PaymentId";
        List<JuridicalPerson> juridicalPersonList;

        if (Objects.equals(personName, null)) {
            juridicalPersonList = jdbcTemplate.query(sqlQuery1, new JuridicalPersonRowMapper());
            return jdbcTemplate.query(sqlQuery3, new PaymentRowMapper());
        } else {
            juridicalPersonList = jdbcTemplate.query(sqlQuery, new JuridicalPersonRowMapper(), personName);
            return jdbcTemplate.query(sqlQuery2, new PaymentRowMapper(), juridicalPersonList.get(0).getJuridicalPersonId());
        }
    }
}
