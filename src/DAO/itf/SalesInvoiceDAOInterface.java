package DAO.itf;

import model.SalesInvoice;

import java.util.List;

public interface SalesInvoiceDAOInterface {
    int create(SalesInvoice salesInvoice);

    List<SalesInvoice> read();

    int update(SalesInvoice salesInvoice);

    int delete(String saleInvoiceID);

    SalesInvoice getBySalesInvoiceID(String salesInvoiceID);

    SalesInvoice getByCustomerID(String customerID);
}
