package controller;

import DAO.imple.CustomerDAO;
import DAO.imple.InvoiceProductDAO;
import DAO.imple.ProductDAO;
import DAO.imple.SalesInvoiceDAO;
import DAO.itf.CustomerDAOInterface;
import DAO.itf.InvoiceProductDAOInterface;
import DAO.itf.ProductDAOInterface;
import DAO.itf.SalesInvoiceDAOInterface;
import model.InvoiceProduct;
import model.Product;
import model.SalesInvoiceDetail;
import view.View;

import javax.swing.*;
import java.util.Objects;

public class SalesInvoiceDetailController {
    private final SalesInvoiceDAOInterface salesInvoiceDAO;
    private final CustomerDAOInterface customerDAO;
    private final InvoiceProductDAOInterface invoiceProductDAO;
    private final ProductDAOInterface productDAO;

    SalesInvoiceDetailController(){
        salesInvoiceDAO = new SalesInvoiceDAO();
        customerDAO = new CustomerDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        productDAO = new ProductDAO();
    }

    void process(View view){
        String ID = JOptionPane.showInputDialog("Enter sales invoice id you want to see details: ");
        MessageCode message = Controller.checkCode(Objects.requireNonNull(view.getTableChooser().getSelectedItem()).toString(), ID);
        if(message.equals(MessageCode.ID_ALREADY_EXISTS)){
            SalesInvoiceDetail salesInvoiceDetail = getSalesInvoiceDetail(ID);
            if(salesInvoiceDetail == null){
                View.showMessage(view.getContentPane(), MessageCode.ERROR_OCCURRED.getMessage());
                return;
            }
            view.createSalesInvoiceDetailFrame(salesInvoiceDetail);
        }else{
            View.showMessage(view.getDetailFrame(), message.getMessage());
        }
    }
    SalesInvoiceDetail getSalesInvoiceDetail(String salesInvoicesID){
        SalesInvoiceDetail salesInvoiceDetail = new SalesInvoiceDetail();
        salesInvoiceDetail.setSalesInvoice(salesInvoiceDAO.get(salesInvoicesID));
        if(salesInvoiceDetail.getSalesInvoice() == null){
            return null;
        }
        salesInvoiceDetail.setCustomer(customerDAO.get(salesInvoiceDetail.getSalesInvoice().getCustomerID()));
        salesInvoiceDetail.setInvoiceProductList(invoiceProductDAO.get(salesInvoicesID));
        int totalAmount = 0;
        for (InvoiceProduct invoiceProduct : salesInvoiceDetail.getInvoiceProductList()) {
            Product product = productDAO.get(invoiceProduct.getProductID());
            salesInvoiceDetail.getProductList().add(product);
            totalAmount += salesInvoiceDetail.getTotalAmount() + (invoiceProduct.getQuantity() * product.getRetailPrice());
        }
        salesInvoiceDetail.setTotalAmount(totalAmount);
        return salesInvoiceDetail;
    }
}
