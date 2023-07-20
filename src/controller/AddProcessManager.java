package controller;

import DAO.imple.DaoHoaDonBanHang;
import DAO.imple.DaoHoaDonMatHang;
import DAO.imple.ProductDAO;
import DAO.imple.EmployeeDAO;
import DAO.itf.IDaoHoaDonBanHang;
import DAO.itf.IDaoHoaDonMatHang;
import DAO.itf.ProductDAOInterface;
import DAO.itf.EmployeeDAOInterface;
import model.HoaDonBanHang;
import model.HoaDonMatHang;
import model.Product;
import model.Employee;
import view.View;

public class AddProcessManager {
    private ProductDAOInterface productDAO;
    private EmployeeDAOInterface employeeDAO;
    private IDaoHoaDonBanHang daoHoaDonBanHang;
    private IDaoHoaDonMatHang daoHoaDonMatHang;

    public AddProcessManager(){
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        daoHoaDonBanHang = new DaoHoaDonBanHang();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }

    //TODO: thêm các bảng khác các bảng hiện có Employee, Product, Hoá Đơn Bán Hàng, Hoá Đơn Mặt Hàng
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
                HoaDonBanHang hoaDonBanHang = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoHoaDonBanHang.create(hoaDonBanHang);
            }else if(selectedTable.equals(view.getTableName(4))){
                HoaDonMatHang hoaDonMatHang = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoHoaDonMatHang.create(hoaDonMatHang);
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
