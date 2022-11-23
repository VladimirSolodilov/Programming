package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.domain.model.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment paymentModel = new Payment();

        paymentModel.setPaymentId(rs.getInt("PaymentId"));
        paymentModel.setAccountId(rs.getInt("AccountId"));
        paymentModel.setData(rs.getDate("Date"));
        paymentModel.setSum(rs.getInt("Sum"));

        return paymentModel;
    }
}
