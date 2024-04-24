package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.Menu;
import java.io.ByteArrayInputStream;


class MenuTests {

    private Menu makeNewMenuWithInput(String data) {
        ByteArrayInputStream input = new ByteArrayInputStream(data.getBytes());
        System.setIn(input);
        return new Menu();  //create a new Menu object with the provided input
    }

	/*
	@Test
	void testUserDeposit() {
		Menu m = new Menu();
		//user has provided value input of 50
		m.processingUserSelection(50, false);
		
		BankAccount account = m.getAccount();
		assertEquals(50, account.getBalance(), 0.01);

	}
	*/


    @Test
    void testUserDeposit() {
        Menu m = makeNewMenuWithInput("1\n50\n5\n");  // inputs: deposit, 50, exit
        m.displayingOptions();
        assertEquals(50.0, m.getAccount().getBalance(), 0.01);
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

    @Test
    void testTakeLoan() {
        Menu m = makeNewMenuWithInput("3\n75\n5\n");  // inputs: take loan of 75; exit
        m.displayingOptions();
        assertEquals(75.0, m.getAccount().getLoanAmount(), 0.01);
        assertEquals(75.0, m.getAccount().getBalance(), 0.01); //loan amount + 0
    }

    @Test
    void testRepayLoan() {
        Menu m = makeNewMenuWithInput("3\n100\n4\n300\n5\n");  // inputs: take loan of 100; repay 25; exit
        m.displayingOptions();
        assertEquals(0.0, m.getAccount().getLoanAmount(), 0.01);
        assertEquals(1000.0, m.getAccount().getBalance(), 0.01); // no loan left, initial balance restored
    }
    

/*
    @Test
    void testInvalidOption() {
        Menu m = makeNewMenuWithInput("6\n5\n");  // inputs: invalid
        m.displayOptions();
        assertTrue(true); //flow control
    }
*/


}
