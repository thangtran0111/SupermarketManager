package model;

import java.time.LocalDateTime;

public class PhieuGiaoHang {
    private int maPhieuGiaoHang;
    private LocalDateTime thoiGianGiaoHang;
    private String tinhTrangGiaoHang;
    private DonHang donHang;
    private Employee employeeGiaoHang;

    public PhieuGiaoHang(int maPhieuGiaoHang, LocalDateTime thoiGianGiaoHang, String tinhTrangGiaoHang, DonHang donHang, Employee employeeGiaoHang) {
        this.maPhieuGiaoHang = maPhieuGiaoHang;
        this.thoiGianGiaoHang = thoiGianGiaoHang;
        this.tinhTrangGiaoHang = tinhTrangGiaoHang;
        this.donHang = donHang;
        this.employeeGiaoHang = employeeGiaoHang;
    }

    public int getMaPhieuGiaoHang() {
        return maPhieuGiaoHang;
    }

    public LocalDateTime getThoiGianGiaoHang() {
        return thoiGianGiaoHang;
    }

    public String getTinhTrangGiaoHang() {
        return tinhTrangGiaoHang;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public Employee getNhanVienGiaoHang() {
        return employeeGiaoHang;
    }
}

