package model;
import java.util.Date;
public class PhieuNhapKho {
    private int maPhieuNhapKho;
    private Date ngayNhap;
    private int maNhaCC;
    private int maNhanVien;

    public PhieuNhapKho(int maPhieuNhapKho, Date ngayNhap, int maNhaCC, int maNhanVien) {
        this.maPhieuNhapKho = maPhieuNhapKho;
        this.ngayNhap = ngayNhap;
        this.maNhaCC = maNhaCC;
        this.maNhanVien = maNhanVien;
    }

    public int getMaPhieuNhapKho() {
        return maPhieuNhapKho;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public int getMaNhaCC() {
        return maNhaCC;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }
}

