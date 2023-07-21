package DAO.imple;

import DAO.itf.SupplierDAOInterface;
import databaseConnection.DatabaseConnection;
import model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO implements SupplierDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Supplier> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Supplier;");
            resultSet = preparedStatement.executeQuery();
            List<Supplier> supplierList = new ArrayList<>();
            while (resultSet.next()) {
                supplierList.add(new Supplier(
                        resultSet.getString("SupplierID").trim(),
                        resultSet.getString("SupplierName").trim(),
                        resultSet.getString("PhoneNumber").trim(),
                        resultSet.getString("Email").trim(),
                        resultSet.getString("Address").trim()));
            }
            return supplierList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(Supplier supplier) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO Supplier (SupplierID, SupplierName, PhoneNumber, Email, Address) VALUES (?, ?, ?, ?, ?);");

            preparedStatement.setString(1, supplier.getSupplierID());
            preparedStatement.setString(2, supplier.getSupplierName());
            preparedStatement.setString(3, supplier.getPhoneNumber());
            preparedStatement.setString(4, supplier.getEmail());
            preparedStatement.setString(5, supplier.getAddress());

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Supplier supplier) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE Supplier SET SupplierName = ?, PhoneNumber = ?, Email = ?, Address = ? WHERE SupplierID = ?");

            preparedStatement.setString(1, supplier.getSupplierName());
            preparedStatement.setString(2, supplier.getPhoneNumber());
            preparedStatement.setString(3, supplier.getEmail());
            preparedStatement.setString(4, supplier.getAddress());
            preparedStatement.setString(5, supplier.getSupplierID());

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(String supplierID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM Supplier WHERE SupplierID = ?");

            preparedStatement.setString(1, supplierID);

            int count = preparedStatement.executeUpdate();

            DatabaseConnection.close(connection, preparedStatement, null);

            return count;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
