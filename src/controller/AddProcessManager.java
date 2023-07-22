package controller;

import DAO.imple.*;
import DAO.itf.*;
import model.*;
import view.View;

import java.util.Objects;

public class AddProcessManager {
    private final ProductDAOInterface productDAO;
    private final EmployeeDAOInterface employeeDAO;
    private final SalesInvoiceDAOInterface salesInvoiceDAO;
    private final InvoiceProductDAOInterface invoiceProductDAO;
    private final CustomerDAOInterface customerDAO;
    private final SupplierDAOInterface supplierDAO;
    private final OrderDAOInterface orderDAO;
    private final DeliveryReceiptDAOInterface deliveryReceiptDAO;
    private final WarehouseReceiptDAOInterface warehouseReceiptDAO;

    public AddProcessManager() {
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        salesInvoiceDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        customerDAO = new CustomerDAO();
        supplierDAO = new SupplierDAO();
        orderDAO = new OrderDAO();
        deliveryReceiptDAO = new DeliveryReceiptDAO();
        warehouseReceiptDAO = new WarehouseReceiptDAO();
    }

    public void processAdd(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        MessageCode message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if (message.equals(MessageCode.ID_NOT_EXIST)) {
            if (Objects.requireNonNull(selectedTable).equals(view.getTableName(1))) {
                Employee nv = view.createObject(selectedTable, view.getNewFieldValues());
                count = employeeDAO.create(nv);
            } else if (selectedTable.equals(view.getTableName(2))) {
                Product mh = view.createObject(selectedTable, view.getNewFieldValues());
                count = productDAO.create(mh);
            } else if (selectedTable.equals(view.getTableName(3))) {
                SalesInvoice salesInvoice = view.createObject(selectedTable, view.getNewFieldValues());
                count = salesInvoiceDAO.create(salesInvoice);
            } else if (selectedTable.equals(view.getTableName(4))) {
                InvoiceProduct invoiceProduct = view.createObject(selectedTable, view.getNewFieldValues());
                count = invoiceProductDAO.create(invoiceProduct);
            } else if (selectedTable.equals(view.getTableName(5))) {
                Customer customer = view.createObject(selectedTable, view.getNewFieldValues());
                count = customerDAO.create(customer);
            } else if (selectedTable.equals(view.getTableName(6))) {
                Supplier supplier = view.createObject(selectedTable, view.getNewFieldValues());
                count = supplierDAO.create(supplier);
            } else if (selectedTable.equals(view.getTableName(7))) {
                Order order = view.createObject(selectedTable, view.getNewFieldValues());
                count = orderDAO.create(order);
            } else if (selectedTable.equals(view.getTableName(8))) {
                DeliveryReceipt deliveryReceipt = view.createObject(selectedTable, view.getNewFieldValues());
                count = deliveryReceiptDAO.create(deliveryReceipt);
            } else if (selectedTable.equals(view.getTableName(9))) {
                WarehouseReceipt warehouseReceipt = view.createObject(selectedTable, view.getNewFieldValues());
                count = warehouseReceiptDAO.create(warehouseReceipt);
            }
        } else {
            View.showMessage(view.getAddFrame(), message.getMessage());
            view.clearNewsValue();
        }
        if (count == 0) {
            View.showMessage(view.getAddFrame(), MessageCode.ERROR_OCCURRED.getMessage());
        } else {
            View.showMessage(view.getDeleteFrame(), MessageCode.SUCCESS.getMessage());
        }
    }
}
