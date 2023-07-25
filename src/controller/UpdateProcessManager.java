package controller;

import DAO.DAOFactory;

import model.*;
import view.View;

import java.util.Objects;

public class UpdateProcessManager {
    private final DAOFactory daoFactory;

    UpdateProcessManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;

    }

    void processUpdate(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        if (Objects.requireNonNull(selectedTable).equals(view.getTableName(0))) return;
        MessageCode message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if (message.equals(MessageCode.ID_ALREADY_EXISTS) || message.equals(MessageCode.BARCODE_ALREADY_EXISTS)) {
            if (Objects.requireNonNull(selectedTable).equals(view.getTableName(1))) {
                Employee employee = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getEmployeeDAO().update(employee);
            } else if (selectedTable.equals(view.getTableName(2))) {
                Product product = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getProductDAO().update(product);
            } else if (selectedTable.equals(view.getTableName(3))) {
                SalesInvoice salesInvoice = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getSalesInvoiceDAO().update(salesInvoice);
            } else if (selectedTable.equals(view.getTableName(4))) {
                InvoiceProduct invoiceProduct = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getInvoiceProductDAO().update(invoiceProduct);
            } else if (selectedTable.equals(view.getTableName(5))) {
                Customer customer = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getCustomerDAO().update(customer);
            } else if (selectedTable.equals(view.getTableName(6))) {
                Supplier supplier = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getSupplierDAO().update(supplier);
            } else if (selectedTable.equals(view.getTableName(7))) {
                Order order = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getOrderDAO().update(order);
            } else if (selectedTable.equals(view.getTableName(8))) {
                DeliveryReceipt deliveryReceipt = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getDeliveryReceiptDAO().update(deliveryReceipt);
            } else if (selectedTable.equals(view.getTableName(9))) {
                SupplyRequest supplyRequest = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getSupplyRequestDAO().update(supplyRequest);
            } else if (selectedTable.equals(view.getTableName(10))) {
                ProductRequest productRequest = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getProductRequestDAO().update(productRequest);
            }
        } else {
            View.showMessage(view.getAddFrame(), message.getMessage());
            view.clearNewsValue();
        }
        if (count == 0) {
            View.showMessage(view.getUpdateFrame(), MessageCode.ERROR_OCCURRED.getMessage());
        } else {
            View.showMessage(view.getUpdateFrame(), MessageCode.SUCCESS.getMessage());
        }
    }
}