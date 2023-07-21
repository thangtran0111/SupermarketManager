package controller;

import DAO.imple.*;
import DAO.itf.*;
import view.View;

public class DeleteProcessManager {
    private EmployeeDAOInterface employeeDAO;
    private ProductDAOInterface productDAO;
    private SalesInvoiceDAOInterface salesInvoiceDAO;
    private InvoiceProductDAOInterface invoiceProductDAO;
    private CustomerDAOInterface customerDAO;
    private SupplierDAOInterface supplierDAO;
    private OrderDAOInterface orderDAO;
    private DeliveryReceiptDAOInterface deliveryReceiptDAO;
    private WarehouseReceiptDAOInterface warehouseReceiptDAO;

    public DeleteProcessManager() {
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

    public void processDelete(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String ID = view.getNewFieldValues()[0].getText();
        String message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if (message.equals("Mã đã tồn tại")) {
            if (selectedTable.equals(view.getTableName(1))) {
                if (!ID.equals("99999")) {
                    count = employeeDAO.delete(ID);
                }
            } else if (selectedTable.equals(view.getTableName(2))) {
                count = productDAO.delete(ID);
            } else if (selectedTable.equals(view.getTableName(3))) {
                count = salesInvoiceDAO.delete(ID);
                if (count > 0) {
                    count = salesInvoiceDAO.delete(ID);
                }
            } else if (selectedTable.equals(view.getTableName(4))) {
                count = invoiceProductDAO.delete(ID, view.getNewFieldValues()[1].getText());
            } else if (selectedTable.equals(view.getTableName(5))) {
                count = customerDAO.delete(ID);
            } else if (selectedTable.equals(view.getTableName(6))) {
                count = supplierDAO.delete(ID);
            } else if (selectedTable.equals(view.getTableName(7))) {
                count = orderDAO.delete(ID);
            } else if (selectedTable.equals(view.getTableName(8))) {
                count = deliveryReceiptDAO.delete(ID);
            } else if (selectedTable.equals(view.getTableName(9))) {
                count = warehouseReceiptDAO.delete(ID);
            }
        } else {
            view.showMessage(view.getAddFrame(), message);
            view.getDeleteFrame().dispose();
            view.createDeleteFrame();
        }
        if (count == 0) {
            view.showMessage(view.getDeleteFrame(), "An error occurred!");
        } else {
            view.showMessage(view.getDeleteFrame(), "Success!");
        }
    }
}
