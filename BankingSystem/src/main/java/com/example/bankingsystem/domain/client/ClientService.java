package com.example.bankingsystem.domain.client;

import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.Transfer;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface ClientService extends UserDetailsService {

    List<Client> getClientList(String clientName);
    List<Client> getUserByClientName(String clientName);

    int setClientList(int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum);
    int deleteClientList(String surname);

    int addSum(String clientName, int sum);
    int transfer(String leftClientName, String rightClientName, int sum);

    List<Transfer> transferInfo(String userName);


    boolean saveClient(Client client);
}
