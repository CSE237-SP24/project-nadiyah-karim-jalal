package bankapp;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

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
		this.account = new BankAccount(getUserName());
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
	public void saveAccountData(BankAccount account) {
		String filename = "files/accountData.txt";

		try (FileWriter writer = new FileWriter(filename, true)) {
			writer.write(account.getName() + ":" + account.getBalance() + "\n");
		} catch (IOException e) {
			System.err.println("Error occured while saving account data: " + e.getMessage());
		}
	} 
	
	public BankAccount getAccount() {
		return account;
	}
}
