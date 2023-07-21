package model;
import java.util.Date;

public class WarehouseReceipt {
    private String warehouseReceiptID;
    private Date receiptDate;
    private String supplierID;
    private String employeeID;

    public WarehouseReceipt(String warehouseReceiptID, Date receiptDate, String supplierID, String employeeID) {
        this.warehouseReceiptID = warehouseReceiptID;
        this.receiptDate = receiptDate;
        this.supplierID = supplierID;
        this.employeeID = employeeID;
    }

    public String getWarehouseReceiptID() {
        return warehouseReceiptID;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getEmployeeID() {
        return employeeID;
    }
}
