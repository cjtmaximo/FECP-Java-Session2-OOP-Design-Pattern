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
