package DAO.imple;

import DAO.itf.ProductDAOInterface;
import databaseConnection.DatabaseConnection;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements ProductDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Product> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Product;");
            resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()) {
                productList.add(new Product(
                        resultSet.getString("ProductID").trim(),
                        resultSet.getString("Barcode").trim(),
                        resultSet.getString("ProductName").trim(),
                        resultSet.getInt("RetailPrice"),
                        resultSet.getInt("QuantityInStock"),
                        resultSet.getString("ProductType").trim(),
                        resultSet.getString("Description").trim()));
            }
            return productList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Product get(String code) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Product WHERE ProductID = ? OR Barcode = ?;");
            preparedStatement.setString(1, code);
            preparedStatement.setString(2, code);
            resultSet = preparedStatement.executeQuery();
            Product product = null;
            while (resultSet.next()) {
                product = (new Product(
                        resultSet.getString("ProductID").trim(),
                        resultSet.getString("Barcode").trim(),
                        resultSet.getString("ProductName").trim(),
                        resultSet.getInt("RetailPrice"),
                        resultSet.getInt("QuantityInStock"),
                        resultSet.getString("ProductType").trim(),
                        resultSet.getString("Description").trim()));
            }
            return product;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(Product product) {
        int count = 0;
        try{
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT Product (ProductID, Barcode, ProductName, RetailPrice, QuantityInStock, ProductType, Description) VALUES (?, ?, ?, ?, ?, ?, ?);");

            preparedStatement.setString(1, product.getProductID());
            preparedStatement.setString(2, product.getBarcode());
            preparedStatement.setString(3, product.getProductName());
            preparedStatement.setFloat(4, product.getRetailPrice());
            preparedStatement.setInt(5, product.getQuantityInStock());
            preparedStatement.setString(6, product.getProductType());
            preparedStatement.setString(7, product.getDescription());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int update(Product product) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE Product SET ProductName = ?, RetailPrice = ?, QuantityInStock = ?, ProductType = ?, Description = ? WHERE ProductID = ?");

            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setFloat(2, product.getRetailPrice());
            preparedStatement.setInt(3, product.getQuantityInStock());
            preparedStatement.setString(4, product.getProductType());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getProductID());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String productID) {
        int count = 0;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM Product WHERE ProductID = ?");

            preparedStatement.setString(1, productID);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }
}
