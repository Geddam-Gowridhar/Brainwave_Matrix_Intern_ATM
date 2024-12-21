package Task1;

import java.util.Scanner;

public class ATM {
    private Account[] accounts;
    private Scanner scanner;

    // Constructor to initialize accounts and scanner
    public ATM() {
        scanner = new Scanner(System.in);
        accounts = new Account[] {
            new Account("12345", "1234", 1000.0), // Predefined account 1
            new Account("67890", "5678", 1500.0)  // Predefined account 2
        };
    }

    // Start ATM system and prompt user for login
    public void start() {
        System.out.println("Welcome to the ATM");
        Account loggedInAccount = null;

        // Login Phase
        while (loggedInAccount == null) {
            System.out.print("Enter Account Number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            for (Account account : accounts) {
                if (accountNumber.equals(account.getAccountNumber()) && account.validatePin(pin)) {
                    loggedInAccount = account;
                    break;
                }
            }

            if (loggedInAccount == null) {
                System.out.println("Invalid Account Number or PIN. Please try again.");
            }
        }

        // Main Menu Phase
        boolean exit = false;
        while (!exit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    System.out.println("Balance: " + loggedInAccount.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    loggedInAccount.deposit(depositAmount);
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    if (loggedInAccount.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
