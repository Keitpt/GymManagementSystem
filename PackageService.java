package gymmanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageService {
    public void addPackage(Package pkg) {
        String sql = "INSERT INTO Packages (name, price, duration) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, pkg.getName());
            pstmt.setDouble(2, pkg.getPrice());
            pstmt.setInt(3, pkg.getDuration());
            pstmt.executeUpdate();

            // Get generated package ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                pkg.setPackageId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePackage(Package pkg) {
        String sql = "UPDATE Packages SET name = ?, price = ?, duration = ? WHERE packageId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pkg.getName());
            pstmt.setDouble(2, pkg.getPrice());
            pstmt.setInt(3, pkg.getDuration());
            pstmt.setInt(4, pkg.getPackageId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePackage(int packageId) {
        String sql = "DELETE FROM Packages WHERE packageId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, packageId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Package> getAllPackages() {
        List<Package> packages = new ArrayList<>();
        String sql = "SELECT * FROM Packages";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Package pkg = new Package(rs.getString("name"), rs.getDouble("price"), rs.getInt("duration"));
                pkg.setPackageId(rs.getInt("packageId"));
                packages.add(pkg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return packages;
    }
}