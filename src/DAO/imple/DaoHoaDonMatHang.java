package DAO.imple;

import DAO.itf.IDaoHoaDonMatHang;
import databaseConnection.DatabaseConnection;
import model.HoaDonMatHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoHoaDonMatHang implements IDaoHoaDonMatHang {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public int create(HoaDonMatHang hoaDonMatHang) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO HoaDonMatHang (MaHoaDon, MaMatHang, SoLuong) VALUES (?, ?, ?)");

            preparedStatement.setString(1, hoaDonMatHang.getMaHoaDon());
            preparedStatement.setString(2, hoaDonMatHang.getMaMatHang());
            preparedStatement.setInt(3, hoaDonMatHang.getSoLuong());

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<HoaDonMatHang> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM HoaDonMatHang;");
            resultSet = preparedStatement.executeQuery();
            List<HoaDonMatHang> listHoaDonMatHang = new ArrayList<>();
            while (resultSet.next()) {
                listHoaDonMatHang.add(new HoaDonMatHang(
                        resultSet.getString("MaHoaDon").trim(),
                        resultSet.getString("MaMatHang").trim(),
                        resultSet.getInt("SoLuong")));
            }
            return listHoaDonMatHang;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(HoaDonMatHang hoaDonMatHang) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE HoaDonMatHang SET SoLuong = ? WHERE MaHoaDon = ? AND MaMatHang = ?");

            preparedStatement.setInt(1, hoaDonMatHang.getSoLuong());
            preparedStatement.setString(2, hoaDonMatHang.getMaHoaDon());
            preparedStatement.setString(3, hoaDonMatHang.getMaMatHang());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String maHoaDon, String maMatHang) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM HoaDonMatHang WHERE MaHoaDon = ? AND MaMatHang = ?");

            preparedStatement.setString(1, maHoaDon);
            preparedStatement.setString(2, maMatHang);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;

    }

    @Override
    public int delete(String maHoaDon) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM HoaDonMatHang WHERE MaHoaDon = ?");

            preparedStatement.setString(1, maHoaDon);
            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

}
