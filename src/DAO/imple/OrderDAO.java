package DAO.imple;

import DAO.itf.OrderDAOInterface;
import databaseConnection.DatabaseConnection;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements OrderDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public int create(Order order) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO Orders (OrderID, InvoiceID, ExpectedDeliveryDate, DeliveryAddress, Notes) VALUES (?, ?, ?, ?, ?);");

            preparedStatement.setString(1, order.getOrderID());
            preparedStatement.setString(2, order.getInvoiceID());
            preparedStatement.setString(3, String.valueOf(order.getExpectedDeliveryDate()));
            preparedStatement.setString(4, order.getDeliveryAddress());
            preparedStatement.setString(5, order.getNotes());

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Orders;");
            resultSet = preparedStatement.executeQuery();
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()) {
                orderList.add(new Order(
                        resultSet.getString("OrderID").trim(),
                        resultSet.getString("InvoiceID").trim(),
                        resultSet.getDate("ExpectedDeliveryDate"),
                        resultSet.getString("DeliveryAddress").trim(),
                        resultSet.getString("Notes").trim()));
            }
            return orderList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(Order order) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE Orders SET InvoiceID = ?, ExpectedDeliveryDate = ?, DeliveryAddress = ?, Notes = ? WHERE OrderID = ?");

            preparedStatement.setString(1, order.getInvoiceID());
            preparedStatement.setString(2, order.getExpectedDeliveryDate().toString());
            preparedStatement.setString(3, order.getDeliveryAddress());
            preparedStatement.setString(4, order.getNotes());
            preparedStatement.setString(5, order.getOrderID());

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String orderID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM Orders WHERE OrderID = ?");

            preparedStatement.setString(1, orderID);

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order get(String orderID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Orders WHERE OrderID = ?;");
            preparedStatement.setString(1, orderID);
            resultSet = preparedStatement.executeQuery();
            Order order = new Order();
            if (resultSet.next()) {
                order = new Order(
                        resultSet.getString("OrderID").trim(),
                        resultSet.getString("InvoiceID").trim(),
                        resultSet.getDate("ExpectedDeliveryDate"),
                        resultSet.getString("DeliveryAddress").trim(),
                        resultSet.getString("Notes").trim());
            }
            return order;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }
}
