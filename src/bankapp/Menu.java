package bankapp;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;

public class Menu {

	private Scanner in;
	private BankAccount account;
	private Map<String, BankAccount> allAccounts;
	
	//not tested
	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.displayingOptions();
	}
	
	//Constructor
	public Menu() {
		this.in = new Scanner(System.in);
		//Load all accounts
		this.allAccounts = getAllAccountData();
		String name = getUserName();
		if (allAccounts.containsKey(name)) {
			// Already has an account, can use their name and previous balance
			this.account = allAccounts.get(name);
		} else {
			//Ask for routing number when account is created 
			System.out.println("Enter routing number for the new account:");
			String routingNumber = in.nextLine();
			// New account, set balance to 0, set routing number
			this.account = new BankAccount(name, 0, routingNumber);
			allAccounts.put(name, this.account);

		}
	}
	
	//Code that just displays stuff - no tests needed
	public void displayingOptions() {
		boolean continueRunning = true;
		while (continueRunning) { 
			System.out.println("Would you like to Deposit (1), Withdraw (2), Take Loan (3), Loan Payment (4), Transfer(5), or Exit (6)? (Enter the number)");
			int choice = in.nextInt();
			in.nextLine();
		    switch (choice) {
                case 1:
                    doDeposit();
                    break;
                case 2:
                    doWithdrawal();
                    break;
                case 3:
                    doTakeLoan();
                    break;
                case 4:
                    doRepayLoan();
                    break;
				case 5:
					doTransfer();
					break;
                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid selection. Please enter a valid option. ");
            }
	}
	}
	
	//Code that gets user input
	//No tests needed...for now (probably discuss in future class)
	public double getValidUserInput(double amount) {
		while(amount < 0) {
			System.out.println("Invalid value! Enter a valid amount: ");
			amount = in.nextDouble();
		}
		return amount;
	}
   // do deposit
    private void doDeposit() {
        System.out.println("How much do you want to deposit? ");
        double amount = in.nextDouble();
        account.deposit(amount);
        getValidUserInput(amount);
        System.out.println("Current balance: " + account.getBalance());
        saveAccountData();
    }

    //do withdrawal
    private void doWithdrawal() {
        System.out.println("How much do you want to withdraw? ");
        double amount = in.nextDouble();
        getValidUserInput(amount);
        account.withdraw(amount);
        System.out.println("Current balance: " + account.getBalance());
        saveAccountData();
    }

    //taking out the loan
    private void doTakeLoan() {
        System.out.println("What is the loan amount? ");
        double amount = in.nextDouble();
        getValidUserInput(amount);
        account.takeLoan(amount);
        System.out.println("Loan taken. Your remaining loan amount is: " + account.getLoanAmount());
        saveAccountData();
    }

    //repaying a loan
    private void doRepayLoan() {
        System.out.println("What is the payment amount? ");
        double amount = in.nextDouble();
        getValidUserInput(amount);
        account.repayLoan(amount);
        System.out.println("Loan payment receieved. Your remaining loan amount is: " + account.getLoanAmount());
        saveAccountData();
    }

	//do a transfer
	private void doTransfer(){
		System.out.println("Enter the routing number of the account you wish to transfer to:");
		String routingNumber = in.nextLine();
		BankAccount recipient = findAccountByRoutingNumber(routingNumber);

		if (recipient == null) {
			System.out.println("Account not found.");
			return;
		}

		System.out.println("Enter the amount to transfer:");
		double amount = in.nextDouble();
		in.nextLine();
		try {
			account.transfer(recipient, amount);
			System.out.println("Transfer successful. New balance: " + account.getBalance());
			saveAccountData();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	//helper method that will find an account by routing number
	private BankAccount findAccountByRoutingNumber(String routingNumber) {
		for (BankAccount acc : allAccounts.values()) {
			if (acc.getRoutingNumber().equals(routingNumber)) {
				return acc;
			}
		}
		return null;
	}

	// In progress: Ask the user for their name to be associated with the account
	public String getUserName() {
		System.out.println("What name would you like to associate with this account?");
		String name = in.nextLine();
		while (name.isEmpty()) {
			System.out.println("Make sure your name is not blank!");
			System.out.println("What name would you like to associate with this account?");
			name = in.nextLine();
		}
		return name;
	}

	// In progress: Saving account data to a file
	// Instead of append, after being able to "log back in an account" the entire file is overwritten. A bit clunky but not really a choice without a real database
	private void saveAccountData() {
		String filename = "files/accountData.txt";

		try (FileWriter writer = new FileWriter(filename, false)) {
			for (BankAccount acc: allAccounts.values()) {
				writer.write(acc.getName() + ":" + acc.getBalance() + ":" + acc.getRoutingNumber() + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error occured while saving account data: " + e.getMessage());
		}
	}
	
	// For now, only worried about getting username with balance for more deposits/withdrawals
	private Map<String, BankAccount> getAllAccountData() {
		String filename = "files/accountData.txt";
		Map<String, BankAccount> allAccounts = new HashMap<>();

		try {
			for (String line : Files.readAllLines(Path.of(filename))) {
				String[] accountDetails = line.split(":");
				allAccounts.put(accountDetails[0], new BankAccount(accountDetails[0], Double.parseDouble(accountDetails[1]), accountDetails[2]));

			}
		} catch (IOException error) {
			System.out.println("Error occurred while reading account data: " + error.getMessage());
		}

		return allAccounts;
	}
	
	public BankAccount getAccount() {
		return account;
	}

	/*
	//Does work - needs tests
	public void processingUserSelection(double amount, boolean actionType) {
		//Withdraw
		if (actionType) {
			try {
				account.withdraw(amount);
				System.out.println("Withdraw Successful. Your balance is now:" + account.getBalance());
			} catch (IllegalArgumentException e) {
				System.out.println("Withdraw Failed");
			}
		}
		//Deposit
		else {
			try {
				account.deposit(amount);
				System.out.println("Deposit Successful. Your balance is now:" + account.getBalance());
			} catch (IllegalArgumentException e) {
				System.out.println("Deposit Failed");
			}
		}
		saveAccountData(account);
	}
	*/
}
