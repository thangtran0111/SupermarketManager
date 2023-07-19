package model;

import java.time.LocalDateTime;

public class PhieuGiaoHang {
    private int maPhieuGiaoHang;
    private LocalDateTime thoiGianGiaoHang;
    private String tinhTrangGiaoHang;
    private DonHang donHang;
    private NhanVien nhanVienGiaoHang;

    public PhieuGiaoHang(int maPhieuGiaoHang, LocalDateTime thoiGianGiaoHang, String tinhTrangGiaoHang, DonHang donHang, NhanVien nhanVienGiaoHang) {
        this.maPhieuGiaoHang = maPhieuGiaoHang;
        this.thoiGianGiaoHang = thoiGianGiaoHang;
        this.tinhTrangGiaoHang = tinhTrangGiaoHang;
        this.donHang = donHang;
        this.nhanVienGiaoHang = nhanVienGiaoHang;
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

    public NhanVien getNhanVienGiaoHang() {
        return nhanVienGiaoHang;
    }
}

