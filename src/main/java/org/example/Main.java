package org.example;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankManager manager = new BankManager();

        while (true) {
            System.out.println("\n=== Welcome to GBank ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Compute Interest");
            System.out.println("5. Display Account");
            System.out.println("6. Exit\n");

            System.out.print("Enter choice: ");

            String accountType;
            String name;
            int accountNumber;
            int pin;
            double amount;

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch(choice) {
                    case 1:
                        // create an account
                        System.out.println("--- Create your GBank Account ---");

                        accountType = getAccountType(scanner);
                        accountNumber = getAccountNumber(scanner);
                        name = getAccountHolderName(scanner);
                        pin = getPin(scanner);

                        // ask for initial deposit
                        System.out.print("Would you like to enter an Initial Deposit? (y/n): ");
                        String depositChoice = scanner.nextLine().toLowerCase();

                        double initialDeposit = 0;
                        if (depositChoice.equals("y")){
                            System.out.print("Enter Initial Deposit Amount: ");
                            try{
                                initialDeposit = Double.parseDouble(scanner.nextLine());
                                if(initialDeposit <= 0.0){
                                    System.out.println("[Invalid Deposit Amount] Amount should be > $0.0");
                                    break;
                                }

                            } catch (NumberFormatException e) {
                                System.out.println("[Invalid Input] Enter Numbers for Deposit Amount.");
                                break;
                            }
                        } else {
                            System.out.println("[No Initial Deposit Made] Balance: $0.0");
                        }

                        BankAccount newAccount = manager.createAccount(accountType, accountNumber, name, pin, initialDeposit);
                        System.out.println("\n[GBank Account Created Successfully] \nAccount Number: " + accountNumber +"\nAccount Type: " + accountType + "\nName: " + name + "\nBalance: $" + newAccount.getBalance());
                        break;

                    case 2:
                        // deposit
                        accountNumber = getAccountNumber(scanner);
                        pin = getPin(scanner);
                        double depositAmount = getDepositAmount(scanner);

                        manager.deposit(accountNumber, pin, depositAmount);
                        break;
                    case 3:
                        // withdraw
                        accountNumber = getAccountNumber(scanner);
                        pin = getPin(scanner);
                        double withdrawAmount = getWithdrawAmount(scanner);

                        manager.withdraw(accountNumber, pin, withdrawAmount);
                        break;
                    case 4: // compute interest
                        accountNumber = getAccountNumber(scanner);
                        pin = getPin(scanner);

                        // Retrieve the account using BankManager.getAccount
                        BankAccount interestAccount = BankManager.getAccount(accountNumber, pin);
                        if (interestAccount != null) {
                            System.out.print("Enter number of months for interest calculation: ");
                            try {
                                int months = Integer.parseInt(scanner.nextLine());
                                double totalInterest = BankManager.computeInterest(interestAccount, months);
                                System.out.printf("Total interest for %d months: $%.2f\n", months, totalInterest);
                            } catch (NumberFormatException e) {
                                System.out.println("[Invalid Input] Number of months must be a number.");
                            }
                        } else {
                            System.out.println("[Account Not Found] No account matches the provided details.");
                        }

                        break;
                    case 5: // display account
                        break;
                    case 6:
                        System.out.println("\n---Thank you for using GBank!---");
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 5.");
                        break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.\n");
            }


        }
    }

    // Helper method to get account type from the user
    private static String getAccountType(Scanner scanner) {
        while (true) {
            System.out.print("Enter Account Type (savings/checking): ");
            String accountType = scanner.nextLine().toLowerCase();
            if (accountType.equals("savings") || accountType.equals("checking")) {
                return accountType;
            } else {
                System.out.println("[Invalid input] Please enter 'savings' or 'checking'.");
            }
        }
    }

    // Helper method to get account number from the user
    private static int getAccountNumber(Scanner scanner) {
        while (true) {
            System.out.print("Enter Account Number: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[Invalid Input] Account Number must be a number.");
            }
        }
    }

    // Helper method to get account holder name from the user
    private static String getAccountHolderName(Scanner scanner) {
        System.out.print("Enter Account Holder Name: ");
        return scanner.nextLine();
    }

    // Helper method to get PIN from the user
    private static int getPin(Scanner scanner) {
        while (true) {
            System.out.print("Enter Account PIN: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("[Invalid Input] PIN must be a number.");
            }
        }
    }

    // Helper method to get deposit amount from the user
    private static double getDepositAmount(Scanner scanner) {
        while (true) {
            System.out.print("Enter Deposit Amount: ");
            try {
                double depositAmount = Double.parseDouble(scanner.nextLine());
                if(depositAmount <= 0.0){
                    System.out.println("[Invalid Deposit Amount] Amount should be > $0.0");
                    continue;
                }

                return depositAmount;
            } catch (NumberFormatException e) {
                System.out.println("[Invalid Input] Deposit amount must be a number.");
            }
        }
    }

    // Helper method to get withdraw amount from the user
    private static double getWithdrawAmount(Scanner scanner) {
        while (true) {
            System.out.print("Enter Withdraw Amount: ");
            try {
                double withdrawAmount = Double.parseDouble(scanner.nextLine());
                if(withdrawAmount <= 0.0){
                    System.out.println("[Invalid Deposit Amount] Amount should be > $0.0");
                    continue;
                }

                return withdrawAmount;
            } catch (NumberFormatException e) {
                System.out.println("[Invalid Input] Deposit amount must be a number.");
            }
        }
    }
}
