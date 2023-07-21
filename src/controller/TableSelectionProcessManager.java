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

    public TableSelectionProcessManager() {
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        salesInvoiceDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        customerDAO = new CustomerDAO();
    }

    //TODO: thêm các bảng khác các bảng hiện có Employee, Product, SaleInvoices, InvoiceProduct, Customer
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
        }

        view.getTable().setModel(model);

    }
}
