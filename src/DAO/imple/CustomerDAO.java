package DAO.imple;

import DAO.itf.CustomerDAOInterface;
import databaseConnection.DatabaseConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Customer> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Customer;");
            resultSet = preparedStatement.executeQuery();
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()) {
                customerList.add(new Customer(resultSet.getString("CustomerID").trim(), resultSet.getString("CustomerName").trim(), resultSet.getDate("DateOfBirth"), resultSet.getString("PhoneNumber").trim(), resultSet.getString("Email").trim(), resultSet.getInt("LoyaltyPoints")));
            }
            return customerList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(Customer customer) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO Customer (CustomerID, CustomerName, DateOfBirth, PhoneNumber, Email, LoyaltyPoints) VALUES (?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getCustomerName());
            preparedStatement.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
            preparedStatement.setString(4, customer.getPhoneNumber());
            preparedStatement.setString(5, customer.getEmail());
            preparedStatement.setInt(6, customer.getLoyaltyPoints());
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(Customer customer) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE Customer SET CustomerName = ?, DateOfBirth = ?, PhoneNumber = ?, Email = ?, LoyaltyPoints = ? WHERE CustomerID = ?");
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setDate(2, new java.sql.Date(customer.getDateOfBirth().getTime()));
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getLoyaltyPoints());
            preparedStatement.setString(6, customer.getCustomerID());
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int delete(String customerID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");
            preparedStatement.setString(1, customerID);
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Customer getByCustomerID(String _customerID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE CustomerID = ?;");
            preparedStatement.setString(1, _customerID);
            resultSet = preparedStatement.executeQuery();
            Customer customer = null;
            if (resultSet.next()) {
                customer = new Customer(resultSet.getString("CustomerID").trim(), resultSet.getString("CustomerName").trim(), resultSet.getDate("DateOfBirth"), resultSet.getString("PhoneNumber").trim(), resultSet.getString("Email").trim(), resultSet.getInt("LoyaltyPoints"));
            }
            return customer;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Customer> getByPhoneNumber(String phoneNumber) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE PhoneNumber = ?;");
            preparedStatement.setString(1, phoneNumber);
            resultSet = preparedStatement.executeQuery();
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()) {
                customerList.add(new Customer(resultSet.getString("CustomerID").trim(), resultSet.getString("CustomerName").trim(), resultSet.getDate("DateOfBirth"), resultSet.getString("PhoneNumber").trim(), resultSet.getString("Email").trim(), resultSet.getInt("LoyaltyPoints")));
            }
            return customerList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Customer> getByEmail(String email) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Customer WHERE Email = ?;");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()) {
                customerList.add(new Customer(resultSet.getString("CustomerID").trim(), resultSet.getString("CustomerName").trim(), resultSet.getDate("DateOfBirth"), resultSet.getString("PhoneNumber").trim(), resultSet.getString("Email").trim(), resultSet.getInt("LoyaltyPoints")));
            }
            return customerList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }
}
