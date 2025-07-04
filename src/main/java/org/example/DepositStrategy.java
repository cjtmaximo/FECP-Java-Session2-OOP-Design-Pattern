package org.example;

public class DepositStrategy implements TransactionStrategy{
    @Override
    public void calculate(BankAccount bankAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than $0.");
            return;
        }

        double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);
    }
}
