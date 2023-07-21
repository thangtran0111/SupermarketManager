package model;

import java.util.Date;

public class Customer {
    private String customerID;
    private String customerName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private int loyaltyPoints;

    public Customer(String customerID, String customerName, Date dateOfBirth, String phoneNumber, String email, int loyaltyPoints) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.loyaltyPoints = loyaltyPoints;
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
}

