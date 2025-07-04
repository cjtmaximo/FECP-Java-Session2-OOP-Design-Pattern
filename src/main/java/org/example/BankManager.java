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
}
