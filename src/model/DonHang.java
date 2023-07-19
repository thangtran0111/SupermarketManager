package model;

import java.time.LocalDateTime;

public class DonHang {
    private int maDonHang;
    private HoaDonBanHang hoaDonBanHang;
    private LocalDateTime thoiGianGiaoHangMongMuon;
    private String diaChiGiaoHang;
    private String ghiChu;

    public DonHang(int maDonHang, HoaDonBanHang hoaDonBanHang, LocalDateTime thoiGianGiaoHangMongMuon, String diaChiGiaoHang, String ghiChu) {
        this.maDonHang = maDonHang;
        this.hoaDonBanHang = hoaDonBanHang;
        this.thoiGianGiaoHangMongMuon = thoiGianGiaoHangMongMuon;
        this.diaChiGiaoHang = diaChiGiaoHang;
        this.ghiChu = ghiChu;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public HoaDonBanHang getHoaDonBanHang() {
        return hoaDonBanHang;
    }

    public LocalDateTime getThoiGianGiaoHangMongMuon() {
        return thoiGianGiaoHangMongMuon;
    }

    public String getDiaChiGiaoHang() {
        return diaChiGiaoHang;
    }

    public String getGhiChu() {
        return ghiChu;
    }
}
