package controller;

import DAO.imple.InvoiceProductDAO;
import DAO.imple.ProductDAO;
import DAO.imple.SalesInvoiceDAO;
import DAO.itf.InvoiceProductDAOInterface;
import DAO.itf.ProductDAOInterface;
import DAO.itf.SalesInvoiceDAOInterface;
import model.InvoiceProduct;
import model.Product;
import model.SalesInvoice;
import model.SalesInvoiceDetail;

import java.util.ArrayList;
import java.util.List;

public class SalesInvoiceDetailController {
    private SalesInvoiceDAOInterface salesInvoiceDAO;
    private InvoiceProductDAOInterface invoiceProductDAO;
    private ProductDAOInterface productDAO;

    SalesInvoiceDetailController(){
        salesInvoiceDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        productDAO = new ProductDAO();
    }

    SalesInvoiceDetail getSalesInvoiceDetail(String salesInvoicesID){
        SalesInvoice salesInvoice = salesInvoiceDAO.get(salesInvoicesID);
        if(salesInvoice == null){
            return null;
        }
        List<InvoiceProduct> invoiceProductList = invoiceProductDAO.get(salesInvoicesID);
        if(invoiceProductList.isEmpty()){
            return null;
        }
        List<Product> productList = new ArrayList<>();
        for (InvoiceProduct invoiceProduct :invoiceProductList) {
            productList.add(productDAO.get(invoiceProduct.getProductID()));
        }

        return new SalesInvoiceDetail(salesInvoice, invoiceProductList, productList);
    }
}
