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

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableSelectionProcessManager {
    private ProductDAOInterface productDAO;
    private IDaoNhanVien daoNhanVien;
    private IDaoHoaDonBanHang daoHoaDonBanHang;
    private IDaoHoaDonMatHang daoHoaDonMatHang;

    public TableSelectionProcessManager(){
        productDAO = new ProductDAO();
        daoNhanVien = new DaoNhanVien();
        daoHoaDonBanHang = new DaoHoaDonBanHang();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }

    //TODO: thêm các bảng khác các bảng hiện có Nhân Viên, Product, Hoá Đơn Bán Hàng, Hoá Đơn Mặt Hàng
    public void processTableSelection(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        DefaultTableModel oldModel = (DefaultTableModel) view.getTable().getModel();
        oldModel.setRowCount(0);
        Object[] columnNames = view.getColumnNames(selectedTable);;

        if (selectedTable.equals(view.getTableName(1))) {
            List<NhanVien> nhanVienList = daoNhanVien.read();
            view.createNhanVienManagementFrame();
        } else if (selectedTable.equals(view.getTableName(2))) {
            List<Product> productList = productDAO.read();
            view.createProductManagementFrame();
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
                List<Product> productList = productDAO.read();
                for (Product product : productList) {
                    model.addRow(new Object[]{String.valueOf(product.getProductID()),String.valueOf(product.getBarcode()) ,String.valueOf(product.getProductName()), product.getRetailPrice(), product.getQuantityInStock(), String.valueOf(product.getProductType()), String.valueOf(product.getDescription())});
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
