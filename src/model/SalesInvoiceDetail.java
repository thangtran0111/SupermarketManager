package model;

import java.util.List;

public class SalesInvoiceDetail {
    private SalesInvoice salesInvoice;
    private List<InvoiceProduct> invoiceProductList;
    private List<Product> productList;

    public SalesInvoiceDetail(SalesInvoice salesInvoice, List<InvoiceProduct> invoiceProductList, List<Product> productList){
        this.salesInvoice = salesInvoice;
        this.invoiceProductList = invoiceProductList;
        this.productList = productList;
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
}
