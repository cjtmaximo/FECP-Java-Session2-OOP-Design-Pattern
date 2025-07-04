package org.example;

public class BankAccount {

    private String accountType;
    private int accountNumber;
    private String name;
    private int pin;
    private double balance;

    // constructors
    public BankAccount(String accountType, int accountNumber, String name,  int pin, double balance) {
        this.accountType = accountType;
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    // getters
    public String getAccountType() {
        return accountType;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    // setters
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
