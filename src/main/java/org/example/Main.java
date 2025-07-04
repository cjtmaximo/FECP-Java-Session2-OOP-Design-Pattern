package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Welcome to GBank ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Compute Interest");
            System.out.println("5. Display Account");
            System.out.println("6. Exit\n");

            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
//                scanner.nextLine();   // consume the leftover newline

                switch(choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        System.out.println("\n---Thank you for using GBank!---");
                        System.exit(0);
                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 5.\n");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.\n");
            }
        }
    }
}