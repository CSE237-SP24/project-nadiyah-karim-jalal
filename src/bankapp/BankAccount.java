package bankapp;

public class BankAccount {
	
	private double balance;
	private String name;
	private double loanAmount;
	private String accountType; 
	private String routingNumber;
	private final int routingNumberLengthGenerator = 10000; // Can change lengths
	
	//Constructors - not tested
	public BankAccount(String name, double balance, String accountType, String routingNumber) {
		this.balance = balance;
		this.name = name;
		this.accountType = accountType;
		this.loanAmount = 0;
		this.routingNumber = routingNumber == null ? generateRoutingNumber() : routingNumber;
		if (routingNumber == null) {
			System.out.println("Your routing number is: " + this.routingNumber + ". Make sure to remember it!\n");
		}
	}
	
	public BankAccount(String name, double balance) {
		this.balance = balance;
		this.name = name;
		this.loanAmount = 0;
		this.routingNumber = generateRoutingNumber();
		System.out.println("Your routing number is: " + this.routingNumber + ". Make sure to remember it!\n");
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
	
	public String getAccountType() {
		return this.accountType;
	}
	

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getRoutingNumber() { 
		return this.routingNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
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
	
	public String getSavingsOrChecking() {
		return this.accountType;
	}
}


