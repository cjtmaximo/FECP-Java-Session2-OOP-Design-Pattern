package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InterestCalculationStrategyTest {

    @Test
    void testSavingsInterestStrategy() {
        // Test SavingsInterestStrategy's monthly interest rate
        SavingsInterestStrategy strategy = new SavingsInterestStrategy();

        assertEquals(0.10, strategy.getMonthlyInterestRate(), 0.001);
    }

    @Test
    void testCheckingInterestStrategy() {
        // Test CheckingInterestStrategy's monthly interest rate
        CheckingInterestStrategy strategy = new CheckingInterestStrategy();

        assertEquals(0.05, strategy.getMonthlyInterestRate(), 0.001);
    }

    @Test
    void testCalculateInterestForPositiveMonths() {
        // Test the default calculate method with positive months
        InterestCalculationStrategy strategy = new SavingsInterestStrategy();

        BankAccount account = new BankAccount("savings", 1, "Test", 1234, 1000.0);
        double expectedInterest = 1000.0 * 0.10 * 3; // 3 months
        assertEquals(expectedInterest, strategy.calculate(account, 3), 0.001);
    }

    @Test
    void testCalculateInterestForZeroMonths() {
        // Test the default calculate method with zero months
        InterestCalculationStrategy strategy = new SavingsInterestStrategy();

        BankAccount account = new BankAccount("savings", 1, "Test", 1234, 1000.0);
        assertEquals(0.0, strategy.calculate(account, 0), 0.001);
    }

    @Test
    void testCalculateInterest_NegativeMonths() {
        // Test the default calculate method with negative months
        InterestCalculationStrategy strategy = new SavingsInterestStrategy();

        BankAccount account = new BankAccount("savings", 1, "Test", 1234, 1000.0);
        assertEquals(0.0, strategy.calculate(account, -5), 0.001);
    }
}
