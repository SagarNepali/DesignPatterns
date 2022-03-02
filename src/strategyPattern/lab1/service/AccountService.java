package strategyPattern.lab1.service;

import strategyPattern.lab1.model.Account;
import strategyPattern.lab1.utils.AccountType;

import java.util.Collection;

public interface AccountService {
    Account createAccount(String accountNumber, String customerName , AccountType accountType);
    Account getAccount(String accountNumber);
    Collection<Account> getAllAccounts();
    void deposit (String accountNumber, double amount);
    void withdraw (String accountNumber, double amount);
    void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description);
    void addInterest();
}
