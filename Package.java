package gymmanagementsystem;

public class Package {
    private int packageId;
    private String name;
    private double price;
    private int duration; // Duration in months

    public Package(String name, double price, int duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    // Getters and Setters
    public int getPackageId() { return packageId; }
    public void setPackageId(int packageId) { this.packageId = packageId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getDuration() { return duration; }
}
   
