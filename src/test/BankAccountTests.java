package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;

import bankapp.BankAccount;

class BankAccountTests {
	BankAccount testAccount = new BankAccount("testAccount", 50);

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
	void testCashOut() {
		assertEquals(testAccount.getBalance(), testAccount.cashOut(), 0.01);
		assertEquals(0, testAccount.getBalance(), 0.01);
	}

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
	
	@Test 
	void testNegativeBalance() {
		//1. set up objects

		//2. call method being tested

		testAccount.deposit(25);

		try {
			testAccount.withdraw(30);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}
	
	
	@Test 
	void testBalanceAfterMultipleDepositsAndWithdrawl() {
		//1. set up objects

		//2. call method being tested

		testAccount.deposit(25);
		testAccount.withdraw(3);
		testAccount.deposit(125);
		testAccount.withdraw(15);
		testAccount.withdraw(60);

		double balance = testAccount.getBalance();

		//3. use assertions to verify results
		assertEquals(72.0, balance, 0.01);
	}

	@Test
	void testWithdrawalLimit() {

		testAccount.deposit(1000);

		assertThrows(IllegalArgumentException.class, () -> testAccount.withdraw(2000), "Withdrawal exceeds balance");
	}
	@Test
	void testNegativeWithdrawal() {

    assertThrows(IllegalArgumentException.class, () -> testAccount.withdraw(-50), "Can't withdraw negative amounts");
	}

	@Test
	void testWithdrawToZero() {

		testAccount.deposit(75);
		testAccount.withdraw(75);

		assertEquals(0, testAccount.getBalance(), 0.01);
	}

	@Test
	void testZeroWithdrawal() {

    	testAccount.deposit(100);
    	testAccount.withdraw(0);

    	assertEquals(100, testAccount.getBalance(), 0.01);
	}

    @Test
    void testTakeLoan() { // balance includes loan
        testAccount.deposit(100);
        testAccount.takeLoan(100);
        assertEquals(100.0, testAccount.getLoanAmount(), 0.01);
        assertEquals(200.0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testRepayLoan() { // balance after repaying part of the loan
        testAccount.deposit(100);
        testAccount.takeLoan(100);
        testAccount.repayLoan(50);
        assertEquals(50.0, testAccount.getLoanAmount(), 0.01);
        assertEquals(150.0, testAccount.getBalance(), 0.01);
    }

    @Test
    void testRepaymentMoreThanLoan() {
        testAccount.deposit(100);
        testAccount.takeLoan(50);
       try {
           testAccount.repayLoan(100); // An attempt to repay more than the loan amount
           fail();
       } catch (IllegalArgumentException e) {
           // We expect to end up here, repaying more than the loan is a bad input
           assertTrue(true);
       }
    }
}
