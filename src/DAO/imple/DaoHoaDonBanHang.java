package DAO.imple;

import DAO.itf.IDaoHoaDonBanHang;
import databaseConnection.DatabaseConnection;
import model.HoaDonBanHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoHoaDonBanHang implements IDaoHoaDonBanHang {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    @Override
    public int create(HoaDonBanHang hoaDonBanHang) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO HoaDonBanHang (MaHoaDon, NgayLap, MaKhachHang, PhuongThucThanhToan) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, hoaDonBanHang.getMaHoaDon());
            preparedStatement.setDate(2, new java.sql.Date(hoaDonBanHang.getNgayLap().getTime()));
            preparedStatement.setString(3, hoaDonBanHang.getMaKhachHang());
            preparedStatement.setString(4, hoaDonBanHang.getPhuongThucThanhToan());
            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<HoaDonBanHang> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM HoaDonBanHang;");
            resultSet = preparedStatement.executeQuery();
            List<HoaDonBanHang> listHoaDonBanHang = new ArrayList<>();
            while (resultSet.next()) {
                listHoaDonBanHang.add(new HoaDonBanHang(
                        resultSet.getString("MaHoaDon").trim(),
                        resultSet.getDate("NgayLap"),
                        resultSet.getString("MaKhachHang").trim(),
                        resultSet.getString("PhuongThucThanhToan").trim()));
            }
            return listHoaDonBanHang;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(HoaDonBanHang hoaDonBanHang) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE HoaDonBanHang SET  NgayLap = ?, MaKhachHang = ?, PhuongThucThanhToan = ? WHERE MaHoaDOn = ?");

            preparedStatement.setDate(1, new java.sql.Date(hoaDonBanHang.getNgayLap().getTime()));
            preparedStatement.setString(2, hoaDonBanHang.getMaKhachHang());
            preparedStatement.setString(3, hoaDonBanHang.getPhuongThucThanhToan());
            preparedStatement.setString(4, hoaDonBanHang.getMaHoaDon());
            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String maHoaDonBanHang) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM HoaDonBanHang WHERE MaHoaDon = ?");

            preparedStatement.setString(1, maHoaDonBanHang);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }
}
