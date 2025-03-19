package gymmanagementsystem;

public class Trainer {
    private int trainerId;
    private String name;
    private String specialization;

    public Trainer(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    // Getters and Setters
    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
}