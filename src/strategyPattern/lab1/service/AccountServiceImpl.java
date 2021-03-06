package strategyPattern.lab1.service;

import strategyPattern.lab1.DAO.AccountDAO;
import strategyPattern.lab1.DAO.AccountDAOImpl;
import strategyPattern.lab1.model.Account;
import strategyPattern.lab1.model.CheckingInterest;
import strategyPattern.lab1.model.Customer;
import strategyPattern.lab1.model.SavingInterest;
import strategyPattern.lab1.utils.AccountType;

import java.util.Collection;

public class AccountServiceImpl implements AccountService {
	private AccountDAO accountDAO;
	
	public AccountServiceImpl(){
		accountDAO = new AccountDAOImpl();
	}

	public Account createAccount(String accountNumber, String customerName, AccountType type) {
		Account account = new Account(accountNumber,type);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		
		accountDAO.saveAccount(account);
		
		return account;
	}

	public void deposit(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		
		accountDAO.updateAccount(account);
	}

	public Account getAccount(String accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(String accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
	}



	public void transferFunds(String fromAccountNumber, String toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
	}

	@Override
	public void addInterest() {
		Collection<Account> accounts = getAllAccounts();
		accounts.forEach(a ->{
			if(a.getAccountType().equals(AccountType.SAVING)){
				a.setInterestBehavior(new SavingInterest());
			}else{
				a.setInterestBehavior(new CheckingInterest());
			}

			a.addInterest(a.getBalance());
		});
	}
}
