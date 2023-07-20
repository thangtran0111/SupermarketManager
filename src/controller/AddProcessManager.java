package controller;

import DAO.imple.SalesInvoiceDAO;
import DAO.imple.InvoiceProductDAO;
import DAO.imple.ProductDAO;
import DAO.imple.EmployeeDAO;
import DAO.itf.SalesInvoiceDAOInterface;
import DAO.itf.InvoiceProductDAOInterface;
import DAO.itf.ProductDAOInterface;
import DAO.itf.EmployeeDAOInterface;
import model.SalesInvoice;
import model.InvoiceProduct;
import model.Product;
import model.Employee;
import view.View;

public class AddProcessManager {
    private ProductDAOInterface productDAO;
    private EmployeeDAOInterface employeeDAO;
    private SalesInvoiceDAOInterface salesInvoiceDAO;
    private InvoiceProductDAOInterface invoiceProductDAO;

    public AddProcessManager(){
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        salesInvoiceDAO = new SalesInvoiceDAO();
        invoiceProductDAO = new InvoiceProductDAO();
    }

    //TODO: thêm các bảng khác các bảng hiện có Employee, Product, SaleInvoices, InvoiceProduct
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
