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
	
	//not tested
	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.displayingOptions();
	}
	
	//Constructor
	public Menu() {
		this.in = new Scanner(System.in);
		Map<String, Double> savedAccounts = getAllAccountData();
		String name = getUserName();
		if (savedAccounts.containsKey(name)) {
			// Already has an account, can use their name and previous balance
			this.account = new BankAccount(name, savedAccounts.get(name));
		} else {
			// New account, set balance to 0
			this.account = new BankAccount(name, 0);
		}
	}
	
	//Code that just displays stuff - no tests needed
	public void displayingOptions() {
		boolean continueRunning = true;
		while (continueRunning) { 
			System.out.println("Would you like to Deposit (1), Withdraw (2), Take Loan (3), Loan Payment (4) or Exit (5)? (Enter 1, 2, 3, 4, or 5)");
			int choice = in.nextInt();
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
                    System.out.println("Exiting program. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid, please enter 1 for Deposit or 2 for Withdraw, 3 for Taking Loan, 4 for Repaying, or 5 to Exit.");
            }
	}

	//Code that gets user input
	//No tests needed...for now (probably discuss in future class)
	public double getValidUserInput() {
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
        // getValidUserInput();
        System.out.println("Current balance: " + account.getBalance());
        saveAccountData(account);
    }

    //do withdrawal
    private void doWithdrawal() {
        System.out.println("How much do you want to withdraw? ");
        double amount = in.nextDouble();
        // getValidUserInput();
        account.withdraw(amount);
        System.out.println("Current balance: " + account.getBalance());
        saveAccountData(account);
    }

    //taking out the loan
    private void doTakeLoan() {
        System.out.println("What is the loan amount? ");
        double amount = in.nextDouble();
        // getValidUserInput();
        account.takeLoan(amount);
        System.out.println("Loan taken. Your remaining loan amount is: " + account.getLoanAmount());
        saveAccountData(account);
    }

    //repaying a loan
    private void doRepayLoan() {
        System.out.println("What is the payment amount? ");
        double amount = in.nextDouble();
        // getValidUserInput();
        account.repayLoan(amount);
        System.out.println("Loan payment receieved. Your remaining loan amount is: " + account.getLoanAmount());
        saveAccountData(account);
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
	public void saveAccountData(BankAccount account) {
		String filename = "files/accountData.txt";
		Map<String, Double> allAccounts = getAllAccountData();

		// Whatever the account that just did a deposit or withdrawal, now has a new balance
		allAccounts.put(account.getName(), account.getBalance());

		try (FileWriter writer = new FileWriter(filename, false)) {
			for (Map.Entry<String, Double> entry : allAccounts.entrySet()) {
				writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
			}
		} catch (IOException e) {
			System.err.println("Error occured while saving account data: " + e.getMessage());
		}
	}
	
	// For now, only worried about getting username with balance for more deposits/withdrawals
	private Map<String, Double> getAllAccountData() {
		String filename = "files/accountData.txt";
		Map<String, Double> allAccounts = new HashMap<>();

		try {
			for (String line : Files.readAllLines(Path.of(filename))) {
				String[] currentAccountDetails = line.split(":");
				allAccounts.put(currentAccountDetails[0], Double.parseDouble(currentAccountDetails[1]));

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