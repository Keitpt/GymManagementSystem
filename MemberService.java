package gymmanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    public void addMember(Member member) {
        String sql = "INSERT INTO Members (name, phone, email, packageId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getEmail());
            pstmt.setInt(4, member.getPackageId());
            pstmt.executeUpdate();

            // Get generated member ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                member.setMemberId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMember(Member member) {
        String sql = "UPDATE Members SET name = ?, phone = ?, email = ?, packageId = ? WHERE memberId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPhone());
            pstmt.setString(3, member.getEmail());
            pstmt.setInt(4, member.getPackageId());
            pstmt.setInt(5, member.getMemberId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(int memberId) {
        String sql = "DELETE FROM Members WHERE memberId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Member> searchMembersByName(String name) {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Members WHERE name LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Member member = new Member(rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getInt("packageId"));
                member.setMemberId(rs.getInt("memberId"));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM Members";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Member member = new Member(rs.getString("name"), rs.getString("phone"), rs.getString("email"), rs.getInt("packageId"));
                member.setMemberId(rs.getInt("memberId"));
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }
}