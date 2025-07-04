package org.example;

public class WithdrawStrategy implements TransactionStrategy {
    @Override
    public void calculate(BankAccount bankAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Withdraw amount must be greater than $0.");
            return;
        }

        if (amount > bankAccount.getBalance()) {
            System.out.println("You cannot withdraw more than you have.");
            return;
        }

        double newBalance = bankAccount.getBalance() - amount;
        bankAccount.setBalance(newBalance);
    }
}
