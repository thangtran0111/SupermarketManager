package controller;

import DAO.imple.DaoHoaDonBanHang;
import DAO.imple.DaoHoaDonMatHang;
import DAO.imple.DaoMatHang;
import DAO.imple.DaoNhanVien;
import DAO.itf.IDaoHoaDonBanHang;
import DAO.itf.IDaoHoaDonMatHang;
import DAO.itf.IDaoMatHang;
import DAO.itf.IDaoNhanVien;
import view.View;

public class DeleteProcessManager {
    IDaoNhanVien daoNhanVien;
    IDaoMatHang daoMatHang;
    IDaoHoaDonBanHang daoHoaDonBanHang;
    IDaoHoaDonMatHang daoHoaDonMatHang;
    public DeleteProcessManager(){
        daoMatHang = new DaoMatHang();
        daoNhanVien = new DaoNhanVien();
        daoHoaDonBanHang = new DaoHoaDonBanHang();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }

    //TODO: thêm các bảng khác các bảng hiện có Nhân Viên, Mặt Hàng, Hoá Đơn Bán Hàng, Hoá Đơn Mặt Hàng
    public void processDelete(View view){
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        String ma = view.getNewFieldValues()[0].getText();
        String message = Controller.checkMa(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if(message.equals("Mã đã tồn tại")){
            if(selectedTable.equals(view.getTableName(1))){
                if(!ma.equals("NV999")){
                    count = daoNhanVien.delete(ma);
                }
            } else if(selectedTable.equals(view.getTableName(2))){
                count = daoMatHang.delete(ma);
            }else if(selectedTable.equals(view.getTableName(3))){
                count = daoHoaDonBanHang.delete(ma);
                if(count > 0){
                    count = 0;
                    count = daoHoaDonBanHang.delete(ma);
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
