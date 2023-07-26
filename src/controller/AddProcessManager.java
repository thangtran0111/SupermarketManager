package controller;

import DAO.DAOFactory;

import model.*;
import view.View;

import java.util.Objects;

public class AddProcessManager {
    private final DAOFactory daoFactory;

    AddProcessManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    void processAdd(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        if (Objects.requireNonNull(selectedTable).equals(view.getTableName(0))) return;
        Message message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if (message.equals(Message.ID_NOT_EXIST)) {
            if (Objects.requireNonNull(selectedTable).equals(view.getTableName(1))) {
                Employee nv = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getEmployeeDAO().create(nv);
            } else if (selectedTable.equals(view.getTableName(2))) {
                Product mh = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getProductDAO().create(mh);
            } else if (selectedTable.equals(view.getTableName(3))) {
                SalesInvoice salesInvoice = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getSalesInvoiceDAO().create(salesInvoice);
            } else if (selectedTable.equals(view.getTableName(4))) {
                InvoiceProduct invoiceProduct = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getInvoiceProductDAO().create(invoiceProduct);
            } else if (selectedTable.equals(view.getTableName(5))) {
                Customer customer = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getCustomerDAO().create(customer);
            } else if (selectedTable.equals(view.getTableName(6))) {
                Supplier supplier = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getSupplierDAO().create(supplier);
            } else if (selectedTable.equals(view.getTableName(7))) {
                Order order = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getOrderDAO().create(order);
            } else if (selectedTable.equals(view.getTableName(8))) {
                DeliveryReceipt deliveryReceipt = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getDeliveryReceiptDAO().create(deliveryReceipt);
            } else if (selectedTable.equals(view.getTableName(9))) {
                SupplyRequest supplyRequest = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getSupplyRequestDAO().create(supplyRequest);
            } else if (selectedTable.equals(view.getTableName(10))) {
                ProductRequest productRequest = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoFactory.getProductRequestDAO().create(productRequest);
            } else {
                View.showMessage(view.getAddFrame(), Message.ERROR_OCCURRED.getMessage());
            }
        } else {
            View.showMessage(view.getAddFrame(), message.getMessage());
            view.clearNewsValue();
        }
        if (count == 0) {
            View.showMessage(view.getAddFrame(), Message.ERROR_OCCURRED.getMessage());
        } else {
            View.showMessage(view.getDeleteFrame(), Message.SUCCESS.getMessage());
        }
    }
}
