package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.Payment;
import com.example.bankingsystem.domain.model.Purpose;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        Purpose purpose = new Purpose();
        Client client = new Client();

        payment.setPaymentId(rs.getInt("PaymentId"));
        payment.setPersonId(rs.getInt("PersonId"));
        payment.setName(rs.getString("Name"));
        payment.setData(rs.getDate("Date"));
        payment.setSum(rs.getInt("Sum"));

        payment.setPurpose(purpose);
        purpose.setPurposeName(rs.getString("PurposeName"));

        payment.setClient(client);
        client.setClientName(rs.getString("ClientName"));

        return payment;
    }
}
