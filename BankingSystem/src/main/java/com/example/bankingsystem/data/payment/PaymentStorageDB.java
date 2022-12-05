package com.example.bankingsystem.data.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class PaymentStorageDB implements PaymentStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createPayment(String personName, String purposeName, Date purposeDate, int purposeSum) {
        String sqlQuery = "INSERT Payment VALUES(?, ?, ?)";
        String sqlQuery2 = "INSERT Purpose VALUES(?, ?)";

        jdbcTemplate.update(sqlQuery, new PaymentRowMapper(), purposeName, purposeDate, purposeSum);


        return 0;
    }
}
