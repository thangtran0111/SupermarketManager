package model;

import java.time.LocalDateTime;

public class DonHang {
    private int maDonHang;
    private SalesInvoice salesInvoice;
    private LocalDateTime thoiGianGiaoHangMongMuon;
    private String diaChiGiaoHang;
    private String ghiChu;

    public DonHang(int maDonHang, SalesInvoice salesInvoice, LocalDateTime thoiGianGiaoHangMongMuon, String diaChiGiaoHang, String ghiChu) {
        this.maDonHang = maDonHang;
        this.salesInvoice = salesInvoice;
        this.thoiGianGiaoHangMongMuon = thoiGianGiaoHangMongMuon;
        this.diaChiGiaoHang = diaChiGiaoHang;
        this.ghiChu = ghiChu;
    }

    public int getMaDonHang() {
        return maDonHang;
    }

    public SalesInvoice getHoaDonBanHang() {
        return salesInvoice;
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
