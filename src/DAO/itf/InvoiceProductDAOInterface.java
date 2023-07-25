package DAO.itf;

import model.InvoiceProduct;

import java.util.List;

public interface InvoiceProductDAOInterface {
    int create(InvoiceProduct invoiceProduct);

    List<InvoiceProduct> read();

    int update(InvoiceProduct invoiceProduct);

    int delete(String invoiceID, String productID);

    int delete(String invoiceID);

    List<InvoiceProduct> getBySalesInvoiceID(String salesInvoiceID);
}

