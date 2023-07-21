package DAO.imple;

import DAO.itf.WarehouseReceiptDAOInterface;
import databaseConnection.DatabaseConnection;
import model.WarehouseReceipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseReceiptDAO implements WarehouseReceiptDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<WarehouseReceipt> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM WarehouseReceipt;");
            resultSet = preparedStatement.executeQuery();
            List<WarehouseReceipt> warehouseReceiptList = new ArrayList<>();
            while (resultSet.next()) {
                WarehouseReceipt warehouseReceipt = new WarehouseReceipt(
                        resultSet.getString("WarehouseReceiptID").trim(),
                        resultSet.getDate("WarehouseReceiptDate"),
                        resultSet.getString("SupplierID").trim(),
                        resultSet.getString("EmployeeID").trim()
                );
                warehouseReceiptList.add(warehouseReceipt);
            }
            return warehouseReceiptList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(WarehouseReceipt warehouseReceipt) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO WarehouseReceipt (WarehouseReceiptID, WarehouseReceiptDate, SupplierID, EmployeeID) VALUES (?, ?, ?, ?);");

            preparedStatement.setString(1, warehouseReceipt.getWarehouseReceiptID());
            preparedStatement.setDate(2, new java.sql.Date(warehouseReceipt.getReceiptDate().getTime()));
            preparedStatement.setString(3, warehouseReceipt.getSupplierID());
            preparedStatement.setString(4, warehouseReceipt.getEmployeeID());

            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(WarehouseReceipt warehouseReceipt) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE WarehouseReceipt SET WarehouseReceiptDate = ?, SupplierID = ?, EmployeeID = ? WHERE WarehouseReceiptID = ?");

            preparedStatement.setDate(1, new java.sql.Date(warehouseReceipt.getReceiptDate().getTime()));
            preparedStatement.setString(2, warehouseReceipt.getSupplierID());
            preparedStatement.setString(3, warehouseReceipt.getEmployeeID());
            preparedStatement.setString(4, warehouseReceipt.getWarehouseReceiptID());

            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int delete(String warehouseReceiptID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM WarehouseReceipt WHERE WarehouseReceiptID = ?");

            preparedStatement.setString(1, warehouseReceiptID);

            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }
}
