package com.example.bankingsystem.data.client;

import com.example.bankingsystem.data.account.AccountRowMapper;
import com.example.bankingsystem.data.payment.PaymentIdRowMapper;
import com.example.bankingsystem.data.transfer.TransferStorage;
import com.example.bankingsystem.domain.model.Account;
import com.example.bankingsystem.domain.model.Client;
import com.example.bankingsystem.domain.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Repository
@Slf4j
public class ClientStorageDB implements ClientStorage {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TransferStorage transferStorage;
    String createClient = "INSERT into Client VALUES (?, ?, ?, ?, ?, ?, ?)";
    String createAccountForClient = "Insert into Account Values (?, ?, ?)";
    String createAccountRequisitesForClient = "Insert into AccountRequisites Values (?, ?, ?)";
    String getAccountByAccountId = "Select * From Account Where Account.ClientId = ?";
    String getPaymentByClientId = "Select * from Payment Where Payment.ClientId = ?";
    String deleteClient = "Delete from Client Where Client.ClientName Like ?";
    String deleteAccount = "Delete from Account Where Account.ClientId = ?";
    String deletePurpose = "Delete from Purpose Where Purpose.PaymentId = ?";
    String deletePayment = "Delete from Payment Where Payment.PaymentId = ?";
    String deleteAccountRequisites = "Delete from AccountRequisites Where AccountRequisites.AccountId = ?";
    String getAccountByClientId = "Select * from Account Where Account.ClientId = ?";
    String addSum = "Update Account Set Account.Sum = Account.Sum + ? Where Account.ClientId = ?";
    String getClientByClientName = "SELECT * from Client join Account on Client.ClientId = Account.ClientId join AccountRequisites on Account.AccountId = AccountRequisites.AccountId Where Client.ClientName Like ?";
    String transferClientLeft = "Update Account Set Account.Sum = Account.Sum - ? Where Account.ClientId = ?";
    String transferClientRight = "Update Account Set Account.Sum = Account.Sum + ? Where Account.ClientId = ?";
    String getClientIdByClient = "Select * From Client Where Client.ClientName = ?";
    String changeClient = "Update Client Set Surname = ?, Name = ?, Patronymic = ?, ClientName = ? Where Client.ClientId = ?";
    @Override
    public List<Client> getAllClient (String pattern) {
        StringBuilder getAllClient = new StringBuilder("SELECT * from Client join Account on Client.ClientId = Account.ClientId " +
                "join AccountRequisites on Account.AccountId = AccountRequisites.AccountId join Branch on Client.BranchId = Branch.BranchId");
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
        String getClientByClientName = "Select * from Client Where Client.ClientName LIKE ?";

        jdbcTemplate.update(createClient, branchId, roleId, surname, name, patronymic, clientName, password);

        List<Client> clients = jdbcTemplate.query(getClientByClientName, new ClientCreateRowMapper(), clientName);

        jdbcTemplate.update(createAccountForClient, null, clients.get(0).getClientId(), sum);

        List<Account> accounts = jdbcTemplate.query(getAccountByAccountId, new AccountRowMapper(), clients.get(0).getClientId());
        jdbcTemplate.update(createAccountRequisitesForClient, accounts.get(0).getAccountId(), (int) (Math.random() * 100000), null);

        log.info("Create Client with Surname: " + surname + ", Name: " + name + ", Patronymic: " + patronymic + ", Login: " + clientName);

        return true;
    }

    @Override
    public boolean deleteClient(String clientName) {
        List<Client> clients = jdbcTemplate.query(getClientByClientName, new ClientCreateRowMapper(), clientName);
        List<Account> accounts = jdbcTemplate.query(getAccountByClientId, new AccountRowMapper(), clients.get(0).getClientId());
        List<Payment> payments = jdbcTemplate.query(getPaymentByClientId, new PaymentIdRowMapper(), clients.get(0).getClientId());

        jdbcTemplate.update(deleteAccountRequisites, accounts.get(0).getAccountId());
        jdbcTemplate.update(deleteAccount, clients.get(0).getClientId());

        if (payments.size() != 0) {
            jdbcTemplate.update(deletePurpose, payments.get(0).getPaymentId());
            jdbcTemplate.update(deletePayment, payments.get(0).getPaymentId());
        }

        jdbcTemplate.update(deleteClient, clientName);

        log.info("Delete Client with Surname: " + clients.get(0).getSurname() + ", " +
                "Name: " + clients.get(0).getName() + ", Patronymic: " + clients.get(0).getPatronymic() + ", Login: " + clients.get(0).getClientName());

        return true;
    }

    @Override
    public boolean addSum(String clientName, int sum) {
        List<Client> clients = jdbcTemplate.query(getClientByClientName, new ClientRowMapper(), clientName);

        jdbcTemplate.update(addSum, sum, clients.get(0).getClientId());
        transferStorage.setTransferInfo(clientName, clientName, sum);

        log.info("Client " + clientName + " add " + sum + " to your account");

        return true;
    }

    @Override
    public boolean transfer(String leftClientName, String rightClientName, int sum) {
        List<Client> clientLeft = jdbcTemplate.query(getClientByClientName, new ClientRowMapper(), leftClientName);
        List<Client> clientRight = jdbcTemplate.query(getClientByClientName, new ClientRowMapper(), rightClientName);

        jdbcTemplate.update(transferClientLeft, sum, clientLeft.get(0).getClientId());
        jdbcTemplate.update(transferClientRight, sum, clientRight.get(0).getClientId());
        transferStorage.setTransferInfo(leftClientName, rightClientName, sum);

        log.info("Client " + leftClientName + " transfer " + sum + " to " + rightClientName + " client");

        return true;
    }

    @Override
    public boolean changeClient(String surname, String name, String patronumic, String clientName) {
        int clientId = jdbcTemplate.query(getClientIdByClient, new ClientCreateRowMapper(), clientName).get(0).getClientId();
        jdbcTemplate.update(changeClient, surname, name, patronumic, clientName, clientId);
        return true;
    }
}
