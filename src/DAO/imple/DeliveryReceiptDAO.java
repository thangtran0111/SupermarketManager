package DAO.imple;

import DAO.itf.DeliveryReceiptDAOInterface;
import databaseConnection.DatabaseConnection;
import model.DeliveryReceipt;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryReceiptDAO implements DeliveryReceiptDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<DeliveryReceipt> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM DeliveryReceipt;");
            resultSet = preparedStatement.executeQuery();
            List<DeliveryReceipt> deliveryReceiptList = new ArrayList<>();
            while (resultSet.next()) {
                DeliveryReceipt deliveryReceipt = new DeliveryReceipt(
                        resultSet.getString("DeliveryReceiptID").trim(),
                        resultSet.getDate("DeliveryDate"),
                        resultSet.getString("DeliveryStatus").trim(),
                        resultSet.getString("OrderID").trim(),
                        resultSet.getString("DeliveryEmployeeID").trim()
                );
                deliveryReceiptList.add(deliveryReceipt);
            }
            return deliveryReceiptList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(DeliveryReceipt deliveryReceipt) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO DeliveryReceipt (DeliveryReceiptID, DeliveryDate, DeliveryStatus, OrderID, DeliveryEmployeeID) VALUES (?, ?, ?, ?, ?);");

            preparedStatement.setString(1, deliveryReceipt.getDeliveryReceiptID());
            preparedStatement.setDate(2, new java.sql.Date(deliveryReceipt.getDeliveryDate().getTime()));
            preparedStatement.setString(3, deliveryReceipt.getDeliveryStatus());
            preparedStatement.setString(4, deliveryReceipt.getOrderID());
            preparedStatement.setString(5, deliveryReceipt.getDeliveryEmployeeID());

            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(DeliveryReceipt deliveryReceipt) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE DeliveryReceipt SET DeliveryDate = ?, DeliveryStatus = ?, OrderID = ?, DeliveryEmployeeID = ? WHERE DeliveryReceiptID = ?");

            preparedStatement.setDate(1, new java.sql.Date(deliveryReceipt.getDeliveryDate().getTime()));
            preparedStatement.setString(2, deliveryReceipt.getDeliveryStatus());
            preparedStatement.setString(3, deliveryReceipt.getOrderID());
            preparedStatement.setString(4, deliveryReceipt.getDeliveryEmployeeID());
            preparedStatement.setString(5, deliveryReceipt.getDeliveryReceiptID());

            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int delete(String deliveryReceiptID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM DeliveryReceipt WHERE DeliveryReceiptID = ?");

            preparedStatement.setString(1, deliveryReceiptID);

            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }
}