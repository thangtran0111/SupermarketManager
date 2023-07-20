package controller;

import DAO.imple.SalesInvoiceDAO;
import DAO.imple.DaoHoaDonMatHang;
import DAO.imple.ProductDAO;
import DAO.imple.EmployeeDAO;
import DAO.itf.SalesInvoiceDAOInterface;
import DAO.itf.IDaoHoaDonMatHang;
import DAO.itf.ProductDAOInterface;
import DAO.itf.EmployeeDAOInterface;
import view.View;

public class DeleteProcessManager {
    EmployeeDAOInterface employeeDAO;
    ProductDAOInterface productDAO;
    SalesInvoiceDAOInterface salesInvoiceDAO;
    IDaoHoaDonMatHang daoHoaDonMatHang;
    public DeleteProcessManager(){
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        salesInvoiceDAO = new SalesInvoiceDAO();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }

    //TODO: thêm các bảng khác các bảng hiện có Employee, Product, SaleInvoices, Hoá Đơn Mặt Hàng
    public void processDelete(View view){
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String ma = view.getNewFieldValues()[0].getText();
        String message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if(message.equals("Mã đã tồn tại")){
            if(selectedTable.equals(view.getTableName(1))){
                if(!ma.equals("99999")){
                    count = employeeDAO.delete(ma);
                }
            } else if(selectedTable.equals(view.getTableName(2))){
                count = productDAO.delete(ma);
            }else if(selectedTable.equals(view.getTableName(3))){
                count = salesInvoiceDAO.delete(ma);
                if(count > 0){
                    count = 0;
                    count = salesInvoiceDAO.delete(ma);
                }
            }else if(selectedTable.equals(view.getTableName(4))){
                count = daoHoaDonMatHang.delete(ma, view.getNewFieldValues()[1].getText());
            }
        }else{
            view.showMessage(view.getAddFrame(), message);
            view.getDeleteFrame().dispose();
            view.createDeleteFrame();
        }
        if(count == 0){
            view.showMessage(view.getDeleteFrame(), "An error occurred!");
        }
        else{
            view.showMessage(view.getDeleteFrame(), "Success!");
        }
    }
}
