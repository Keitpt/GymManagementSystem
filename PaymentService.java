package gymmanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    public void addPayment(Payment payment) {
        String sql = "INSERT INTO Payments (memberId, amount, date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, payment.getMemberId());
            pstmt.setDouble(2, payment.getAmount());
            pstmt.setDate(3, payment.getDate());
            pstmt.executeUpdate();

            // Get generated payment ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                payment.setPaymentId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getPaymentsByMemberId(int memberId) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Payments WHERE memberId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(rs.getInt("memberId"), rs.getDouble("amount"), rs.getDate("date"));
                payment.setPaymentId(rs.getInt("paymentId"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public double getTotalRevenueByMonth(int month, int year) {
        double totalRevenue = 0;
        String sql = "SELECT SUM(amount) AS total FROM Payments WHERE MONTH(date) = ? AND YEAR(date) = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, month);
            pstmt.setInt(2, year);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                totalRevenue = rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalRevenue;
    }
}