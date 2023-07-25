package DAO.imple;

import DAO.itf.SalesInvoiceDAOInterface;
import databaseConnection.DatabaseConnection;
import model.SalesInvoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesInvoiceDAO implements SalesInvoiceDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;


    @Override
    public int create(SalesInvoice salesInvoice) {
        int count;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO SalesInvoice (InvoiceID, InvoiceDate, CustomerID, PaymentMethod) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, salesInvoice.getInvoiceID());
            preparedStatement.setDate(2, new java.sql.Date(salesInvoice.getInvoiceDate().getTime()));
            preparedStatement.setString(3, salesInvoice.getCustomerID());
            preparedStatement.setString(4, salesInvoice.getPaymentMethod());
            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<SalesInvoice> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM SalesInvoice;");
            resultSet = preparedStatement.executeQuery();
            List<SalesInvoice> listSalesInvoice = new ArrayList<>();
            while (resultSet.next()) {
                listSalesInvoice.add(new SalesInvoice(
                        resultSet.getString("InvoiceID").trim(),
                        resultSet.getDate("InvoiceDate"),
                        resultSet.getString("CustomerID").trim(),
                        resultSet.getString("PaymentMethod").trim()));
            }
            return listSalesInvoice;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(SalesInvoice salesInvoice) {
        int count;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE SalesInvoice SET  InvoiceDate = ?, CustomerID = ?, PaymentMethod = ? WHERE InvoiceID = ?");

            preparedStatement.setDate(1, new java.sql.Date(salesInvoice.getInvoiceDate().getTime()));
            preparedStatement.setString(2, salesInvoice.getCustomerID());
            preparedStatement.setString(3, salesInvoice.getPaymentMethod());
            preparedStatement.setString(4, salesInvoice.getInvoiceID());
            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String saleInvoiceID) {
        int count;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM SalesInvoice WHERE InvoiceID = ?");

            preparedStatement.setString(1, saleInvoiceID);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public SalesInvoice getBySalesInvoiceID(String salesInvoiceID) {
        SalesInvoice salesInvoice = null;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM SalesInvoice WHERE InvoiceID = ?;");
            preparedStatement.setString(1, salesInvoiceID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                salesInvoice = new SalesInvoice(
                        resultSet.getString("InvoiceID").trim(),
                        resultSet.getDate("InvoiceDate"),
                        resultSet.getString("CustomerID").trim(),
                        resultSet.getString("PaymentMethod").trim());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return salesInvoice;
    }

    @Override
    public SalesInvoice getByCustomerID(String customerID) {
        SalesInvoice salesInvoice = null;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM SalesInvoice WHERE CustomerID = ?;");
            preparedStatement.setString(1, customerID);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                salesInvoice = new SalesInvoice(
                        resultSet.getString("InvoiceID").trim(),
                        resultSet.getDate("InvoiceDate"),
                        resultSet.getString("CustomerID").trim(),
                        resultSet.getString("PaymentMethod").trim());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return salesInvoice;
    }

}