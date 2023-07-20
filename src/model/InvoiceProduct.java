package model;

public class InvoiceProduct {
    private String invoiceID;
    private String productID;
    private int quantity;

    public InvoiceProduct(String invoiceID, String productID, int quantity) {
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.quantity = quantity;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
