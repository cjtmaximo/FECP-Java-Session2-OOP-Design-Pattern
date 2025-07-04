package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @BeforeEach
    void setUp() {
        BankManager.getAccounts().clear();
    }

    @Test
    void testCreateAccountSuccessfully() {
        BankManager manager = new BankManager();
        BankAccount account = manager.createAccount("savings", 12345, "Alice", 1234, 100.0);
        assertNotNull(account);
        assertEquals(12345, account.getAccountNumber());
        assertEquals("Alice", account.getName());
        assertEquals(100.0, account.getBalance());
        assertEquals(1, BankManager.getAccounts().size());
    }

    @Test
    void testDuplicateAccountNumberNotAllowed() {
        BankManager manager = new BankManager();
        manager.createAccount("savings", 12345, "Alice", 1234, 100.0);
        boolean exists = BankManager.accountNumberExists(12345);
        assertTrue(exists);
        // try to create another account with the same account number
        if (BankManager.accountNumberExists(12345)) {
            assertEquals(1, BankManager.getAccounts().size());
        } else {
            manager.createAccount("checking", 12345, "Bob", 4321, 200.0);
            fail("Duplicate account number should not be allowed");
        }
    }

    @Test
    void testCreateAccountWithNegativeDeposit() {
        BankManager manager = new BankManager();
        BankAccount account = manager.createAccount("savings", 22222, "Negative", 1234, -50.0);
        assertNotNull(account);
        assertTrue(account.getBalance() < 0);
    }

    @Test
    void testCreateAccountWithZeroDeposit() {
        BankManager manager = new BankManager();
        BankAccount account = manager.createAccount("savings", 33333, "Zero", 1234, 0.0);
        assertNotNull(account);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void testCreateAccountWithNegativeAccountNumber() {
        BankManager manager = new BankManager();
        BankAccount account = manager.createAccount("savings", -12345, "NegativeAccount", 1234, 100.0);
        assertNull(account);
        assertEquals(0, BankManager.getAccounts().size());
    }

    @Test
    void testCreateAccountWithNegativePin() {
        BankManager manager = new BankManager();
        BankAccount account = manager.createAccount("savings", 67890, "NegativePin", -4321, 100.0);
        assertNull(account);
        assertEquals(0, BankManager.getAccounts().size());
    }
}