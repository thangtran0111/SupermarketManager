package model;

import java.time.LocalDateTime;

public class DeliveryReceipt {
    private String deliveryReceiptID;
    private LocalDateTime deliveryTime;
    private String deliveryStatus;
    private String orderID;
    private String deliveryEmployeeID;

    public DeliveryReceipt(String deliveryReceiptID, LocalDateTime deliveryTime, String deliveryStatus, String orderID, String deliveryEmployeeID) {
        this.deliveryReceiptID = deliveryReceiptID;
        this.deliveryTime = deliveryTime;
        this.deliveryStatus = deliveryStatus;
        this.orderID = orderID;
        this.deliveryEmployeeID = deliveryEmployeeID;
    }

    public String getDeliveryReceiptID() {
        return deliveryReceiptID;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
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
