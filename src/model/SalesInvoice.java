package model;

import java.util.Date;

public class SalesInvoice {
    private String invoiceID;
    private Date invoiceDate;
    private String customerID;
    private String paymentMethod;


    public SalesInvoice(String invoiceID, Date invoiceDate, String customerID, String paymentMethod) {
        this.invoiceID = invoiceID;
        this.invoiceDate = invoiceDate;
        this.customerID = customerID;
        this.paymentMethod = paymentMethod;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

