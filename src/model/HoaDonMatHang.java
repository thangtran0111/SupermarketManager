package model;

public class HoaDonMatHang {
    private String maHoaDon;
    private String maMatHang;
    private int soLuong;

    public HoaDonMatHang(String maHoaDon, String maMatHang, int soLuong) {
        this.maHoaDon = maHoaDon;
        this.maMatHang = maMatHang;
        this.soLuong = soLuong;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaMatHang() {
        return maMatHang;
    }

    public void setMaMatHang(String maMatHang) {
        this.maMatHang = maMatHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
