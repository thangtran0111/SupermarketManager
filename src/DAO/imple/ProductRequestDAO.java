package DAO.imple;

import DAO.itf.ProductRequestDAOInterface;
import databaseConnection.DatabaseConnection;
import model.ProductRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRequestDAO implements ProductRequestDAOInterface {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public int create(ProductRequest productRequest) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO ProductRequest (ProductID, SupplyRequestID, QuantityReceived, UnitPrice) VALUES (?, ?, ?, ?);");

            preparedStatement.setString(1, productRequest.getProductID());
            preparedStatement.setString(2, productRequest.getSupplyRequestID());
            preparedStatement.setInt(3, productRequest.getQuantityReceived());
            preparedStatement.setInt(4, productRequest.getUnitPrice());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public List<ProductRequest> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM ProductRequest;");
            resultSet = preparedStatement.executeQuery();
            List<ProductRequest> productRequestList = new ArrayList<>();
            while (resultSet.next()) {
                productRequestList.add(new ProductRequest(
                        resultSet.getString("SupplyRequestID").trim(),
                        resultSet.getString("ProductID").trim(),
                        resultSet.getInt("QuantityReceived"),
                        resultSet.getInt("UnitPrice")));
            }
            return productRequestList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public ProductRequest get(String supplyRequestID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM ProductRequest WHERE SupplyRequestID = ?;");
            preparedStatement.setString(1, supplyRequestID);
            resultSet = preparedStatement.executeQuery();
            ProductRequest productRequest = null;
            while (resultSet.next()) {
                productRequest = new ProductRequest(
                        resultSet.getString("ProductID").trim(),
                        resultSet.getString("SupplyRequestID").trim(),
                        resultSet.getInt("QuantityReceived"),
                        resultSet.getInt("UnitPrice"));
            }
            return productRequest;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public ProductRequest get(String supplyRequestID, String productID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM ProductRequest WHERE SupplyRequestID = ? AND ProductID = ?;");
            preparedStatement.setString(1, supplyRequestID);
            preparedStatement.setString(2, productID);
            resultSet = preparedStatement.executeQuery();
            ProductRequest productRequest = null;
            while (resultSet.next()) {
                productRequest = new ProductRequest(
                        resultSet.getString("ProductID").trim(),
                        resultSet.getString("SupplyRequestID").trim(),
                        resultSet.getInt("QuantityReceived"),
                        resultSet.getInt("UnitPrice"));
            }
            return productRequest;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    //TODO: xử lí vấn đề cặp khoá chính
    @Override
    public int update(ProductRequest productRequest) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE ProductRequest SET ProductID = ?, QuantityReceived = ?, UnitPrice = ? WHERE SupplyRequestID = ?");

            preparedStatement.setString(1, productRequest.getSupplyRequestID());
            preparedStatement.setInt(2, productRequest.getQuantityReceived());
            preparedStatement.setInt(3, productRequest.getUnitPrice());
            preparedStatement.setString(4, productRequest.getProductID());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String supplyRequestID) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM ProductRequest WHERE SupplyRequestID = ?");

            preparedStatement.setString(1, supplyRequestID);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String supplyProductID, String productID) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM ProductRequest WHERE SupplyRequestID = ? AND ProductID = ?");
            preparedStatement.setString(1, supplyProductID);
            preparedStatement.setString(2, productID);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }
}
