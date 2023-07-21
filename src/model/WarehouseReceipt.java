package model;
import java.util.Date;

public class WarehouseReceipt {
    private String warehouseReceiptID;
    private Date warehouReceiptDate;

    private String warehouseReceiptStatus;
    private String supplierID;
    private String employeeID;

    public WarehouseReceipt(String warehouseReceiptID, Date warehouReceiptDate, String warehouseReceiptStatus, String supplierID, String employeeID) {
        this.warehouseReceiptID = warehouseReceiptID;
        this.warehouReceiptDate = warehouReceiptDate;
        this.warehouseReceiptStatus = warehouseReceiptStatus;
        this.supplierID = supplierID;
        this.employeeID = employeeID;
    }

    public String getWarehouseReceiptID() {
        return warehouseReceiptID;
    }

    public Date getWarehouReceiptDate() {
        return warehouReceiptDate;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getWarehouseReceiptStatus() {
        return warehouseReceiptStatus;
    }
}
