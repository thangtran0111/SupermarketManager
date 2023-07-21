package controller;

import DAO.imple.*;
import DAO.itf.*;
import model.*;
import view.View;

public class AddProcessManager {
    private ProductDAOInterface productDAO;
    private EmployeeDAOInterface employeeDAO;
    private SalesInvoiceDAOInterface salesInvoiceDAO;
    private InvoiceProductDAOInterface invoiceProductDAO;
    private CustomerDAOInterface customerDAO;
    private SupplierDAOInterface supplierDAO;

    public AddProcessManager(){
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        salesInvoiceDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
        customerDAO = new CustomerDAO();
        supplierDAO = new SupplierDAO();
    }

    //TODO: thêm các bảng khác các bảng hiện có Employee, Product, SaleInvoices, InvoiceProduct, Customer, Supplier
    public void processAdd(View view){
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if(message.equals("Mã không tồn tại")){
            if(selectedTable.equals(view.getTableName(1))){
                Employee nv = view.createObject(selectedTable, view.getNewFieldValues());
                count = employeeDAO.create(nv);
            } else if(selectedTable.equals(view.getTableName(2))){
                Product mh = view.createObject(selectedTable, view.getNewFieldValues());
                count = productDAO.create(mh);
            }else if(selectedTable.equals(view.getTableName(3))){
                SalesInvoice salesInvoice = view.createObject(selectedTable, view.getNewFieldValues());
                count = salesInvoiceDAO.create(salesInvoice);
            }else if(selectedTable.equals(view.getTableName(4))){
                InvoiceProduct invoiceProduct = view.createObject(selectedTable, view.getNewFieldValues());
                count = invoiceProductDAO.create(invoiceProduct);
            }else if(selectedTable.equals(view.getTableName(5))){
                Customer customer = view.createObject(selectedTable, view.getNewFieldValues());
                count = customerDAO.create(customer);
            } else if (selectedTable.equals(view.getTableName(6))) {
                Supplier supplier = view.createObject(selectedTable, view.getNewFieldValues());
                count = supplierDAO.create(supplier);
            }
        }else{
            view.showMessage(view.getAddFrame(), message);
            view.clearNewsValue();
        }
        if(count == 0){
            view.showMessage(view.getAddFrame(), "An error occurred!");
        }else{
            view.showMessage(view.getDeleteFrame(), "Success!");
        }
    }
}
