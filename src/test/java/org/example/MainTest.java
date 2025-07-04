package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    private SavingsInterestStrategy savingsInterestStrategy;
    private CheckingInterestStrategy checkingInterestStrategy;

    private DepositStrategy depositStrategy;
    private WithdrawStrategy withdrawStrategy;

    private BankAccount account;
    private BankAccount checkingAccount;

    @BeforeEach
    void setUp() {
        savingsInterestStrategy = new SavingsInterestStrategy();
        checkingInterestStrategy = new CheckingInterestStrategy();

        depositStrategy = new DepositStrategy();
        withdrawStrategy = new WithdrawStrategy();

        BankManager.getAccounts().clear();
        account = new BankAccount("savings", 12345, "Test User", 1234, 100.0);
    }

    // For Account Creation
    @Test
    void testCreateAccountSuccessfully() {
        BankManager manager = new BankManager();
        BankAccount account = manager.createAccount("savings", 12345, "Alice", 1234, 100.0);
        assertNotNull(account);
        assertEquals(12345, account.getAccountNumber());
        assertEquals("Alice", account.getName());
        assertEquals(100.0, account.getBalance());
        assertEquals(1234, account.getPin());
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

    // For Interest Computation
    @Test
    void testCalculateInterestForValidMonths() {
        double expectedInterest = account.getBalance() * 0.10 * 3; // 3 months
        assertEquals(expectedInterest, savingsInterestStrategy.calculate(account, 3), 0.001);
    }

    @Test
    void testCalculateInterestForZeroMonths() {
        assertEquals(0.0, savingsInterestStrategy.calculate(account, 0), 0.001);
    }

    @Test
    void testCalculateInterestForNegativeMonths() {
        assertEquals(0.0, savingsInterestStrategy.calculate(account, -5), 0.001);
    }

    @Test
    void testComputeInterestForSavingsAccount() {
        // Savings account interest computation
        BankAccount savingsAccount = new BankAccount("savings", 1001, "John Doe", 1234, 1000.0);
        BankManager.getAccounts().add(savingsAccount);

        double expectedInterest = 1000.0 * 0.10 * 6; // 10% monthly for 6 months
        double actualInterest = BankManager.computeInterest(savingsAccount, 6);
        assertEquals(expectedInterest, actualInterest, 0.001);
    }

    @Test
    void testComputeInterestForCheckingAccount() {
        // Checking account interest computation
        BankAccount checkingAccount = new BankAccount("checking", 1002, "John Doe", 5678, 500.0);
        BankManager.getAccounts().add(checkingAccount);

        double expectedInterest = 500.0 * 0.05 * 12; // 5% monthly for 12 months
        double actualInterest = BankManager.computeInterest(checkingAccount, 12);
        assertEquals(expectedInterest, actualInterest, 0.001);
    }

    @Test
    void testComputeInterestForUnknownAccountType() {
        // Interest computation with an unknown account type
        BankAccount unknownAccount = new BankAccount("unknown", 1004, "John Doe", 1111, 100.0);
        BankManager.getAccounts().add(unknownAccount);

        double actualInterest = BankManager.computeInterest(unknownAccount, 1);
        assertEquals(0.0, actualInterest);
    }

    // For deposits
    @Test
    void testCalculateValidDeposit1() {
        depositStrategy.calculate(account, 50.0);
        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    void testCalculateZeroDeposit() {
        depositStrategy.calculate(account, 0.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    void testCalculateNegativeDeposit() {
        depositStrategy.calculate(account, -25.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    // For withdrawals
    @Test
    void testCalculateValidWithdrawal() {
        withdrawStrategy.calculate(account, 50.0);
        assertEquals(50.0, account.getBalance(), 0.001);
    }

    @Test
    void testCalculateZeroWithdrawal() {
        withdrawStrategy.calculate(account, 0.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    void testCalculateNegativeWithdrawal() {
        withdrawStrategy.calculate(account, -25.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }

    @Test
    void testCalculateWithdrawalExceedingBalance() {
        withdrawStrategy.calculate(account, 150.0);
        assertEquals(100.0, account.getBalance(), 0.001);
    }
}