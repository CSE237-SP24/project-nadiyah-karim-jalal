package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import bankapp.Menu;
import bankapp.BankAccount;
import java.io.File;
import java.nio.files.Files;
import java.nio.files.Paths;
import java.util.List;


public class FileTests {

    private final String testFilePath = "files/testingAccountData.txt";
    private Menu testMenu;

    @BeforeEach
    void setUp() throws Exception {
        testMenu = new Menu();  

        // Manually set the account details for testing purposes
        String name = "Test_User";
        double balance = 99.0;
        String routingNumber = "4000";
        String accountType = "checking";
        double loanAmount = 0.0;

        List<String> lines = List.of(name + ":" + balance + ":" + routingNumber + ":" + accountType + ":" + loanAmount);
        Files.write(Paths.get(testFilePath), lines);
    }

    @AfterEach
    void finishFileTest() throws Exception {
        java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(testFilePath));
    }

    @Test
    void testSavingAccountInformation() {
        assertTrue(Files.exists(Paths.get(testFilePath)), "The account data file should exist after setup.");

        // Read content from file
        String fileContent = Files.readString(Paths.get(testFilePath));
        String expectedFileContent = "Test_User:99.0:4000:checking:0.0\n"; // Expected content format in the file

        assertEquals(expectedFileContent, fileContent, "The file does not contain the expected account information.");
    }    

    @Test
    void testAccountCreationAndDataIntegrity() {
        // Assuming testMenu initializes allAccounts with the file data
        BankAccount loadedAccount = testMenu.getAllAccountData().get("Test_User");

        // Verify that all account details were properly initialized and saved
        assertEquals("Test_User", loadedAccount.getName(), "Names should match");
        assertEquals(99.0, loadedAccount.getBalance(), "Balances should match");
        assertEquals("1234567890", loadedAccount.getRoutingNumber(), "Routing numbers should match");
        assertEquals("checking", loadedAccount.getAccountType(), "Account types should match");
        assertEquals(0.0, loadedAccount.getLoanAmount(), "Loan amounts should match");
    }


}
