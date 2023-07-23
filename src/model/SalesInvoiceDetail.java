package model;

import java.util.ArrayList;
import java.util.List;

public class SalesInvoiceDetail {
    private SalesInvoice salesInvoice;
    private Customer customer;
    private List<InvoiceProduct> invoiceProductList;
    private List<Product> productList;
    private int totalAmount;

    public SalesInvoiceDetail(SalesInvoice salesInvoice, Customer customer, List<InvoiceProduct> invoiceProductList, List<Product> productList, int totalAmount){
        this.salesInvoice = salesInvoice;
        this.customer = customer;
        this.invoiceProductList = invoiceProductList;
        this.productList = productList;
        this.totalAmount = totalAmount;
    }

    public SalesInvoiceDetail() {
        salesInvoice = new SalesInvoice();
        customer = new Customer();
        invoiceProductList = new ArrayList<>();
        productList = new ArrayList<>();
        totalAmount = 0;
    }

    public SalesInvoice getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public List<InvoiceProduct> getInvoiceProductList() {
        return invoiceProductList;
    }

    public void setInvoiceProductList(List<InvoiceProduct> invoiceProductList) {
        this.invoiceProductList = invoiceProductList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
         this.totalAmount = totalAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
