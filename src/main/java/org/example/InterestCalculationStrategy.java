package org.example;

interface InterestCalculationStrategy {
    double getMonthlyInterestRate(); // abstract method to be implemented by the concrete strategies (savings, checking)

    default double calculate(BankAccount account, int months) {
        if (months <= 0) {
            System.out.println("The time period for interest calculation must be positive.");
            return 0;
        }

        double currentBalance = account.getBalance();
        double totalInterest = currentBalance * getMonthlyInterestRate() * months;

        return totalInterest;
    }
}