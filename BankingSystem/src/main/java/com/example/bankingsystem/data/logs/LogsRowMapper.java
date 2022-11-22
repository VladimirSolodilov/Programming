package com.example.bankingsystem.data.logs;

import com.example.bankingsystem.domain.model.Logs;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogsRowMapper implements RowMapper<Logs> {

    @Override
    public Logs mapRow(ResultSet rs, int rowNum) throws SQLException {
        Logs logsModel = new Logs();

        logsModel.setLogId(rs.getInt("logs_id"));
        logsModel.setLog(rs.getString("logs_log"));

        return logsModel;
    }
}
