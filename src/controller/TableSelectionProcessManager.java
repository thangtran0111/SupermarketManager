package controller;

import DAO.DAOFactory;
import model.*;
import view.View;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Objects;

public class TableSelectionProcessManager {
    private final DAOFactory daoFactory;

    TableSelectionProcessManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    void processTableSelection(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        if (Objects.requireNonNull(selectedTable).equals(view.getTableName(0))) {
            return;
        }
        DefaultTableModel oldModel = (DefaultTableModel) view.getTable().getModel();
        oldModel.setRowCount(0);
        Object[] columnNames = view.getColumnNames(Objects.requireNonNull(selectedTable));

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
            view.createSupplyRequestManagementFrame();
        } else if (selectedTable.equals(view.getTableName(10))) {
            view.createProductRequestManagementFrame();
        } else {
            View.showMessage(view.getContentPane(), Message.ERROR_OCCURRED.getMessage());
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        if (selectedTable.equals(view.getTableName(1))) {
            List<Employee> employeeList = daoFactory.getEmployeeDAO().read();
            for (Employee employee : employeeList) {
                model.addRow(new Object[]{String.valueOf(employee.getEmployeeID()), String.valueOf(employee.getEmployeeName()), String.valueOf(employee.getIDNumber()), String.valueOf(employee.getPhoneNumber()), String.valueOf(employee.getEmail()), employee.getDateOfBirth(), String.valueOf(employee.getGender()), String.valueOf(employee.getAddress()), String.valueOf(employee.getPosition()), employee.getSalary()});
            }
        } else if (selectedTable.equals(view.getTableName(2))) {
            List<Product> productList = daoFactory.getProductDAO().read();
            for (Product product : productList) {
                model.addRow(new Object[]{String.valueOf(product.getProductID()), String.valueOf(product.getBarcode()), String.valueOf(product.getProductName()), product.getRetailPrice(), product.getQuantityInStock(), String.valueOf(product.getProductType()), String.valueOf(product.getDescription())});
            }
        } else if (selectedTable.equals(view.getTableName(3))) {
            List<SalesInvoice> salesInvoiceList = daoFactory.getSalesInvoiceDAO().read();
            for (SalesInvoice salesInvoice : salesInvoiceList) {
                model.addRow(new Object[]{String.valueOf(salesInvoice.getInvoiceID()), salesInvoice.getInvoiceDate(), String.valueOf(salesInvoice.getCustomerID()), String.valueOf(salesInvoice.getPaymentMethod())});
            }
        } else if (selectedTable.equals(view.getTableName(4))) {
            List<InvoiceProduct> invoiceProductList = daoFactory.getInvoiceProductDAO().read();
            for (InvoiceProduct invoiceProduct : invoiceProductList) {
                model.addRow(new Object[]{String.valueOf(invoiceProduct.getInvoiceID()), String.valueOf(invoiceProduct.getProductID()), invoiceProduct.getQuantity()});
            }
        } else if (selectedTable.equals(view.getTableName(5))) {
            List<Customer> customerList = daoFactory.getCustomerDAO().read();
            for (Customer customer : customerList) {
                model.addRow(new Object[]{String.valueOf(customer.getCustomerID()), String.valueOf(customer.getCustomerName()), customer.getDateOfBirth(), String.valueOf(customer.getPhoneNumber()), String.valueOf(customer.getEmail()), customer.getLoyaltyPoints()});
            }
        } else if (selectedTable.equals(view.getTableName(6))) {
            List<Supplier> supplierList = daoFactory.getSupplierDAO().read();
            for (Supplier supplier : supplierList) {
                model.addRow(new Object[]{String.valueOf(supplier.getSupplierID()), String.valueOf(supplier.getSupplierName()), String.valueOf(supplier.getPhoneNumber()), String.valueOf(supplier.getEmail()), String.valueOf(supplier.getAddress())});
            }
        } else if (selectedTable.equals(view.getTableName(7))) {
            List<Order> orderList = daoFactory.getOrderDAO().read();
            for (Order order : orderList) {
                model.addRow(new Object[]{String.valueOf(order.getOrderID()), String.valueOf(order.getInvoiceID()), order.getExpectedDeliveryDate(), String.valueOf(order.getDeliveryAddress()), String.valueOf(order.getNotes())});
            }
        } else if (selectedTable.equals(view.getTableName(8))) {
            List<DeliveryReceipt> deliveryReceiptList = daoFactory.getDeliveryReceiptDAO().read();
            for (DeliveryReceipt deliveryReceipt : deliveryReceiptList) {
                model.addRow(new Object[]{String.valueOf(deliveryReceipt.getDeliveryReceiptID()), deliveryReceipt.getDeliveryDate(), String.valueOf(deliveryReceipt.getDeliveryStatus()), deliveryReceipt.getDeliveryFee(), String.valueOf(deliveryReceipt.getOrderID()), String.valueOf(deliveryReceipt.getDeliveryEmployeeID())});
            }
        } else if (selectedTable.equals(view.getTableName(9))) {
            List<SupplyRequest> supplyRequestList = daoFactory.getSupplyRequestDAO().read();
            for (SupplyRequest supplyRequest : supplyRequestList) {
                model.addRow(new Object[]{String.valueOf(supplyRequest.getSupplyRequestID()), supplyRequest.getSupplyRequestDate(), String.valueOf(supplyRequest.getSupplyRequestStatus()), supplyRequest.getReceiveDate(), String.valueOf(supplyRequest.getSupplierID()), String.valueOf(supplyRequest.getEmployeeID())});
            }
        } else if (selectedTable.equals(view.getTableName(10))) {
            List<ProductRequest> productRequestList = daoFactory.getProductRequestDAO().read();
            for (ProductRequest productRequest : productRequestList) {
                model.addRow(new Object[]{String.valueOf(productRequest.getSupplyRequestID()), String.valueOf(productRequest.getProductID()), productRequest.getQuantityReceived(), productRequest.getUnitPrice()});
            }
        } else {
            View.showMessage(view.getContentPane(), Message.ERROR_OCCURRED.getMessage());
        }

        view.getTable().setModel(model);
    }
}
