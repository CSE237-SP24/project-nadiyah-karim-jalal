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
		double amount = mainMenu.getValidUserInput();
		mainMenu.processingUserSelection(amount);
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
		System.out.println("How much money do you want to deposit?");
	}
	
	//Code that gets user input
	//No tests needed...for now (probably discuss in future class)
	public double getValidUserInput() {
		double amount = in.nextDouble();
		while(amount < 0) {
			System.out.println("Invalid value!");
			System.out.println("How much money do you want to deposit?");
			amount = in.nextDouble();
		}
		return amount;
	}
	
	//Does work - needs tests
	public void processingUserSelection(double amount) {
		account.deposit(amount);
		System.out.println("Your name is: " + account.getName() + " and your balance is now: " + account.getBalance());
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

		// Whatever the account that just did a deposit, now has a new balance
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
}
