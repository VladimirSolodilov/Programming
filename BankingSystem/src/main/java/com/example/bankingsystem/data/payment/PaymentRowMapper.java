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
        Payment paymentModel = new Payment();
        Purpose purpose = new Purpose();
        Client client = new Client();

        paymentModel.setName(rs.getString("Name"));
        paymentModel.setData(rs.getDate("Date"));
        paymentModel.setSum(rs.getInt("Sum"));

        paymentModel.setPurpose(purpose);
        purpose.setPurposeName(rs.getString("PurposeName"));

        paymentModel.setClient(client);
        client.setClientName(rs.getString("ClientName"));

        return paymentModel;
    }
}
