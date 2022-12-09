package com.example.bankingsystem.data.client;

import com.example.bankingsystem.data.account.AccountRowMapper;
import com.example.bankingsystem.data.accountRequisites.AccountRequisitesRowMapper;
import com.example.bankingsystem.data.transfer.TransferStorage;
import com.example.bankingsystem.domain.model.Account;
import com.example.bankingsystem.domain.model.AccountRequisites;
import com.example.bankingsystem.domain.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


@Transactional
@Repository
public class ClientStorageDB implements ClientStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TransferStorage transferStorage;

    @Override
    public List<Client> getAllClient (String pattern) {
        StringBuilder getAllClient = new StringBuilder("SELECT * from Client join Account on Client.ClientId = Account.ClientId ");
        List<Client> clients;

        if (!Objects.equals(pattern, "admin")) {
            getAllClient.append(" WHERE Client.ClientName LIKE ?");
            clients = jdbcTemplate.query(getAllClient.toString(), new ClientRowMapper(), pattern);
        } else {
            clients = jdbcTemplate.query(getAllClient.toString(), new ClientRowMapper());
        }

        return clients;
    }

    @Override
    public boolean createClient(int branchId, int roleId, String surname, String name, String patronymic, String clientName, String password, int sum) {
        String createClient = "INSERT into Client VALUES (?, ?, ?, ?, ?, ?, ?)";
        String getClientByClientName = "Select * from Client Where Client.ClientName LIKE ?";
        String createAccountForClient = "Insert into Account Values (?, ?, ?)";
        String createAccountRequisitesForClient = "Insert into AccountRequisites Values (?, ?, ?)";
        String getAccountByAccountId = "Select * From Account Where Account.ClientId = ?";

        jdbcTemplate.update(createClient, branchId, roleId, surname, name, patronymic, clientName, password);

        List<Client> clients = jdbcTemplate.query(getClientByClientName, new ClientCreateRowMapper(), clientName);

        jdbcTemplate.update(createAccountForClient, null, clients.get(0).getClientId(), sum);

        List<Account> accounts = jdbcTemplate.query(getAccountByAccountId, new AccountRowMapper(), clients.get(0).getClientId());

        jdbcTemplate.update(createAccountRequisitesForClient, accounts.get(0).getAccountId(), (int) (Math.random() * 100000), null);

        return true;
    }

    @Override
    public boolean deleteClient(String clientName) {
        String deleteClient = "Delete from Client Where Client.ClientName Like ?";
        String deleteAccount = "Delete from Account Where Account.ClientId = ?";
        String deleteAccountRequisites = "Delete from AccountRequisites Where AccountRequisites.AccountId = ?";
        String getClientByClientName = "Select * from Client Where Client.ClientName LIKE ?";
        String getAccountByClientId = "Select * from Account Where Account.ClientId = ?";

        List<Client> clients = jdbcTemplate.query(getClientByClientName, new ClientRowMapper(), clientName);
        List<Account> accounts = jdbcTemplate.query(getAccountByClientId, new AccountRowMapper(), clients.get(0).getClientId());

        jdbcTemplate.update(deleteAccountRequisites, accounts.get(0).getAccountId());
        jdbcTemplate.update(deleteAccount, clients.get(0).getClientId());
        jdbcTemplate.update(deleteClient, clientName);

        return true;
    }

    @Override
    public boolean addSum(String clientName, int sum) {
        String addSum = "Update Account Set Account.Sum = Account.Sum + ? Where Account.ClientId = ?";
        String getClientByClientName = "Select * from Client join Account on Client.ClientId = Account.ClientId Where Client.ClientName Like ?";
        List<Client> clients = jdbcTemplate.query(getClientByClientName, new ClientRowMapper(), clientName);

        System.out.println("ClientId = " + clients.get(0).getClientId());
        System.out.println("Sum = " + sum);

        jdbcTemplate.update(addSum, sum, clients.get(0).getClientId());
        transferStorage.setTransferInfo(clientName, clientName, sum);

        return true;
    }

    @Override
    public boolean transfer(String leftClientName, String rightClientName, int sum) {
        String transferClientLeft = "Update Account Set Account.Sum = Account.Sum - ? Where Account.ClientId = ?";
        String transferClientRight = "Update Account Set Account.Sum = Account.Sum + ? Where Account.ClientId = ?";
        String getClientByClientName = "Select * from Client Join Account on Client.ClientId = Account.ClientId Where Client.ClientName Like ?";

        List<Client> clientLeft = jdbcTemplate.query(getClientByClientName, new ClientRowMapper(), leftClientName);
        List<Client> clientRight = jdbcTemplate.query(getClientByClientName, new ClientRowMapper(), rightClientName);

        jdbcTemplate.update(transferClientLeft, sum, clientLeft.get(0).getClientId());
        jdbcTemplate.update(transferClientRight, sum, clientRight.get(0).getClientId());
        transferStorage.setTransferInfo(leftClientName, rightClientName, sum);

        return true;
    }

    @Override
    public List<Client> findByClientId(int clientId) {
        String findByClientId = "SELECT Name from Role where Role.RoleId = ?";
        return jdbcTemplate.query(findByClientId, new ClientRowMapper(), clientId);
    }

    @Override
    public List<Client> transferInfo(String clientName) {
        return null;
    }
}
