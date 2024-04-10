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


public class FileTests {

    private final String testFilePath = "files/testingAccountData.txt";
    private Menu testMenu;

    @BeforeEach
    void setUp() throws Exception {
        testMenu = new Menu();
        java.nio.file.Files.createFile(Paths.get(testFilePath));
    }

    @AfterEach
    void finishFileTest() throws Exception {
        java.nio.file.Files.deleteIfExists(Paths.get(testFilePath));
    }

    @Test
    void testSavingAccountInformation() {

        BankAccount testAccount = new BankAccount("Test_User", 99.0);
        testMenu.saveAccountData(testAccount);

        assertTrue(Files.exists(Paths.get(testFilePath)));

        String fileContent = Files.readString(Paths.get(testFilePath));
        String expectedFileContent = "Test_User:99.0\n";
        assertEquals(expectedFileContent, fileContent, "The file does not contain the expected format name:balance");
    }    
}
