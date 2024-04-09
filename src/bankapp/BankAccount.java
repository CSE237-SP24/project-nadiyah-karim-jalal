package bankapp;

public class BankAccount {
	
	private double balance;
	private String name;
	
	//Constructors - not tested
	public BankAccount(String name, double balance) {
		this.balance = balance;
		this.name = name;
	}
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}
    
	//public method doing some work - lots of tests
	public void validBalance(double amount) { 
		if (amount > this.balance) { 
			throw new IllegalArgumentException("Insufficient balance");
		}
		if (amount < 0) { 
			throw new IllegalArgumentException("Amount must be positive");
		}
		// this.balance -= amount; Commenting before I delete, but wouldn't this do the withdrawal twice?
	}
	
	public double withdraw(double amount) { 
		validBalance(amount);
		this.balance -= amount;
	
		return amount;
	}
	
	//getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}
		
	public double cashOut() {
		return this.withdraw(this.balance);
	}

	public String getName() {
		return this.name;
	}
}
