import java.sql.SQLException;
import java.util.*;

public class Atm_main {

    public void deposit_amt(long accountId, double amount) throws SQLException {
        Account a = new Account();
        Trans t = new Trans();
        try {
            a.update_bal_credit(accountId, amount);
            t.Trans_credit(accountId, amount);

            System.out.println("Account " + accountId + "has been credited with RS " + amount);
        } catch (SQLException e) {
            System.out.println("Invalid Transaction");
            e.printStackTrace();
        }
    }

    public void withdraw_amt(long accountId, double amount) throws SQLException {
        Account a = new Account();
        Trans t = new Trans();

        try {
            a.update_bal_debit(accountId, amount);
            t.Trans_debit(accountId, amount);
            System.out.println("Account " + accountId + " has been creditd by Rs" + amount);
        } catch (SQLException e) {
            System.out.println("Invalid Transaction");
        }
    }

    public double check_balance(long accountId) throws SQLException {
        Account a = new Account();
        return a.get_balance(accountId);
    }

    public void Transaction_history(long accountId) {
        Trans t = new Trans();
        try {
            t.printTransactionHistory(accountId);
        } catch (SQLException e) {
            System.out.println("Invalid Transaction");
            e.printStackTrace();
        }
    }
    public boolean isNotAlphabet(String pin) {
    return pin.matches("\\d+");  // Returns true if the PIN contains only numbers
}

    public void change_pin(long accountId) throws SQLException {
        Account ac = new Account();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter new PIN: ");
            String newPin = sc.nextLine();
            System.out.print("\nConfirm new PIN: ");
            String confirmPin = sc.nextLine();

            // Check if both entered PINs are the same and contain only digits
            if (newPin.equals(confirmPin) && isNotAlphabet(newPin)) {
                ac.change_pin(accountId, confirmPin);
                System.out.println("PIN changed successfully.");
            } else if (!isNotAlphabet(newPin)) {
                System.out.println("PIN must contain only numbers.");
            } else {
                System.out.println("PINs do not match.");
            }
        }
    }


}
