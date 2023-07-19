package model;

import java.util.Date;

public class KhachHang {
    private int maKhachHang;
    private String tenKhachHang;
    private Date ngaySinh;
    private String soDienThoai;
    private String email;
    private int diemThanThiet;

    public KhachHang(int maKhachHang, String tenKhachHang, Date ngaySinh, String soDienThoai, String email, int diemThanThiet) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diemThanThiet = diemThanThiet;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public int getDiemThanThiet() {
        return diemThanThiet;
    }
}

