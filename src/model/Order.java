package model;

import java.util.Date;

public class Order {
    private String OrderID;
    private String invoiceID;
    private Date expectedDeliveryDate;
    private String deliveryAddress;
    private String notes;

    public Order(String OrderID, String invoiceID, Date expectedDeliveryDate, String deliveryAddress, String notes) {
        this.OrderID = OrderID;
        this.invoiceID = invoiceID;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.notes = notes;
    }

    public Order() {

    }

    public String getOrderID() {
        return OrderID;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getNotes() {
        return notes;
    }
}
