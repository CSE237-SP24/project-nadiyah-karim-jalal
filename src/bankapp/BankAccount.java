package bankapp;

public class BankAccount {
	
	private double balance;
	private String name;
	private double loanAmount;
	private String routingNumber;
	private final int routingNumberLengthGenerator = 10000; // Can change lengths
	
	//Constructors - not tested
	public BankAccount(String name, double balance) {
		this.balance = balance;
		this.name = name;
		this.loanAmount = 0;
		this.routingNumber = generateRoutingNumber();
		System.out.println("Your routing number is: " + this.routingNumber + ". Make sure to remember it!");
	}

	public BankAccount(String name, double balance, String routingNumber) {
		this.balance = balance;
		this.name = name;
		this.loanAmount = 0;
		this.routingNumber = routingNumber;
	}

	private String generateRoutingNumber() {
		return String.valueOf((int)(Math.random() * routingNumberLengthGenerator));
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

	public double getLoanAmount(){
		return this.loanAmount;
	}

	public String getRoutingNumber() { 
		return this.routingNumber;
	}

    //taking loan
    public void takeLoan(double amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("Loan amount must be positive!");
        }
        this.loanAmount += amount;
        this.balance += amount;
    }

    //making loan payment
    public void repayLoan(double amount) {
        if(amount <= 0 || amount > this.loanAmount) {
            throw new IllegalArgumentException("Invalid repayment amount!");
        }
        this.loanAmount -= amount;
        this.balance -= amount;
    }

	//transfer money from sender to recipient
	public void transfer (BankAccount recipient, double amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.validBalance(amount);
		recipient.deposit(amount);
		this.balance -= amount;
	}
    


}


