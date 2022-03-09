package observerPattern;

import observerPattern.domain.Account;
import observerPattern.domain.AccountEntry;
import observerPattern.domain.Customer;
import observerPattern.observers.EmailSender;
import observerPattern.observers.Logger;
import observerPattern.observers.SMSSender;
import observerPattern.service.AccountService;
import observerPattern.service.AccountServiceImpl;
import observerPattern.util.MessageSubject;
import observerPattern.util.Observer;

public class Application {
	public static void main(String[] args) {
		AccountService accountService = new AccountServiceImpl();
		MessageSubject messageSubject = new MessageSubject();

		// create 2 accounts;
		accountService.createAccount("1263862", "Frank Brown");
		accountService.createAccount("4253892", "John Doe");
		messageSubject.registerObserver(new EmailSender());
		messageSubject.setMsg("Account has been created");

		// use account 1;
		accountService.deposit("1263862", 240);
		messageSubject.registerObserver(new Logger());
		messageSubject.setMsg("Account info has been changed");
		accountService.deposit("1263862", 529);
		accountService.withdraw("1263862", 230);
		// use account 2;
		accountService.deposit("4253892", 12450);
		accountService.transferFunds("4253892", "1263862", 100, "payment of invoice 10232");
		// show balances
		messageSubject.notifyObservers();
		for (Account account : accountService.getAllAccounts()) {
			Customer customer = account.getCustomer();
			System.out.println("Statement for Account: " + account.getAccountNumber());
			System.out.println("Account Holder: " + customer.getName());
			
			System.out.println("-Date-------------------------" 
					+ "-Description------------------" 
					+ "-Amount-------------");
			
			for (AccountEntry entry : account.getEntryList()) {
				System.out.printf("%30s%30s%20.2f\n", 
						entry.getDate().toString(), 
						entry.getDescription(),
						entry.getAmount());
			}
			
			System.out.println("----------------------------------------" + "----------------------------------------");
			System.out.printf("%30s%30s%20.2f\n\n", "", "Current Balance:", account.getBalance());
		}
	}

}
