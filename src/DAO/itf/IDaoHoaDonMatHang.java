package DAO.itf;

import model.HoaDonMatHang;

import java.util.List;

public interface IDaoHoaDonMatHang {
    int create(HoaDonMatHang hoaDonMatHang);
    List<HoaDonMatHang> read();
    int update(HoaDonMatHang hoaDonMatHang);
    int delete(String maHoaDon, String maMatHang);
    int delete(String maHoaDon);
}

