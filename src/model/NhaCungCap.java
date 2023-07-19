package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class NhaCungCap {
    private int maNhaCC;
    private String tenNhaCC;
    private String soDienThoai;
    private String email;
    private String diaChi;

    public NhaCungCap(int maNhaCC, String tenNhaCC, String soDienThoai, String email, String diaChi) {
        this.maNhaCC = maNhaCC;
        this.tenNhaCC = tenNhaCC;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
    }

    public int getMaNhaCC() {
        return maNhaCC;
    }

    public void setMaNhaCC(int maNhaCC) {
        this.maNhaCC = maNhaCC;
    }

    public String getTenNhaCC() {
        return tenNhaCC;
    }

    public void setTenNhaCC(String tenNhaCC) {
        this.tenNhaCC = tenNhaCC;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public static void themNhaCungCap(Connection connection, NhaCungCap ncc){
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO NhaCungCap(MaNhaCC, TenNhaCC, SoDienThoai, Email, DiaChi) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, ncc.getMaNhaCC());
            preparedStatement.setString(2, ncc.getTenNhaCC());
            preparedStatement.setString(3, ncc.getSoDienThoai());
            preparedStatement.setString(4, ncc.getEmail());
            preparedStatement.setString(5, ncc.getDiaChi());

            preparedStatement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}

