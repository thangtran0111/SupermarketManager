package model;

import java.util.Date;

public class Customer {
    private String customerID;
    private String customerName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private int loyaltyPoints;
    private final static double LOYALTY_POINTS_RATIO = 1.0/100000;

    public Customer(String customerID, String customerName, Date dateOfBirth, String phoneNumber, String email, int loyaltyPoints) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Customer() {

    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }


    public static int getIncreaseLoyaltyPoints(int money) {
        double loyaltyPoints = money * LOYALTY_POINTS_RATIO;
        return (int) Math.round(loyaltyPoints);
    }

    public void increaseLoyaltyPoints(int money){
        loyaltyPoints +=  getIncreaseLoyaltyPoints(money);
    }

}

