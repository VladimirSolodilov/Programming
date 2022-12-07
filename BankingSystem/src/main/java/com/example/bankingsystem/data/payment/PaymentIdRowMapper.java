package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.Payment;
import com.example.bankingsystem.domain.model.Purpose;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentIdRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();

        payment.setPaymentId(rs.getInt("PaymentId"));
        payment.setPersonId(rs.getInt("PersonId"));
        payment.setName(rs.getString("Name"));
        payment.setData(rs.getDate("Date"));
        payment.setSum(rs.getInt("Sum"));

        return payment;
    }
}
