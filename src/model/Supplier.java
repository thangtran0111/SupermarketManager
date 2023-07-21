package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Supplier {
    private String supplierID;
    private String supplierName;
    private String phoneNumber;
    private String email;
    private String address;

    public Supplier(String supplierID, String supplierName, String phoneNumber, String email, String address) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}

