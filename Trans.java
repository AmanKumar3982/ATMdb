import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Trans {
    public void Trans_debit(long accountId , double amount) throws SQLException{
        String query = "INSERT INTO transactions (account_id, type, amount) VALUES ( ?, 'debit', ?); ";
        try (Connection connect = DBConnection.getConnection();PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setLong(1, accountId);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
        }    
    }
    public void Trans_credit(long accountId , double amount) throws SQLException{
        String query = "INSERT INTO transactions (account_id, type, amount) VALUES ( ?, 'credit', ?); ";
        try (Connection connect = DBConnection.getConnection();PreparedStatement stmt = connect.prepareStatement(query)) {
            stmt.setLong(1, accountId);
            stmt.setDouble(2, amount);
            stmt.executeUpdate();
        }
    }
    public void printTransactionHistory(long accountId) throws SQLException {
        String query = "SELECT type, amount, timestamp FROM transactions WHERE account_id = ? ORDER BY timestamp DESC";
        try (Connection connect = DBConnection.getConnection();
             PreparedStatement pstmt = connect.prepareStatement(query)) {
            pstmt.setLong(1, accountId);
            try (ResultSet rs = pstmt.executeQuery()) {  
                if (!rs.isBeforeFirst()) {
                    System.out.println("No transactions found for account ID: " + accountId);
                    return;
                }
                System.out.println("Transaction History for Account ID: " + accountId);
                System.out.println("------------------------------------------------------------");
                System.out.printf("%-20s %-15s %-25s%n", "Transaction Type", "Amount", "Date");
                System.out.println("------------------------------------------------------------");
                while (rs.next()) {
                    String transactionType = rs.getString("type");
                    double amount = rs.getDouble("amount");
                    String timestamp = rs.getString("timestamp");
                    System.out.printf("%-20s %-15.2f %-25s%n", transactionType, amount, timestamp);
                }
                System.out.println("------------------------------------------------------------");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
