package DAO.imple;

import DAO.itf.IDaoMatHang;
import databaseConnection.DatabaseConnection;
import model.MatHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoMatHang implements IDaoMatHang {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<MatHang> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM MatHang;");
            resultSet = preparedStatement.executeQuery();
            List<MatHang> listMatHang = new ArrayList<>();
            while (resultSet.next()) {
                listMatHang.add(new MatHang(
                        resultSet.getString("MaMatHang").trim(),
                        resultSet.getString("TenMatHang").trim(),
                        resultSet.getFloat("GiaBan"),
                        resultSet.getInt("SoLuongTonKho"),
                        resultSet.getString("LoaiMatHang").trim(),
                        resultSet.getDate("HanSuDung"),
                        resultSet.getString("MoTa").trim()));
            }
            return listMatHang;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public MatHang get(String maMatHang) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM MatHang WHERE MaMatHang = ?;");
            preparedStatement.setString(1, maMatHang);
            resultSet = preparedStatement.executeQuery();
            MatHang matHang = new MatHang();
            while (resultSet.next()) {
                matHang = (new MatHang(
                        resultSet.getString("MaMatHang").trim(),
                        resultSet.getString("TenMatHang").trim(),
                        resultSet.getFloat("GiaBan"),
                        resultSet.getInt("SoLuongTonKho"),
                        resultSet.getString("LoaiMatHang").trim(),
                        resultSet.getDate("HanSuDung"),
                        resultSet.getString("MoTa").trim()));
            }
            return matHang;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(MatHang matHang) {
        int count = 0;
        try{
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT MatHang (MaMatHang, TenMatHang, GiaBan, SoLuongTonKho, LoaiMatHang, HanSuDung, MoTa) VALUES (?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, matHang.getMaMatHang());
            preparedStatement.setString(2, matHang.getTenMatHang());
            preparedStatement.setFloat(3, matHang.getGiaBan());
            preparedStatement.setInt(4, matHang.getSoLuongTonKho());
            preparedStatement.setString(5, matHang.getLoaiMatHang());
            preparedStatement.setDate(6, new java.sql.Date(matHang.getHanSuDung().getTime()));
            preparedStatement.setString(7, matHang.getMoTa());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int update(MatHang matHang) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE MatHang SET TenMatHang = ?, GiaBan = ?, SoLuongTonKho = ?, LoaiMatHang = ?, HanSuDung = ?, MoTa = ? WHERE MaMatHang = ?");

            preparedStatement.setString(1, matHang.getTenMatHang());
            preparedStatement.setFloat(2, matHang.getGiaBan());
            preparedStatement.setInt(3, matHang.getSoLuongTonKho());
            preparedStatement.setString(4, matHang.getLoaiMatHang());
            preparedStatement.setDate(5, new java.sql.Date(matHang.getHanSuDung().getTime()));
            preparedStatement.setString(6, matHang.getMoTa());
            preparedStatement.setString(7, matHang.getMaMatHang());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String maMatHang) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM MatHang WHERE MaMatHang = ?");

            preparedStatement.setString(1, maMatHang);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }
}
