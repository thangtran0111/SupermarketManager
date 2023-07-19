package model;

public class TaiKhoan {
    private char[] userName = new char[64];
    private String passwd;
    private String maNhanVien;

    public TaiKhoan() {
    }
    public TaiKhoan(char[] userName, String passwd, String maNhanVien) {
        this.userName = userName;
        this.passwd = passwd;
        this.maNhanVien = maNhanVien;
    }

    public char[] getUserName() {
        return userName;
    }

    public void setUserName(char[] userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }
}
