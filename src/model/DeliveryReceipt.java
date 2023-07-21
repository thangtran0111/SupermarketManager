package model;

import java.util.Date;

public class DeliveryReceipt {
    private String deliveryReceiptID;
    private Date deliveryDate;
    private String deliveryStatus;
    private String orderID;
    private String deliveryEmployeeID;

    public DeliveryReceipt(String deliveryReceiptID, Date deliveryDate, String deliveryStatus, String orderID, String deliveryEmployeeID) {
        this.deliveryReceiptID = deliveryReceiptID;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
        this.orderID = orderID;
        this.deliveryEmployeeID = deliveryEmployeeID;
    }

    public String getDeliveryReceiptID() {
        return deliveryReceiptID;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getDeliveryEmployeeID() {
        return deliveryEmployeeID;
    }
}
