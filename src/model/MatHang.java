package model;

import java.util.Date;

public class MatHang {
    private String maMatHang;
    private String tenMatHang;
    private float giaBan;
    private int soLuongTonKho;
    private String loaiMatHang;
    private Date hanSuDung;
    private String moTa;

    public MatHang(String maMatHang, String tenMatHang, float giaBan, int soLuongTonKho, String loaiMatHang, Date hanSuDung, String moTa) {
        this.maMatHang = maMatHang;
        this.tenMatHang = tenMatHang;
        this.giaBan = giaBan;
        this.soLuongTonKho = soLuongTonKho;
        this.loaiMatHang = loaiMatHang;
        this.hanSuDung = hanSuDung;
        this.moTa = moTa;
    }

    public MatHang() {

    }

    public String getMaMatHang() {
        return maMatHang;
    }

    public void setMaMatHang(String maMatHang) {
        this.maMatHang = maMatHang;
    }

    public String getTenMatHang() {
        return tenMatHang;
    }

    public void setTenMatHang(String tenMatHang) {
        this.tenMatHang = tenMatHang;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public int getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(int soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public String getLoaiMatHang() {
        return loaiMatHang;
    }

    public void setLoaiMatHang(String loaiMatHang) {
        this.loaiMatHang = loaiMatHang;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    // Helper method: chuyển đổi chuỗi thành ngày tháng
    private static Date convertToDate(String dateString) {
        // Cài đặt logic chuyển đổi từ chuỗi sang ngày tháng tại đây
        return null;
    }
}
