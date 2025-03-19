package gymmanagementsystem;

import java.sql.Date;

import java.sql.Date;

public class Payment {
    private int paymentId;
    private int memberId;
    private double amount;
    private Date date;

    public Payment(int memberId, double amount, Date date) {
        this.memberId = memberId;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public int getMemberId() { return memberId; }
    public double getAmount() { return amount; }
    public Date getDate() { return date; }
}