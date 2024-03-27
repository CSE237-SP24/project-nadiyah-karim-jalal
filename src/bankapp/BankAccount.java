package bankapp;

public class BankAccount {
	
	private double balance;
	private String name;
	
	//Constructors - not tested
	public BankAccount(String name) {
		this.balance = 0;
		this.name = name;
	}
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}
	
	//getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}

	public String getName() {
		return this.name;
	}
}
