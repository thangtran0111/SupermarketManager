package controller;

import DAO.imple.*;
import DAO.itf.*;
import model.*;
import view.View;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableSelectionProcessManager {
    private ProductDAOInterface productDAO;
    private EmployeeDAOInterface employeeDAO;
    private SalesInvoiceDAOInterface salesInvoiceDAO;
    private InvoiceProductDAOInterface invoiceProductDAO;
    private CustomerDAOInterface customerDAO;
    private SupplierDAOInterface supplierDAO;
    private OrderDAOInterface orderDAO;
    private DeliveryReceiptDAOInterface deliveryReceiptDAO;
    private WarehouseReceiptDAOInterface warehouseReceiptDAO;

    public TableSelectionProcessManager() {
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

    public void processTableSelection(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        DefaultTableModel oldModel = (DefaultTableModel) view.getTable().getModel();
        oldModel.setRowCount(0);
        Object[] columnNames = view.getColumnNames(selectedTable);

        if (selectedTable.equals(view.getTableName(1))) {
            view.createEmployeeManagementFrame();
        } else if (selectedTable.equals(view.getTableName(2))) {
            view.createProductManagementFrame();
        } else if (selectedTable.equals(view.getTableName(3))) {
            view.createSaleInvoicesManagementFrame();
        } else if (selectedTable.equals(view.getTableName(4))) {
            view.createInvoiceProductManagementFrame();
        } else if (selectedTable.equals(view.getTableName(5))) {
            view.createCustomerManagementFrame();
        } else if (selectedTable.equals(view.getTableName(6))) {
            view.createSupplierManagementFrame();
        } else if (selectedTable.equals(view.getTableName(7))) {
            view.createOrderManagementFrame();
        } else if (selectedTable.equals(view.getTableName(8))) {
            view.createDeliveryReceiptManagementFrame();
        } else if (selectedTable.equals(view.getTableName(9))) {
            view.createWarehouseReceiptManagementFrame();
        } else {
            return;
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        if (selectedTable.equals(view.getTableName(1))) {
            List<Employee> employeeList = employeeDAO.read();
            for (Employee employee : employeeList) {
                model.addRow(new Object[]{String.valueOf(employee.getEmployeeID()), String.valueOf(employee.getEmployeeName()), String.valueOf(employee.getIDNumber()), String.valueOf(employee.getPhoneNumber()), String.valueOf(employee.getEmail()), employee.getDateOfBirth(), String.valueOf(employee.getGender()), String.valueOf(employee.getAddress()), String.valueOf(employee.getPosition()), employee.getSalary()});
            }
        } else if (selectedTable.equals(view.getTableName(2))) {
            List<Product> productList = productDAO.read();
            for (Product product : productList) {
                model.addRow(new Object[]{String.valueOf(product.getProductID()), String.valueOf(product.getBarcode()), String.valueOf(product.getProductName()), product.getRetailPrice(), product.getQuantityInStock(), String.valueOf(product.getProductType()), String.valueOf(product.getDescription())});
            }
        } else if (selectedTable.equals(view.getTableName(3))) {
            List<SalesInvoice> salesInvoiceList = salesInvoiceDAO.read();
            for (SalesInvoice salesInvoice : salesInvoiceList) {
                model.addRow(new Object[]{String.valueOf(salesInvoice.getInvoiceID()), salesInvoice.getInvoiceDate(), String.valueOf(salesInvoice.getCustomerID()), String.valueOf(salesInvoice.getPaymentMethod())});
            }
        } else if (selectedTable.equals(view.getTableName(4))) {
            List<InvoiceProduct> invoiceProductList = invoiceProductDAO.read();
            for (InvoiceProduct invoiceProduct : invoiceProductList) {
                model.addRow(new Object[]{String.valueOf(invoiceProduct.getInvoiceID()), String.valueOf(invoiceProduct.getProductID()), invoiceProduct.getQuantity()});
            }
        } else if (selectedTable.equals(view.getTableName(5))) {
            List<Customer> customerList = customerDAO.read();
            for (Customer customer : customerList) {
                model.addRow(new Object[]{String.valueOf(customer.getCustomerID()), String.valueOf(customer.getCustomerName()), customer.getDateOfBirth(), String.valueOf(customer.getPhoneNumber()), String.valueOf(customer.getEmail()), customer.getLoyaltyPoints()});
            }
        } else if (selectedTable.equals(view.getTableName(6))) {
            List<Supplier> supplierList = supplierDAO.read();
            for (Supplier supplier : supplierList){
                model.addRow(new Object[]{String.valueOf(supplier.getSupplierID()), String.valueOf(supplier.getSupplierName()), String.valueOf(supplier.getPhoneNumber()), String.valueOf(supplier.getEmail()), String.valueOf(supplier.getAddress())});
            }
        } else if (selectedTable.equals(view.getTableName(7))) {
            List<Order> orderList = orderDAO.read();
            for (Order order : orderList) {
                model.addRow(new Object[]{String.valueOf(order.getOrderID()), String.valueOf(order.getInvoiceID()), order.getExpectedDeliveryDate(), String.valueOf(order.getDeliveryAddress()), String.valueOf(order.getNotes())});
            }
        } else if (selectedTable.equals(view.getTableName(8))) {
            List<DeliveryReceipt> deliveryReceiptList = deliveryReceiptDAO.read();
            for (DeliveryReceipt deliveryReceipt : deliveryReceiptList) {
                model.addRow(new Object[]{String.valueOf(deliveryReceipt.getDeliveryReceiptID()), deliveryReceipt.getDeliveryDate(), String.valueOf(deliveryReceipt.getDeliveryStatus()), String.valueOf(deliveryReceipt.getOrderID()), String.valueOf(deliveryReceipt.getDeliveryEmployeeID())});
            }
        } else if (selectedTable.equals(view.getTableName(9))) {
            List<WarehouseReceipt> warehouseReceiptList = warehouseReceiptDAO.read();
            for (WarehouseReceipt warehouseReceipt : warehouseReceiptList) {
                model.addRow(new Object[]{String.valueOf(warehouseReceipt.getWarehouseReceiptID()), warehouseReceipt.getReceiptDate(), String.valueOf(warehouseReceipt.getSupplierID()), String.valueOf(warehouseReceipt.getEmployeeID())});
            }
        }

        view.getTable().setModel(model);
    }
}
