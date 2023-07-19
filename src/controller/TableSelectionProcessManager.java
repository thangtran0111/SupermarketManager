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

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableSelectionProcessManager {
    private IDaoMatHang daoMatHang;
    private IDaoNhanVien daoNhanVien;
    private IDaoHoaDonBanHang daoHoaDonBanHang;
    private IDaoHoaDonMatHang daoHoaDonMatHang;

    public TableSelectionProcessManager(){
        daoMatHang = new DaoMatHang();
        daoNhanVien = new DaoNhanVien();
        daoHoaDonBanHang = new DaoHoaDonBanHang();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }

    //TODO: thêm các bảng khác các bảng hiện có Nhân Viên, Mặt Hàng, Hoá Đơn Bán Hàng, Hoá Đơn Mặt Hàng
    public void processTableSelection(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        DefaultTableModel oldModel = (DefaultTableModel) view.getTable().getModel();
        oldModel.setRowCount(0);
        Object[] columnNames = view.getColumnNames(selectedTable);;

        if (selectedTable.equals(view.getTableName(1))) {
            List<NhanVien> nhanVienList = daoNhanVien.read();
            view.createNhanVienManagementFrame();
        } else if (selectedTable.equals(view.getTableName(2))) {
            List<MatHang> matHangList = daoMatHang.read();
            view.createMatHangManagementFrame();
        } else if(selectedTable.equals(view.getTableName(3))){
            List<HoaDonBanHang> hoaDonBanHangList = daoHoaDonBanHang.read();
            view.createHoaDonBanHangManagementFrame();
        } else if(selectedTable.equals(view.getTableName(4))){
            List<HoaDonMatHang> hoaDonBanHangList = daoHoaDonMatHang.read();
            view.createHoaDonMatHangManagementFrame();
        }
        else {
            return;
        }
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        if (selectedTable.equals(view.getTableName(1))) {
            List<NhanVien> nhanVienList = daoNhanVien.read();
            for (NhanVien nv : nhanVienList) {
                model.addRow(new Object[]{String.valueOf(nv.getMaNhanVien()), String.valueOf(nv.getTenNhanVien()), String.valueOf(nv.getSoDienThoai()), String.valueOf(nv.getEmail()), nv.getNgaySinh(), String.valueOf(nv.getGioiTinh()), String.valueOf(nv.getDiaChi()), String.valueOf(nv.getChucVu()), nv.getLuong()});
            }
        }else if (selectedTable.equals(view.getTableName(2))) {
                List<MatHang> matHangList = daoMatHang.read();
                for (MatHang mh : matHangList) {
                    model.addRow(new Object[]{String.valueOf(mh.getMaMatHang()), String.valueOf(mh.getTenMatHang()), mh.getGiaBan(), mh.getSoLuongTonKho(), String.valueOf(mh.getLoaiMatHang()), mh.getHanSuDung(), String.valueOf(mh.getMoTa())});
                }
        }else if (selectedTable.equals(view.getTableName(3))) {
            List<HoaDonBanHang> hoaDonBanHangList = daoHoaDonBanHang.read();
            for (HoaDonBanHang hoaDonBanHang : hoaDonBanHangList) {
                model.addRow(new Object[]{String.valueOf(hoaDonBanHang.getMaHoaDon()), hoaDonBanHang.getNgayLap(), String.valueOf(hoaDonBanHang.getMaKhachHang()), String.valueOf(hoaDonBanHang.getPhuongThucThanhToan())});
            }
        }else if (selectedTable.equals(view.getTableName(4))) {
            List<HoaDonMatHang> hoaDonMatHangList = daoHoaDonMatHang.read();
            for (HoaDonMatHang hoaDonMatHang : hoaDonMatHangList) {
                model.addRow(new Object[]{String.valueOf(hoaDonMatHang.getMaHoaDon()), String.valueOf(hoaDonMatHang.getMaMatHang()), hoaDonMatHang.getSoLuong()});
            }
        }

        view.getTable().setModel(model);

    }
}
