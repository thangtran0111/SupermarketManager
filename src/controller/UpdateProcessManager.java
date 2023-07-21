package controller;

import DAO.imple.*;
import DAO.itf.*;
import model.*;
import view.View;

public class UpdateProcessManager {
    private ProductDAOInterface productDAO;
    private EmployeeDAOInterface employeeDAO;
    private SalesInvoiceDAOInterface saleInvoicesDAO;
    private InvoiceProductDAOInterface invoiceProductDAO;
    private CustomerDAOInterface customerDAO;
    private SupplierDAOInterface supplierDAO;

    public UpdateProcessManager(){
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        saleInvoicesDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        customerDAO = new CustomerDAO();
        supplierDAO = new SupplierDAO();
    }
    //TODO: thêm các bảng khác các bảng hiện có Employee, Product, SaleInvoices, InvoiceProduct, Customer, Supplier
    public void processUpdate(View view){
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if(message.equals("Mã đã tồn tại")){
            if(selectedTable.equals(view.getTableName(1))){
                Employee employee = view.createObject(selectedTable, view.getNewFieldValues());
                count = employeeDAO.update(employee);
            } else if(selectedTable.equals(view.getTableName(2))){
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
            } else if(selectedTable.equals(view.getTableName(6))){
                Supplier supplier = view.createObject(selectedTable, view.getNewFieldValues());
                count = supplierDAO.update(supplier);
            }
        }else{
            view.showMessage(view.getAddFrame(), message);
            view.clearNewsValue();
        }
        if(count == 0){
            view.showMessage(view.getUpdateFrame(), "An error occurred!");
        }else{
            view.showMessage(view.getUpdateFrame(), "Success!");
        }
    }
}
