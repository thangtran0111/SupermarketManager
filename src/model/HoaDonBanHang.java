package model;

import java.util.Date;

public class HoaDonBanHang {
    private String maHoaDon;
    private Date ngayLap;
    private String maKhachHang;
    private String phuongThucThanhToan;


    public HoaDonBanHang(String maHoaDon, Date ngayLap, String maKhachHang, String phuongThucThanhToan) {
        this.maHoaDon = maHoaDon;
        this.ngayLap = ngayLap;
        this.maKhachHang = maKhachHang;
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }
}

