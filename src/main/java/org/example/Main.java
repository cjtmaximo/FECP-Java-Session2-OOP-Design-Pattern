package org.example;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

                        // accountType
                        System.out.print("Enter Account Type (savings/checking): ");
                        accountType = scanner.nextLine().toLowerCase();

                        if(!accountType .equals("savings") && !accountType .equals("checking")){
                            System.out.println("[Invalid input] Please enter 'savings' or 'checking'.");
                            break;
                        }

                        // account number
                        System.out.print("Enter Account Number: ");
                        try{
                            accountNumber = Integer.parseInt(scanner.nextLine());
                            // check if account already exists
                            if (BankManager.accountNumberExists(accountNumber)) {
                                System.out.println("[Invalid Account Number] Account number already exists.");
                                break;
                            }
                        } catch(NumberFormatException e){
                            System.out.println("[Invalid Account Number]");
                            break;
                        }

                        // account name
                        System.out.print("Enter Account Name: ");
                        name = scanner.nextLine();

                        // ask for pin
                        System.out.print("Enter Account PIN (4-digit): ");
                        try{
                            pin = Integer.parseInt(scanner.nextLine());

                            if(String.valueOf(pin).length() != 4){
                                System.out.println("[Invalid PIN] PIN must be 4 digits.");
                                break;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("[Invalid PIN]");
                            break;
                        }

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

                        BankManager manager = new BankManager();
                        BankAccount newAccount = manager.createAccount(accountType, accountNumber, name, pin, initialDeposit);
                        System.out.println("\n[GBank Account Created Successfully] \nAccount Number: " + accountNumber +"\nAccount Type: " + accountType + "\nName: " + name + "\nBalance: $" + newAccount.getBalance());
                        break;

                    case 2:
                        // deposit
                        break;
                    case 3:
                        // withdraw
                        break;
                    case 4:
                        // compute interest
                        break;
                    case 5:
                        // display account
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
}
