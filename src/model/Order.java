package model;

import java.time.LocalDateTime;

public class Order {
    private String OrderID;
    private String salesInvoiceID;
    private LocalDateTime expectedDeliveryTime;
    private String deliveryAddress;
    private String notes;

    public Order(String OrderID, String salesInvoiceID, LocalDateTime expectedDeliveryTime, String deliveryAddress, String notes) {
        this.OrderID = OrderID;
        this.salesInvoiceID = salesInvoiceID;
        this.expectedDeliveryTime = expectedDeliveryTime;
        this.deliveryAddress = deliveryAddress;
        this.notes = notes;
    }

    public String getOrderID() {
        return OrderID;
    }

    public String getHoaDonBanHang() {
        return salesInvoiceID;
    }

    public LocalDateTime getExpectedDeliveryTime() {
        return expectedDeliveryTime;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getNotes() {
        return notes;
    }
}
