package DAO.imple;
import DAO.itf.IDaoNhanVien;
import databaseConnection.DatabaseConnection;
import model.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoNhanVien implements IDaoNhanVien {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<NhanVien> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM NhanVien;");
            resultSet = preparedStatement.executeQuery();
            List<NhanVien> listNhanVien = new ArrayList<>();
            while (resultSet.next()) {
                listNhanVien.add(new NhanVien(resultSet.getString("MaNhanVien").trim(),
                        resultSet.getString("TenNhanVien").trim(),
                        resultSet.getString("SoDienThoai").trim(),
                        resultSet.getString("Email").trim(),
                        resultSet.getDate("NgaySinh"),
                        resultSet.getString("GioiTinh").trim(),
                        resultSet.getString("DiaChi").trim(),
                        resultSet.getString("ChucVu").trim(),
                        resultSet.getInt("Luong")));
            }
            return listNhanVien;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(NhanVien nv) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO NhanVien (MaNhanVien, TenNhanVien, SoDienThoai, Email, NgaySinh, GioiTinh, DiaChi, ChucVu, Luong) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, nv.getMaNhanVien());
            preparedStatement.setString(2, nv.getTenNhanVien());
            preparedStatement.setString(3, nv.getSoDienThoai());
            preparedStatement.setString(4, nv.getEmail());
            preparedStatement.setDate(5, new java.sql.Date(nv.getNgaySinh().getTime()));
            preparedStatement.setString(6, nv.getGioiTinh());
            preparedStatement.setString(7, nv.getDiaChi());
            preparedStatement.setString(8, nv.getChucVu());
            preparedStatement.setInt(9, nv.getLuong());

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int update(NhanVien nv) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE NhanVien SET TenNhanVien = ?, SoDienThoai = ?, Email = ?, NgaySinh = ?, GioiTinh = ?, DiaChi = ?, ChucVu = ?, Luong = ? WHERE MaNhanVien = ?");

            preparedStatement.setString(1, nv.getTenNhanVien());
            preparedStatement.setString(2, nv.getSoDienThoai());
            preparedStatement.setString(3, nv.getEmail());
            preparedStatement.setDate(4, new java.sql.Date(nv.getNgaySinh().getTime()));
            preparedStatement.setString(5, nv.getGioiTinh());
            preparedStatement.setString(6, nv.getDiaChi());
            preparedStatement.setString(7, nv.getChucVu());
            preparedStatement.setInt(8, nv.getLuong());
            preparedStatement.setString(9, nv.getMaNhanVien());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String MaNhanVien) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM NhanVien WHERE MaNhanVien = ?");

            preparedStatement.setString(1, MaNhanVien);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }


}
