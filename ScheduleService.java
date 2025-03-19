package gymmanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
    public void addSchedule(Schedule schedule) {
        String sql = "INSERT INTO Schedules (memberId, trainerId, date, timeSlot) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, schedule.getMemberId());
            pstmt.setInt(2, schedule.getTrainerId());
            pstmt.setDate(3, schedule.getDate());
            pstmt.setTime(4, schedule.getTimeSlot());
            pstmt.executeUpdate();

            // Get generated schedule ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                schedule.setScheduleId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Schedule> getSchedulesByDate(Date date) {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM Schedules WHERE date = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule(rs.getInt("memberId"), rs.getInt("trainerId"), rs.getDate("date"), rs.getTime("timeSlot"));
                schedule.setScheduleId(rs.getInt("scheduleId"));
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }
}