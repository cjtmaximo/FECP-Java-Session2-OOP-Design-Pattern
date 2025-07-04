package org.example;

import java.util.ArrayList;

public class BankManager {
    private static ArrayList<BankAccount> accounts = new ArrayList<>();

    // creating an account
    public BankAccount createAccount(String accountType, int accountNumber, String name,  int pin, double balance){
        BankAccount bankAccount = new BankAccount(accountType, accountNumber, name, pin, balance);
        accounts.add(bankAccount);
        return bankAccount;
    }

    public static ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public static boolean accountNumberExists(int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return true;
            }
        }
        return false;
    }
    // Find specific account
    public static BankAccount getAccount(int accountNumber, int pin) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber && account.getPin() == pin) {
                return account;
            }
        }
        return null;
    }

    public static double computeInterest(BankAccount account, int months) {
        if (account == null) {
            System.out.println("[Invalid Account] Cannot compute interest for a null account.");
            return 0;
        }

        InterestCalculationStrategy strategy;
        if (account.getAccountType().equals("savings")) {
            strategy = new SavingsInterestStrategy();
        } else if (account.getAccountType().equals("checking")) {
            strategy = new CheckingInterestStrategy();
        } else {
            System.out.println("Unknown account type for interest calculation. No interest computed.");
            return 0;
        }
        return strategy.calculate(account, months);
    public void deposit(int accountNumber, int pin, double depositAmount) {
        if (depositAmount <= 0.0) {
            System.out.println("Deposit amount must be greater than $0.");
            return;
        }

        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                double newBalance = account.getBalance() + depositAmount;
                account.setBalance(newBalance);
            }
        }

        System.out.printf("Account with account number %d does not exist.\n", accountNumber);
        return;
    }

    public void withdraw(int accountNumber, int pin, double withdrawAmount) {
        if (withdrawAmount <= 0.0) {
            System.out.println("Withdraw amount must be greater than $0.");
            return;
        }

        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                if (withdrawAmount > account.getBalance()) {
                    System.out.println("You cannot withdraw more than you have.");
                    return;
                }
                double newBalance = account.getBalance() - withdrawAmount;
                account.setBalance(newBalance);
            }
        }

        System.out.printf("Account with account number %d does not exist.\n", accountNumber);
        return;
    }

}
