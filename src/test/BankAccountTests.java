package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import bankapp.BankAccount;

class BankAccountTests {
	
	private BankAccount testAccount;
	
	@BeforeEach
	public void setUp() {
		testAccount = new BankAccount();
	}

	@Test
	void testSimpleDeposit() {
		//1. Setup Objects

		//2. Call the method being tested
		testAccount.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testNegativeDeposit() {
		//1. Setup Objects
	
		//2. Call the method being tested
		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
	}
	
	@Test
	void testZeroBalance() {
	
		//1. set up objects 

		//2. call the method being tested
		
		//3. use assertions to verify results
		assertEquals(0.0, testAccount.getBalance(), 0.01);
	}
	
	@Test 
	void testSmallBalance() {
		//1. set up objects
	
		//2. call method being tested
		
		testAccount.deposit(25);
		
		//3. use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);
	}
	
	@Test 
	void testLargeBalance() {
		//1. set up objects
	
		//2. call method being tested
		
		testAccount.deposit(1000000);
		
		double balance = testAccount.getBalance();
		
		//3. use assertions to verify results
		assertEquals(1000000.0, balance, 0.01);
	}
	
	@Test 
	void testBalanceAfterMutlipleDeposits() {
		//1. set up objects
	
		//2. call method being tested
		
		testAccount.deposit(25);
		testAccount.deposit(100);
		
		double balance = testAccount.getBalance();
		
		//3. use assertions to verify results
		assertEquals(125.0, testAccount.getBalance(), 0.01);
	}
	
	@Test 
	void testBalanceAfterMultipleDeposits() {
		//1. set up objects
		
		//2. call method being tested
		
		testAccount.deposit(25);
		testAccount.deposit(125);
		testAccount.deposit(50);
		testAccount.deposit(60);
		
		double balance = testAccount.getBalance();
		
		//3. use assertions to verify results
		assertEquals(260.0, testAccount.getBalance(), 0.01);
	}
	
	//implementing as soon as we add withdraw function
	
//	@Test 
//	void testNegativeBalance() {
//		//1. set up objects
//		
//		//2. call method being tested
//		
//		testAccount.deposit(25);
//		testAccount.withdraw(30);
//		
//		double balance = testAccount.getBalance();
//		
//		//3. use assertions to verify results
//		assertEquals(-5.0, balance, 0.01);
//	}
	
	
//	@Test 
//	void testBalanceAfterMultipleDepositsAndWithdrawl() {
//		//1. set up objects
//		
//		//2. call method being tested
//		
//		testAccount.deposit(25);
//		testAccount.withdraw(30);
//		testAccount.deposit(125);
//		testAccount.withdraw(50);
//		testAccount.withdraw(60);
//		
//		double balance = testAccount.getBalance();
//		
//		//3. use assertions to verify results
//		assertEquals(10.0, balance, 0.01);
//	}

}
