package DAO.imple;

import DAO.itf.InvoiceProductDAOInterface;
import databaseConnection.DatabaseConnection;
import model.InvoiceProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//TODO: lỗi nhiều

public class InvoiceProductDAO implements InvoiceProductDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    @Override
    public int create(InvoiceProduct invoiceProduct) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO InvoiceProduct (InvoiceID, ProductID, Quantity) VALUES (?, ?, ?)");

            preparedStatement.setString(1, invoiceProduct.getInvoiceID());
            preparedStatement.setString(2, invoiceProduct.getProductID());
            preparedStatement.setInt(3, invoiceProduct.getQuantity());

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<InvoiceProduct> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM InvoiceProduct;");
            resultSet = preparedStatement.executeQuery();
            List<InvoiceProduct> listInvoiceProduct = new ArrayList<>();
            while (resultSet.next()) {
                listInvoiceProduct.add(new InvoiceProduct(
                        resultSet.getString("InvoiceID").trim(),
                        resultSet.getString("ProductID").trim(),
                        resultSet.getInt("Quantity")));
            }
            return listInvoiceProduct;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(InvoiceProduct invoiceProduct) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE InvoiceProduct SET Quantity = ? WHERE InvoiceID = ? AND ProductID = ?");

            preparedStatement.setInt(1, invoiceProduct.getQuantity());
            preparedStatement.setString(2, invoiceProduct.getInvoiceID());
            preparedStatement.setString(3, invoiceProduct.getProductID());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String invoiceID, String productID) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM InvoiceProduct WHERE InvoiceID = ? AND ProductID = ?");

            preparedStatement.setString(1, invoiceID);
            preparedStatement.setString(2, productID);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;

    }

    @Override
    public int delete(String invoiceID) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM InvoiceProduct WHERE InvoiceID = ?");

            preparedStatement.setString(1, invoiceID);
            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

}
