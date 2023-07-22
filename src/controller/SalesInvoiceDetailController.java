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
import view.View;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SalesInvoiceDetailController {
    private final SalesInvoiceDAOInterface salesInvoiceDAO;
    private final InvoiceProductDAOInterface invoiceProductDAO;
    private final ProductDAOInterface productDAO;

    SalesInvoiceDetailController(){
        salesInvoiceDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        productDAO = new ProductDAO();
    }

    void process(View view){
        String ID = JOptionPane.showInputDialog("Enter sales invoice id you want to see details: ");
        MessageCode message = Controller.checkCode(view.getTableChooser().getSelectedItem().toString(), ID);
        if(message.equals(MessageCode.ID_ALREADY_EXISTS)){
            view.createSalesInvoiceDetailFrame(getSalesInvoiceDetail(ID));
        }else{
            View.showMessage(view.getSalesInvoiceDetailFrame(), message.getMessage());
        }
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
