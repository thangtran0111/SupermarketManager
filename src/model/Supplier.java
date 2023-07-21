package model;

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

    public String getSupplierName() {
        return supplierName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }


}

