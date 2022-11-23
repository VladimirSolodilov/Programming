package com.example.bankingsystem.domain.logs;

import com.example.bankingsystem.data.logs.LogsStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogsServiceDomain implements LogsService {

    @Autowired
    private LogsStorage logsStorage;

    @Override
    public int setLogs() {
        return logsStorage.setLog();
    }
}
