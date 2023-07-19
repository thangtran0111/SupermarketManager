package DAO.imple;

import DAO.itf.IDaoTaiKhoan;
import databaseConnection.DatabaseConnection;
import model.TaiKhoan;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoTaiKhoan implements IDaoTaiKhoan {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public TaiKhoan get(String userName) {
        TaiKhoan taiKhoan;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM TaiKhoan WHERE UserName = ?");

            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                taiKhoan = new TaiKhoan(resultSet.getString(1).toCharArray(), resultSet.getString(2), resultSet.getString(3));
            } else {
                taiKhoan = new TaiKhoan();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return taiKhoan;
    }

    @Override
    public List<TaiKhoan> read() {
        return new ArrayList<>();
    }

}
