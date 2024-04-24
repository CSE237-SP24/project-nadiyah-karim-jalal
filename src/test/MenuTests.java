package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;

class MenuTests {

	
	@Test
	void testUserDeposit() {
		Menu m = new Menu();
		//user has provided value input of 50
		m.processingUserSelection(50, false);
		
		BankAccount account = m.getAccount();
		assertEquals(50, account.getBalance(), 0.01);
		
		
	}

	@Test
	void testUserWithdrawal() {
    Menu m = makeNewMenuWithInput("2\n25\n5\n");  // inputs: withdraw, 25, exit
    m.displayingOptions();
    assertEquals(75.0, m.getAccount().getBalance(), 0.01); // Assuming initial balance was 100
	}

	@Test
	void testDepositThenWithdrawal() {
    Menu m = makeNewMenuWithInput("1\n100\n2\n50\n5\n");  // inputs: deposit 100, withdraw 50, exit
    m.displayingOptions();
    assertEquals(50.0, m.getAccount().getBalance(), 0.01);
	}

}
