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
    public Account get(String username) {
        Account account;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Account WHERE Username = ?");

            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account = new Account(resultSet.getString(1).toCharArray(), resultSet.getString(2), resultSet.getString(3));
            } else {
                account = new Account();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return account;
    }

    @Override
    public boolean exist(String username) {
        boolean isFound;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Account WHERE Username = ?");
            preparedStatement.setString(1, username);
            isFound = preparedStatement.executeQuery().next();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return isFound;
    }

    @Override
    public boolean exist(String username, String passwd) {
        boolean isFound;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Account WHERE Username = ? AND Passwd = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, passwd);
            isFound = preparedStatement.executeQuery().next();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return isFound;
    }


    @Override
    public List<Account> read() {
        return new ArrayList<>();
    }

}
