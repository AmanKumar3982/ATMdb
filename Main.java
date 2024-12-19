import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public void showATMMenu(long accountId) throws SQLException {
        Atm_main am = new Atm_main();
        try (Scanner sc = new Scanner(System.in)) {
            boolean keepRunning = true;

            while (keepRunning) {
                System.out.println("ATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Amount");
                System.out.println("3. Withdraw Amount");
                System.out.println("4. Transaction history");
                System.out.println("6. Exit");

                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        double balance = am.check_balance(accountId);
                        System.out.println("Current Balance: " + balance);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = sc.nextDouble();
                        am.deposit_amt(accountId, depositAmount);
                        System.out.println("Amount deposited successfully.");
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = sc.nextDouble();
                        am.withdraw_amt(accountId, withdrawAmount);
                        System.out.println("Amount withdrawn successfully.");
                        break;
                    case 4:
                        am.Transaction_history(accountId);
                        break;
                    case 5:
                        am.change_pin(accountId);
                        break;
                    case 6:
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }

    public void signup() throws SQLException {
        Account account = new Account();
        Trans t = new Trans();
    
        Atm_main am = new Atm_main();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
    
            System.out.print("Enter your initial deposit amount: ");
            double balance = sc.nextDouble();
            sc.nextLine(); // Consume leftover newline after nextDouble()
    
            System.out.print("\nEnter PIN: ");
            String pin = sc.nextLine();
    
            System.out.print("\nConfirm PIN: ");
            String confPin = sc.nextLine();
    
            CreateAccNo can = new CreateAccNo();
    
            if (pin.equals(confPin) && am.isNotAlphabet(confPin)) {
                account.crearte_account(can.generateAccountNumber(), confPin, balance, name);
                System.out.println("Account created successfully.");
            } else if (!am.isNotAlphabet(confPin)) {
                System.out.println("PIN must contain only numbers.");
            } else {
                System.out.println("PINs do not match.");
            }
        }
    }

    public void signIn() throws SQLException {
        Account Account = new Account();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Account ID: ");
            long accountId = scanner.nextLong();

            System.out.print("Enter PIN: ");
            String enteredPin = scanner.next();

            if (Account.validatePin(accountId, enteredPin)) {
                System.out.println("Login successful. Access granted to ATM functions.");
                showATMMenu(accountId);
            } else {
                System.out.println("Invalid PIN or Account ID.");
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        Main m = new Main();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1. Create Account");
                System.out.println("2. Sign In");
                System.out.println("3. Exit");
                System.out.println("Enter Choice: ");
                // int input = sc.nextInt();
                if (sc.hasNextInt()) {
                    int input = sc.nextInt();
                    switch (input) {
                        case 1:
                            m.signup();
                            break;
                        case 2:
                            m.signIn();
                            break;
                        case 3:
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.next(); // Clear the invalid input
                }
            }
        }
    }
}