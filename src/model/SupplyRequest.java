package model;
import java.util.Date;

public class SupplyRequest {
    private String supplyRequestID;
    private Date supplyRequestDate;
    private String supplyRequestStatus;
    private Date receiveDate;
    private String supplierID;
    private String employeeID;

    public SupplyRequest(String supplyRequestID, Date supplyRequestDate, String supplyRequestStatus, Date receiveDate, String supplierID, String employeeID) {
        this.supplyRequestID = supplyRequestID;
        this.supplyRequestDate = supplyRequestDate;
        this.supplyRequestStatus = supplyRequestStatus;
        this.receiveDate = receiveDate;
        this.supplierID = supplierID;
        this.employeeID = employeeID;
    }

    public SupplyRequest() {

    }

    public String getSupplyRequestID() {
        return supplyRequestID;
    }

    public Date getSupplyRequestDate() {
        return supplyRequestDate;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getSupplyRequestStatus() {
        return supplyRequestStatus;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }
}
