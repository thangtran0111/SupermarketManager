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

    public String getProductID() {
        return productID;
    }

    public int getQuantity() {
        return quantity;
    }

}
