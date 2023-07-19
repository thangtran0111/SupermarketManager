package DAO.itf;

import model.HoaDonBanHang;

import java.util.List;

public interface IDaoHoaDonBanHang {
    int create(HoaDonBanHang hoaDonBanHang);
    List<HoaDonBanHang> read();
    int update(HoaDonBanHang hoaDonBanHang);
    int delete(String maHoaDonBanHang);
}
