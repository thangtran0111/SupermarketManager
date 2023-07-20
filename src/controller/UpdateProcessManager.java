package controller;

import DAO.imple.SalesInvoiceDAO;
import DAO.imple.DaoHoaDonMatHang;
import DAO.imple.ProductDAO;
import DAO.imple.EmployeeDAO;
import DAO.itf.SalesInvoiceDAOInterface;
import DAO.itf.IDaoHoaDonMatHang;
import DAO.itf.ProductDAOInterface;
import DAO.itf.EmployeeDAOInterface;
import model.SalesInvoice;
import model.HoaDonMatHang;
import model.Product;
import model.Employee;
import view.View;

public class UpdateProcessManager {
    private ProductDAOInterface productDAO;
    private EmployeeDAOInterface employeeDAO;
    private SalesInvoiceDAOInterface saleInvoicesDAO;
    private IDaoHoaDonMatHang daoHoaDonMatHang;

    public UpdateProcessManager(){
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        saleInvoicesDAO = new SalesInvoiceDAO();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }
    //TODO: thêm các bảng khác các bảng hiện có Employee, Product, SaleInvoices, Hoá Đơn Mặt Hàng
    public void processUpdate(View view){
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if(message.equals("Mã đã tồn tại")){
            System.out.println("2");
            if(selectedTable.equals(view.getTableName(1))){
                Employee employee = view.createObject(selectedTable, view.getNewFieldValues());
                count = employeeDAO.update(employee);
            } else if(selectedTable.equals(view.getTableName(2))){
                Product product = view.createObject(selectedTable, view.getNewFieldValues());
                count = productDAO.update(product);
            } else if (selectedTable.equals(view.getTableName(3))) {
                SalesInvoice salesInvoice = view.createObject(selectedTable, view.getNewFieldValues());
                count = saleInvoicesDAO.update(salesInvoice);
            } else if (selectedTable.equals(view.getTableName(3))) {
                HoaDonMatHang hoaDonMatHang = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoHoaDonMatHang.update(hoaDonMatHang);
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
