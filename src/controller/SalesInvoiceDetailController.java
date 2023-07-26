package controller;

import DAO.DAOFactory;
import model.InvoiceProduct;
import model.Product;
import model.SalesInvoiceDetail;
import view.View;

import javax.swing.*;
import java.util.Objects;

public class SalesInvoiceDetailController {
    private final DAOFactory daoFactory;

    SalesInvoiceDetailController(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    void process(View view) {
        String ID = JOptionPane.showInputDialog("Enter sales invoice id you want to see details: ");
        Message message = Controller.checkCode(Objects.requireNonNull(view.getTableChooser().getSelectedItem()).toString(), ID);
        if (message.equals(Message.ID_ALREADY_EXISTS)) {
            SalesInvoiceDetail salesInvoiceDetail = getSalesInvoiceDetail(ID);
            if (salesInvoiceDetail == null) {
                View.showMessage(view.getContentPane(), Message.ERROR_OCCURRED.getMessage());
                return;
            }
            view.createSalesInvoiceDetailFrame(salesInvoiceDetail);
        } else {
            View.showMessage(view.getDetailFrame(), message.getMessage());
        }
    }

    SalesInvoiceDetail getSalesInvoiceDetail(String salesInvoicesID) {
        SalesInvoiceDetail salesInvoiceDetail = new SalesInvoiceDetail();
        salesInvoiceDetail.setSalesInvoice(daoFactory.getSalesInvoiceDAO().getBySalesInvoiceID(salesInvoicesID));
        if (salesInvoiceDetail.getSalesInvoice() == null) {
            return null;
        }
        salesInvoiceDetail.setCustomer(daoFactory.getCustomerDAO().getByCustomerID(salesInvoiceDetail.getSalesInvoice().getCustomerID()));
        salesInvoiceDetail.setInvoiceProductList(daoFactory.getInvoiceProductDAO().getBySalesInvoiceID(salesInvoicesID));
        int totalAmount = 0;
        for (InvoiceProduct invoiceProduct : salesInvoiceDetail.getInvoiceProductList()) {
            Product product = daoFactory.getProductDAO().getByProductID(invoiceProduct.getProductID());
            salesInvoiceDetail.getProductList().add(product);
            totalAmount += salesInvoiceDetail.getTotalAmount() + (invoiceProduct.getQuantity() * product.getRetailPrice());
        }
        salesInvoiceDetail.setTotalAmount(totalAmount);
        return salesInvoiceDetail;
    }
}
