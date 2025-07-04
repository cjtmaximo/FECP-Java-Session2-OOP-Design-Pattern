package org.example;

public class CheckingInterestStrategy implements InterestCalculationStrategy {
    private static final double MONTHLY_INTEREST_RATE = 0.05; // 5% monthly

    @Override
    public double getMonthlyInterestRate() {
        return MONTHLY_INTEREST_RATE;
    }
}