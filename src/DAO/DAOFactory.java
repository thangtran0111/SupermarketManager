package DAO;

import DAO.imple.*;
import DAO.itf.*;

public class DAOFactory {
    private final EmployeeDAOInterface employeeDAO;
    private final ProductDAOInterface productDAO;
    private final SalesInvoiceDAOInterface salesInvoiceDAO;
    private final InvoiceProductDAOInterface invoiceProductDAO;
    private final CustomerDAOInterface customerDAO;
    private final SupplierDAOInterface supplierDAO;
    private final OrderDAOInterface orderDAO;
    private final DeliveryReceiptDAOInterface deliveryReceiptDAO;
    private final SupplyRequestDAOInterface supplyRequestDAO;
    private final ProductRequestDAOInterface productRequestDAO;
    private final AccountDAOInterface accountDAO;

    public DAOFactory() {
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        salesInvoiceDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        customerDAO = new CustomerDAO();
        supplierDAO = new SupplierDAO();
        orderDAO = new OrderDAO();
        deliveryReceiptDAO = new DeliveryReceiptDAO();
        supplyRequestDAO = new SupplyRequestDAO();
        productRequestDAO = new ProductRequestDAO();
        accountDAO = new AccountDAO();
    }

    public EmployeeDAOInterface getEmployeeDAO() {
        return employeeDAO;
    }

    public ProductDAOInterface getProductDAO() {
        return productDAO;
    }

    public SalesInvoiceDAOInterface getSalesInvoiceDAO() {
        return salesInvoiceDAO;
    }

    public InvoiceProductDAOInterface getInvoiceProductDAO() {
        return invoiceProductDAO;
    }

    public CustomerDAOInterface getCustomerDAO() {
        return customerDAO;
    }

    public SupplierDAOInterface getSupplierDAO() {
        return supplierDAO;
    }

    public OrderDAOInterface getOrderDAO() {
        return orderDAO;
    }

    public DeliveryReceiptDAOInterface getDeliveryReceiptDAO() {
        return deliveryReceiptDAO;
    }

    public SupplyRequestDAOInterface getSupplyRequestDAO() {
        return supplyRequestDAO;
    }

    public ProductRequestDAOInterface getProductRequestDAO() {
        return productRequestDAO;
    }

    public AccountDAOInterface getAccountDAO() {
        return accountDAO;
    }
}
