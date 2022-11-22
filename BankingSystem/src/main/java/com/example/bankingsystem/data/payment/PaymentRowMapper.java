package com.example.bankingsystem.data.payment;

import com.example.bankingsystem.domain.model.Payment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRowMapper implements RowMapper<Payment> {
    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment paymentModel = new Payment();

        paymentModel.setPaymentId(rs.getInt("payment_id"));
        paymentModel.setPurposeId(rs.getInt("payment_purposeId"));
        paymentModel.setData(rs.getDate("payment_date"));

        return paymentModel;
    }
}
