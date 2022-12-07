package com.example.bankingsystem.data.purpose;

import com.example.bankingsystem.domain.model.Payment;
import com.example.bankingsystem.domain.model.Purpose;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PurposeRowMapper implements RowMapper<Purpose> {
    @Override
    public Purpose mapRow(ResultSet rs, int rowNum) throws SQLException {
        Purpose purpose = new Purpose();

        purpose.setPurposeId(rs.getInt("PurposeId"));
        purpose.setPaymentId(rs.getInt("PaymentId"));
        purpose.setPurposeName(rs.getString("PurposeName"));

        return purpose;
    }
}
