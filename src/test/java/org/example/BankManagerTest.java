package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BankManagerTest {

    private BankManager bankManager;

    @BeforeEach
    void setUp() {
        BankManager.getAccounts().clear();
        bankManager = new BankManager();
    }

    @Test
    void testComputeInterestForSavingsAccount() {
        // Test case 1: Savings account interest computation
        BankAccount savingsAccount = new BankAccount("savings", 1001, "John Doe", 1234, 1000.0);
        BankManager.getAccounts().add(savingsAccount);

        double expectedInterest = 1000.0 * 0.10 * 6; // 10% monthly for 6 months
        double actualInterest = BankManager.computeInterest(savingsAccount, 6);
        assertEquals(expectedInterest, actualInterest, 0.001, "Savings account interest should be calculated correctly.");
    }

    @Test
    void testComputeInterestForCheckingAccount() {
        // Test case 2: Checking account interest computation
        BankAccount checkingAccount = new BankAccount("checking", 1002, "John Doe", 5678, 500.0);
        BankManager.getAccounts().add(checkingAccount);

        double expectedInterest = 500.0 * 0.05 * 12; // 5% monthly for 12 months
        double actualInterest = BankManager.computeInterest(checkingAccount, 12);
        assertEquals(expectedInterest, actualInterest, 0.001);
    }

    @Test
    void testComputeInterestForInvalidMonths() {
        // Test case 3: Interest computation with invalid (non-positive) months
        BankAccount account = new BankAccount("savings", 1003, "John Doe", 9999, 2000.0);
        BankManager.getAccounts().add(account);

        double actualInterestNegativeMonths = BankManager.computeInterest(account, -5);
        assertEquals(0.0, actualInterestNegativeMonths);

        double actualInterestZeroMonths = BankManager.computeInterest(account, 0);
        assertEquals(0.0, actualInterestZeroMonths);
    }

    @Test
    void testComputeInterestForNullAccount() {
        // Test case 4: Interest computation with a null account
        double actualInterest = BankManager.computeInterest(null, 3);
        assertEquals(0.0, actualInterest);
    }

    @Test
    void testComputeInterestForUnknownAccountType() {
        // Test case 5: Interest computation with an unknown account type
        BankAccount unknownAccount = new BankAccount("unknown", 1004, "John Doe", 1111, 100.0);
        BankManager.getAccounts().add(unknownAccount);

        double actualInterest = BankManager.computeInterest(unknownAccount, 1);
        assertEquals(0.0, actualInterest);
    }

    @Test
    void testDepositAValidAmount() {
        // Test case for a valid deposit
        BankAccount account = bankManager.createAccount("savings", 1000, "John Doe", 1234, 100.0);
        bankManager.deposit(account, 1000);
        assertEquals(1100.0, account.getBalance(), 0.001);
    }

    @Test
    void testDepositAnInvalidAmount() {
        // Test case for an invalid deposit amount (zero or negative)
        BankAccount account = bankManager.createAccount("checking", 2000, "John Doe", 5678, 200.0);
        bankManager.deposit(account, -20);
        assertEquals(200.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawAValidAmount() {
        // Test case for a valid withdrawal
        BankAccount account = bankManager.createAccount("savings", 3000, "John Doe", 9876, 500.0);
        bankManager.withdraw(account, -50);
        assertEquals(500.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawAnAmountExceedingBalance() {
        // Test case for withdrawal with insufficient funds
        BankAccount account = bankManager.createAccount("checking", 4000, "John Doe", 4321, 50.0);
        bankManager.withdraw(account,500);
        assertEquals(50.0, account.getBalance(), 0.001);
    }

    @Test
    void testWithdrawAnInvalidAmount() {
        // Test case for an invalid withdrawal amount (zero or negative)
        BankAccount account = bankManager.createAccount("savings", 5000, "John Doe", 1122, 300.0);
        bankManager.withdraw(account, -50);
        assertEquals(300.0, account.getBalance(), 0.001);
    }
}
