package DAO.imple;

import DAO.itf.AccountDAOInterface;
import databaseConnection.DatabaseConnection;
import model.Account;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements AccountDAOInterface {
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
    public Account get(String userName) {
        Account account;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM TaiKhoan WHERE UserName = ?");

            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account(resultSet.getString(1).toCharArray(), resultSet.getString(2), resultSet.getString(3));
            } else {
                account = new Account();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return account;
    }

    @Override
    public List<Account> read() {
        return new ArrayList<>();
    }

}
