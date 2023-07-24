package controller;

import DAO.imple.*;
import DAO.itf.*;
import view.View;

import java.util.Objects;

public class DeleteProcessManager {
    private final EmployeeDAOInterface employeeDAO;
    private final ProductDAOInterface productDAO;
    private final SalesInvoiceDAOInterface salesInvoiceDAO;
    private final InvoiceProductDAOInterface invoiceProductDAO;
    private final CustomerDAOInterface customerDAO;
    private final SupplierDAOInterface supplierDAO;
    private final OrderDAOInterface orderDAO;
    private final DeliveryReceiptDAOInterface deliveryReceiptDAO;
    private final SupplyRequestDAOInterface warehouseReceiptDAO;

    public DeleteProcessManager() {
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        salesInvoiceDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        customerDAO = new CustomerDAO();
        supplierDAO = new SupplierDAO();
        orderDAO = new OrderDAO();
        deliveryReceiptDAO = new DeliveryReceiptDAO();
        warehouseReceiptDAO = new SupplyRequestDAO();
    }

    public void processDelete(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String ID = view.getNewFieldValues()[0].getText();
        MessageCode message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if (message.equals(MessageCode.ID_ALREADY_EXISTS) || message.equals(MessageCode.BARCODE_ALREADY_EXISTS)) {
            if (Objects.requireNonNull(selectedTable).equals(view.getTableName(1))) {
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
            View.showMessage(view.getAddFrame(), message.getMessage());
            view.getDeleteFrame().dispose();
            view.createDeleteFrame();
        }
        if (count == 0) {
            View.showMessage(view.getDeleteFrame(), MessageCode.ERROR_OCCURRED.getMessage());
        } else {
            View.showMessage(view.getDeleteFrame(), MessageCode.SUCCESS.getMessage());
        }
    }
}
