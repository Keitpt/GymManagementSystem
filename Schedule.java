package gymmanagementsystem;


import java.sql.Date;
import java.sql.Time;

public class Schedule {
    private int scheduleId;
    private int memberId;
    private int trainerId;
    private Date date;
    private Time timeSlot;

    public Schedule(int memberId, int trainerId, Date date, Time timeSlot) {
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.date = date;
        this.timeSlot = timeSlot;
    }

    // Getters and Setters
    public int getScheduleId() { return scheduleId; }
    public void setScheduleId(int scheduleId) { this.scheduleId = scheduleId; }
    public int getMemberId() { return memberId; }
    public int getTrainerId() { return trainerId; }
    public Date getDate() { return date; }
    public Time getTimeSlot() { return timeSlot; }
}
