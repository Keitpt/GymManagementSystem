package gymmanagementsystem;

public class Member {
    private int memberId;
    private String name;
    private String phone;
    private String email;
    private int packageId;

    public Member(String name, String phone, String email, int packageId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.packageId = packageId;
    }

    // Getters and Setters
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public int getPackageId() { return packageId; }
}