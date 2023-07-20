package controller;

import DAO.imple.DaoHoaDonBanHang;
import DAO.imple.DaoHoaDonMatHang;
import DAO.imple.ProductDAO;
import DAO.imple.DaoNhanVien;
import DAO.itf.IDaoHoaDonBanHang;
import DAO.itf.IDaoHoaDonMatHang;
import DAO.itf.ProductDAOInterface;
import DAO.itf.IDaoNhanVien;
import model.HoaDonBanHang;
import model.HoaDonMatHang;
import model.Product;
import model.NhanVien;
import view.View;

public class UpdateProcessManager {
    private ProductDAOInterface productDAO;
    private IDaoNhanVien daoNhanVien;
    private IDaoHoaDonBanHang daoHoaDonBanHang;
    private IDaoHoaDonMatHang daoHoaDonMatHang;

    public UpdateProcessManager(){
        productDAO = new ProductDAO();
        daoNhanVien = new DaoNhanVien();
        daoHoaDonBanHang = new DaoHoaDonBanHang();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }
    //TODO: thêm các bảng khác các bảng hiện có Nhân Viên, Product, Hoá Đơn Bán Hàng, Hoá Đơn Mặt Hàng
    public void processUpdate(View view){
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if(message.equals("Mã đã tồn tại")){
            System.out.println("2");
            if(selectedTable.equals(view.getTableName(1))){
                NhanVien nv = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoNhanVien.update(nv);
            } else if(selectedTable.equals(view.getTableName(2))){
                Product mh = view.createObject(selectedTable, view.getNewFieldValues());
                count = productDAO.update(mh);
            } else if (selectedTable.equals(view.getTableName(3))) {
                HoaDonBanHang hoaDonBanHang = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoHoaDonBanHang.update(hoaDonBanHang);
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
