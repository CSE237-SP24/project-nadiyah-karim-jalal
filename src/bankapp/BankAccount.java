package bankapp;

public class BankAccount {
	
	private double balance;
	
	//Constructors - not tested
	public BankAccount() {
		this.balance = 0;
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
		this.balance -= amount;
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
	
	

}
