package gymmanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceTrainer {
        public void addTrainer(Trainer trainer) {
        String sql = "INSERT INTO Trainers (name, specialization) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, trainer.getName());
            pstmt.setString(2, trainer.getSpecialization());
            pstmt.executeUpdate();

            // Get generated trainer ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                trainer.setTrainerId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTrainer(Trainer trainer) {
        String sql = "UPDATE Trainers SET name = ?, specialization = ? WHERE trainerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, trainer.getName());
            pstmt.setString(2, trainer.getSpecialization());
            pstmt.setInt(3, trainer.getTrainerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTrainer(int trainerId) {
        String sql = "DELETE FROM Trainers WHERE trainerId = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, trainerId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Trainer> getAllTrainers() {
        List<Trainer> trainers = new ArrayList<>();
        String sql = "SELECT * FROM Trainers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Trainer trainer = new Trainer(rs.getString("name"), rs.getString("specialization"));
                trainer.setTrainerId(rs.getInt("trainerId"));
                trainers.add(trainer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trainers;
    }
}