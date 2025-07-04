package org.example;

public interface TransactionStrategy {
    void calculate(BankAccount bankAccount, double amount);
}
