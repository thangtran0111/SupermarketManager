package controller;

import DAO.imple.*;
import DAO.itf.*;
import model.*;
import view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class UpdateProcessManager {
    private ProductDAOInterface productDAO;
    private EmployeeDAOInterface employeeDAO;
    private SalesInvoiceDAOInterface saleInvoicesDAO;
    private InvoiceProductDAOInterface invoiceProductDAO;
    private CustomerDAOInterface customerDAO;
    private SupplierDAOInterface supplierDAO;
    private OrderDAOInterface orderDAO;
    private DeliveryReceiptDAOInterface deliveryReceiptDAO;
    private WarehouseReceiptDAOInterface warehouseReceiptDAO;

    public UpdateProcessManager() {
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        saleInvoicesDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        customerDAO = new CustomerDAO();
        supplierDAO = new SupplierDAO();
        orderDAO = new OrderDAO();
        deliveryReceiptDAO = new DeliveryReceiptDAO();
        warehouseReceiptDAO = new WarehouseReceiptDAO();
    }

    public void processUpdate(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if (message.equals("Mã đã tồn tại")) {
            if (selectedTable.equals(view.getTableName(1))) {
                Employee employee = view.createObject(selectedTable, view.getNewFieldValues());
                count = employeeDAO.update(employee);
            } else if (selectedTable.equals(view.getTableName(2))) {
                Product product = view.createObject(selectedTable, view.getNewFieldValues());
                count = productDAO.update(product);
            } else if (selectedTable.equals(view.getTableName(3))) {
                SalesInvoice salesInvoice = view.createObject(selectedTable, view.getNewFieldValues());
                count = saleInvoicesDAO.update(salesInvoice);
            } else if (selectedTable.equals(view.getTableName(4))) {
                InvoiceProduct invoiceProduct = view.createObject(selectedTable, view.getNewFieldValues());
                count = invoiceProductDAO.update(invoiceProduct);
            } else if (selectedTable.equals(view.getTableName(5))) {
                Customer customer = view.createObject(selectedTable, view.getNewFieldValues());
                count = customerDAO.update(customer);
            } else if (selectedTable.equals(view.getTableName(6))) {
                Supplier supplier = view.createObject(selectedTable, view.getNewFieldValues());
                count = supplierDAO.update(supplier);
            } else if (selectedTable.equals(view.getTableName(7))) {
                Order order = view.createObject(selectedTable, view.getNewFieldValues());
                count = orderDAO.update(order);
            } else if (selectedTable.equals(view.getTableName(8))) {
                DeliveryReceipt deliveryReceipt = view.createObject(selectedTable, view.getNewFieldValues());
                count = deliveryReceiptDAO.update(deliveryReceipt);
            } else if (selectedTable.equals(view.getTableName(9))) {
                WarehouseReceipt warehouseReceipt = view.createObject(selectedTable, view.getNewFieldValues());
                count = warehouseReceiptDAO.update(warehouseReceipt);
            }
        } else {
            view.showMessage(view.getAddFrame(), message);
            view.clearNewsValue();
        }
        if (count == 0) {
            view.showMessage(view.getUpdateFrame(), "An error occurred!");
        } else {
            view.showMessage(view.getUpdateFrame(), "Success!");
        }
    }
}