package controller;

import DAO.imple.DaoHoaDonBanHang;
import DAO.imple.DaoHoaDonMatHang;
import DAO.imple.DaoMatHang;
import DAO.imple.DaoNhanVien;
import DAO.itf.IDaoHoaDonBanHang;
import DAO.itf.IDaoHoaDonMatHang;
import DAO.itf.IDaoMatHang;
import DAO.itf.IDaoNhanVien;
import model.HoaDonBanHang;
import model.HoaDonMatHang;
import model.MatHang;
import model.NhanVien;
import view.View;

public class AddProcessManager {
    private IDaoMatHang daoMatHang;
    private IDaoNhanVien daoNhanVien;
    private IDaoHoaDonBanHang daoHoaDonBanHang;
    private IDaoHoaDonMatHang daoHoaDonMatHang;

    public AddProcessManager(){
        daoMatHang = new DaoMatHang();
        daoNhanVien = new DaoNhanVien();
        daoHoaDonBanHang = new DaoHoaDonBanHang();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }

    //TODO: thêm các bảng khác các bảng hiện có Nhân Viên, Mặt Hàng, Hoá Đơn Bán Hàng, Hoá Đơn Mặt Hàng
    public void processAdd(View view){
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String message = Controller.checkMa(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if(message.equals("Mã không tồn tại")){
            if(selectedTable.equals(view.getTableName(1))){
                NhanVien nv = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoNhanVien.create(nv);
            } else if(selectedTable.equals(view.getTableName(2))){
                MatHang mh = view.createObject(selectedTable, view.getNewFieldValues());
                count = daoMatHang.create(mh);
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
