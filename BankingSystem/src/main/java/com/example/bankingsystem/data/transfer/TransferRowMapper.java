package com.example.bankingsystem.data.transfer;

import com.example.bankingsystem.domain.model.Role;
import com.example.bankingsystem.domain.model.Transfer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransferRowMapper implements RowMapper<Transfer> {
    @Override
    public Transfer mapRow(ResultSet resultSet, int i) throws SQLException {
        Transfer transfer = new Transfer();
        transfer.setLeftUser(resultSet.getString("LeftUser"));
        transfer.setRightUser(resultSet.getString("RightUser"));
        transfer.setSum(resultSet.getInt("Sum"));
        return transfer;
    }
}
