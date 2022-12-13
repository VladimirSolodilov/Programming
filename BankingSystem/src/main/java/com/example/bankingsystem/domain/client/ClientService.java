package com.example.bankingsystem.domain.client;

import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.Transfer;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService {
    List<Client> getClientList(String clientName);
    boolean createClient(int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    boolean deleteClient(String clientName);
    boolean addSum(String clientName, int sum);
    boolean transfer(String leftClientName, String rightClientName, int sum);
    boolean clientChange(String surname, String name, String patronymic, String clientName);
    List<Transfer> transferInfo(String userName);
}
