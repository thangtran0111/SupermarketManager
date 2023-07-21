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
                String customerID = resultSet.getString("CustomerID").trim();
                String customerName = resultSet.getString("CustomerName").trim();
                java.util.Date dateOfBirth = resultSet.getDate("DateOfBirth");
                String phoneNumber = resultSet.getString("PhoneNumber").trim();
                String email = resultSet.getString("Email").trim();
                int loyaltyPoints = resultSet.getInt("LoyaltyPoints");

                customerList.add(new Customer(customerID, customerName, dateOfBirth, phoneNumber, email, loyaltyPoints));
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

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String customerID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");

            preparedStatement.setString(1, customerID);

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
