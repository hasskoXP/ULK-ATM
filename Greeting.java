import java.util.Scanner;
public class Greeting {
    static final int MAX_ACCOUNTS = 100;
    static final int MAX_TRANSACTIONS = 100;
    static final int MAX_LOGIN_ATTEMPTS = 3;
    static final double STARTING_BALANCE = 1000.0;

    static Scanner input = new Scanner(System.in);

    // Ten sample accounts for classroom demonstrations.
    static String[] bankIds = new String[MAX_ACCOUNTS];
    static String[] pins = new String[MAX_ACCOUNTS];
    static double[] balances = new double[MAX_ACCOUNTS];
    static String[][] transactions = new String[MAX_ACCOUNTS][MAX_TRANSACTIONS];
    static int[] transactionCounts = new int[MAX_ACCOUNTS];
    static int accountCount = 0;
    static int currentAccount = -1;

    public static void main(String[] args) {
        displayProjectInfo();

        // put demo accnts and common pins here
        bankIds[0] = "100001"; pins[0] = "1234";
        bankIds[1] = "100002"; pins[1] = "0000";
        bankIds[2] = "100003"; pins[2] = "1111";
        bankIds[3] = "100004"; pins[3] = "1212";
        bankIds[4] = "100005"; pins[4] = "7777";
        bankIds[5] = "100006"; pins[5] = "2222";
        bankIds[6] = "100007"; pins[6] = "1122";
        bankIds[7] = "100008"; pins[7] = "9999";
        bankIds[8] = "100009"; pins[8] = "5555";
        bankIds[9] = "100010"; pins[9] = "6969";
        accountCount = 10;

        int i = 0;
        while (i < accountCount) {
            balances[i] = STARTING_BALANCE;
            i++;
        }

        int choice = 0;
        // keep asking untill user exit
        while (choice != 4) {
            System.out.println("\n========== ULK ATM ==========");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Forgot Password");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                boolean success = login();
                if (success) {
                    menu();
                }
            } else if (choice == 2) {
                register();
            } else if (choice == 3) {
                forgot_password();
            } else if (choice == 4) {
                System.out.println("Thank you for using ULK ATM!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
        input.close();
    }

    public static void displayProjectInfo() {
        System.out.println("Project Prepared By:");
        System.out.println("Name: Hassan Sami");
        System.out.println("Reg Number: 202510183");
        System.out.println("Course: Introduction to Java Programming");

        // topics I lerned and used in this project
        System.out.println("\nTopics Learned and Applied:");
        System.out.println("1- Variables and Data Types");
        System.out.println("2- User Input using Scanner");
        System.out.println("3- Methods and Parameters");
        System.out.println("4- If and Else Statements");
        System.out.println("5- Logical Operators");
        System.out.println("6- While and For Loops");
        System.out.println("7- Arrays");
        System.out.println("8- Basic Arithmetic Operations");
        System.out.println("9- Constants");
        System.out.println("10- Return Values");

        // resorces I used for learning
        System.out.println("\n===== LEARNING RESOURCES =====");
        System.out.println("1- Telusko - Java Tutorial");
        System.out.println("   YouTube:");
        System.out.println("   https://www.youtube.com/playlist?list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5");
        System.out.println("\n2- W3Schools - Java Tutorial");
        System.out.println("   https://www.w3schools.com/java/default.asp");
    }

    public static int findAccount(String id) {
        int i = 0;
        while (i < accountCount) {
            if (bankIds[i].equals(id)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static void register() {
        if (accountCount >= MAX_ACCOUNTS) {
            System.out.println("No more accounts can be created.");
            return;
        }

        System.out.print("Create Bank ID (6 digits): ");
        String newId = input.next();
        if (!isDigits(newId, 6)) {
            System.out.println("Bank ID must contain exactly 6 digits.");
            return;
        }
        if (findAccount(newId) != -1) {
            System.out.println("This Bank ID already exists.");
            return;
        }

        System.out.print("Create PIN (4 digits): ");
        String newPin = input.next();
        if (!isDigits(newPin, 4)) {
            System.out.println("PIN must contain exactly 4 digits.");
            return;
        }

        bankIds[accountCount] = newId;
        pins[accountCount] = newPin;
        balances[accountCount] = STARTING_BALANCE;
        accountCount++;
        System.out.println("Account created successfully! Starting balance: $" + STARTING_BALANCE);
        System.out.println("Please login.");
    }

    public static boolean login() {
        int attempts = 0;
        while (attempts < MAX_LOGIN_ATTEMPTS) {
            System.out.print("Enter your Bank ID: ");
            String enteredId = input.next();
            System.out.print("Enter your PIN: ");
            String enteredPin = input.next();

            int accountIndex = findAccount(enteredId);
            // check if entered id and pin are corect
            if (accountIndex != -1 && pins[accountIndex].equals(enteredPin)) {
                currentAccount = accountIndex;
                System.out.println("Login successful!");
                return true;
            }

            attempts++;
            if (accountIndex == -1) {
                System.out.println("Bank ID not found. Please try again.");
            } else {
                System.out.println("Incorrect PIN. Please try again.");
            }
            if (attempts < 3) {
                System.out.println("Attempts remaining: " + (3 - attempts));
            }
        }
        System.out.println("You have used all " + MAX_LOGIN_ATTEMPTS + " attempts. Please try again later.");
        return false;
    }

    public static void forgot_password() {
        System.out.print("Enter your Bank ID: ");
        String enteredId = input.next();
        int accountIndex = findAccount(enteredId);

        if (accountIndex == -1) {
            System.out.println("Bank ID not found!");
            return;
        }

        System.out.print("Enter your new PIN (4 digits): ");
        String newPin = input.next();
        if (!isDigits(newPin, 4)) {
            System.out.println("PIN must contain exactly 4 digits.");
            return;
        }

        pins[accountIndex] = newPin;
        System.out.println("PIN updated successfully! Please login again.");
    }

    public static void menu() {
        int choice = 0;
        while (choice != 5) {
            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice == 1) {
                checkBalance();
            } else if (choice == 2) {
                deposit();
            } else if (choice == 3) {
                withdraw();
            } else if (choice == 4) {
                transactionHistory();
            } else if (choice == 5) {
                System.out.println("Logging out ...");
                currentAccount = -1;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public static void checkBalance() {
        System.out.println("Current Balance: $" + balances[currentAccount]);
    }

    public static void deposit() {
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero!");
        } else if (transactionCounts[currentAccount] >= transactions[currentAccount].length) {
            System.out.println("Transaction history is full!");
        } else {
            balances[currentAccount] += amount;
            transactions[currentAccount][transactionCounts[currentAccount]] = "Deposit: +$" + amount;
            transactionCounts[currentAccount]++;
            System.out.println("Deposit successful!");
            checkBalance();
        }
    }

    public static void withdraw() {
        System.out.print("Enter amount: ");
        double amount = input.nextDouble();
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero!");
        } else if (amount > balances[currentAccount]) {
            System.out.println("Insufficient balance!");
        } else if (transactionCounts[currentAccount] >= transactions[currentAccount].length) {
            System.out.println("Transaction history is full!");
        } else {
            balances[currentAccount] -= amount;
            transactions[currentAccount][transactionCounts[currentAccount]] = "Withdrawal: -$" + amount;
            transactionCounts[currentAccount]++;
            System.out.println("Withdrawal successful!");
            checkBalance();
        }
    }

    public static void transactionHistory() {
        System.out.println("\n===== TRANSACTION HISTORY =====");
        if (transactionCounts[currentAccount] == 0) {
            System.out.println("No transactions found.");
        } else {
            // show transations one by one
            for (int i = 0; i < transactionCounts[currentAccount]; i++) {
                System.out.println((i + 1) + ". " + transactions[currentAccount][i]);
            }
        }
    }

    public static boolean isDigits(String value, int requiredLength) {
        if (value.length() != requiredLength) {
            return false;
        }
        int i = 0;
        while (i < value.length()) {
            if (value.charAt(i) < '0' || value.charAt(i) > '9') {
                return false;
            }
            i++;
        }
        return true;
    }
}
