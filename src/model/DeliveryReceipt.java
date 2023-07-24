package model;

import java.util.Date;

public class DeliveryReceipt {
    private String deliveryReceiptID;
    private Date deliveryDate;
    private String deliveryStatus;
    private int deliveryFee;
    private String orderID;
    private String deliveryEmployeeID;

    public DeliveryReceipt(String deliveryReceiptID, Date deliveryDate, String deliveryStatus, int deliveryFee, String orderID, String deliveryEmployeeID) {
        this.deliveryReceiptID = deliveryReceiptID;
        this.deliveryDate = deliveryDate;
        this.deliveryStatus = deliveryStatus;
        this.deliveryFee = deliveryFee;
        this.orderID = orderID;
        this.deliveryEmployeeID = deliveryEmployeeID;
    }

    public DeliveryReceipt() {

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

    public int getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(int deliveryFee) {
        this.deliveryFee = deliveryFee;
    }
}
