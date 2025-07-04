package org.example;

public class SavingsInterestStrategy implements InterestCalculationStrategy {
    private static final double MONTHLY_INTEREST_RATE = 0.10; // 10% monthly

    @Override
    public double getMonthlyInterestRate() {
        return MONTHLY_INTEREST_RATE;
    }
}