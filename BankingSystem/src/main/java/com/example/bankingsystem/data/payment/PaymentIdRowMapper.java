package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.domain.model.Payment;
import com.example.bankingsystem.domain.model.Purpose;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentIdRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment paymentModel = new Payment();
        paymentModel.setPaymentId(rs.getInt("PaymentId"));
        return paymentModel;
    }
}
