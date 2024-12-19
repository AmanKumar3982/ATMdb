import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
    public void crearte_account (long accountId, String pin, double balance, String name) throws SQLException {
        Trans t = new Trans();

        Connection connect = DBConnection.getConnection();
        String query = "INSERT INTO accounts (id, pin, balance, name) VALUES (?, ?, ?, ?)";
        // t.Trans_credit(accountId, balance);

        try (PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setLong(1, accountId);
            pstmt.setString(2, pin);
            pstmt.setDouble(3, balance);
            pstmt.setString(4, name);
            pstmt.executeUpdate();
            System.out.println(accountId);
            t.Trans_credit(accountId, balance);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public double get_balance(long accountId) throws SQLException {
        String query = "SELECT balance FROM accounts WHERE id = ?";
        try (Connection connect = DBConnection.getConnection();
                PreparedStatement stmt = connect.prepareStatement(query)) {

            stmt.setLong(1, accountId);
            try (ResultSet r = stmt.executeQuery()) {
                if (r.next()) {
                    return r.getDouble("balance");
                }
            }
        }
        return 0.0;
        
    }
    public void update_bal_credit(long accountId , double upbal) throws SQLException{
        String query = "UPDATE accounts SET balance = balance + ? where id = ?";
        try (Connection connect = DBConnection.getConnection();PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setDouble(1, upbal);
            stmt.setLong(2, accountId);
            stmt.executeUpdate();
        }

            
    }
    public void update_bal_debit(long accountId , double upbal) throws SQLException{
        String query = "UPDATE accounts SET balance = balance - ? where id = ?";
        try (Connection connect = DBConnection.getConnection();PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setDouble(1, upbal);
            stmt.setLong(2, accountId);
            stmt.executeUpdate();
        }     
    }
    public boolean validatePin(long account_id, String pin) throws SQLException {
        String query = "SELECT pin FROM accounts WHERE id = ?";

        try (Connection connect = DBConnection.getConnection();
             PreparedStatement stmt = connect.prepareStatement(query)) {

            stmt.setLong(1, account_id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPin = rs.getString("pin");
                    return storedPin.equals(pin);  // Return true if PIN matches, otherwise false
                } else {
                    System.out.println("Account ID not found.");
                    return false;
                }
            }
        }
    }
    public void change_pin(long account_id , String newpin) throws SQLException{
        String query = "UPDATE accounts SET pin = ? where id = ?";
        try (Connection connect = DBConnection.getConnection();PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setString(1, newpin);
            stmt.setLong(2, account_id);
            stmt.executeUpdate();
        }  
    }
    
}