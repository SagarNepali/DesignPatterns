package strategyPattern.lab1.model;

import strategyPattern.lab1.utils.AccountType;
import strategyPattern.lab1.utils.InterestBehavior;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Account {
	private Customer customer;

	private AccountType accountType;

	private InterestBehavior interestBehavior;

	private String accountNumber;

	private List<AccountEntry> entryList = new ArrayList<AccountEntry>();

	public Account(String accountNumber, AccountType accountType) {
		this.accountNumber = accountNumber;
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		double balance = 0;
		for (AccountEntry entry : entryList) {
			balance += entry.getAmount();
		}
		return balance;
	}

	public void deposit(double amount) {
		AccountEntry entry = new AccountEntry(amount, "deposit", "", "");
		entryList.add(entry);
	}

	public void withdraw(double amount) {
		AccountEntry entry = new AccountEntry(-amount, "withdraw", "", "");
		entryList.add(entry);
	}

	private void addEntry(AccountEntry entry) {
		entryList.add(entry);
	}

	public void transferFunds(Account toAccount, double amount, String description) {
		AccountEntry fromEntry = new AccountEntry(-amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		AccountEntry toEntry = new AccountEntry(amount, description, toAccount.getAccountNumber(),
				toAccount.getCustomer().getName());
		
		entryList.add(fromEntry);
		
		toAccount.addEntry(toEntry);
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<AccountEntry> getEntryList() {
		return entryList;
	}

	public void setInterestBehavior(InterestBehavior interestBehavior){
		this.interestBehavior = interestBehavior;
	}

	public InterestBehavior getInterestBehavior(){
		return interestBehavior;
	}

	public void addInterest(double balance) {
		double bal = interestBehavior.calculateInterest(balance);
		AccountEntry accountEntry = new AccountEntry(bal,"interest", "","");
		addEntry(accountEntry);

	}
}
