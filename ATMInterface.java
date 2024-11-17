import java.util.Scanner;

// Class representing a Bank Account
class BankAccount {
    private double balance;

    // Constructor to initialize balance
    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit amount
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Method to withdraw amount
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
            return true;
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
        return false;
    }

    // Method to check balance
    public double getBalance() {
        return balance;
    }
}

// Class representing the ATM Machine
class ATM {
    private BankAccount account;

    // Constructor to link a bank account
    public ATM(BankAccount account) {
        this.account = account;
    }

    // Display the ATM menu
    public void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    // Method to handle user operations
    public void start(String userName, String phoneNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome, " + userName + "!");
        System.out.println("Registered Phone Number: " + phoneNumber);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Check balance
                    System.out.println("Your current balance is: ₹" + account.getBalance());
                    break;
                case 2: // Deposit money
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3: // Withdraw money
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 4: // Exit
                    System.out.println("Thank you for using the ATM, " + userName + ". Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}

// Main class
public class ATMInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user details
        System.out.print("Enter your name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        // Initializing the bank account with a starting balance
        BankAccount myAccount = new BankAccount(1000.0);

        // Linking the bank account to the ATM
        ATM myATM = new ATM(myAccount);

        // Start the ATM interface
        myATM.start(userName, phoneNumber);
    }
}
