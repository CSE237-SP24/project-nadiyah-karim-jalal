package bankapp;

import java.util.Scanner;

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
		this.account = new BankAccount();
	}
	
	//Code that just displays stuff - no tests needed
	public void displayingOptions() {
		System.out.println("Would you like to Deposit (1) or Withdraw (2) money? (Enter 1 or 2)?");
		int choice = in.nextInt();
		while (choice != 1 && choice != 2) {
			System.out.println("Invalid, please enter 1 for Deposit or 2 for Withdraw.");
			choice = in.nextInt();
		}
		//Deposit
		if (choice == 1){
			System.out.println("How much money do you want to deposit?");
		}
		else if (choice == 2){
			System.out.println("How much money do you want to withdraw?");
		}

		double amount = getValidUserInput();
		processingUserSelection(amount, choice != 1);

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
	}
	
	public BankAccount getAccount() {
		return account;
	}
}
