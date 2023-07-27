package DAO.imple;

import DAO.itf.SupplyRequestDAOInterface;
import databaseConnection.DatabaseConnection;
import model.SupplyRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplyRequestDAO implements SupplyRequestDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<SupplyRequest> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM SupplyRequest;");
            resultSet = preparedStatement.executeQuery();
            List<SupplyRequest> supplyRequestList = new ArrayList<>();
            while (resultSet.next()) {
                SupplyRequest supplyRequest = new SupplyRequest(
                        resultSet.getString("SupplyRequestID").trim(),
                        resultSet.getDate("SupplyRequestDate"),
                        resultSet.getString("SupplyRequestStatus").trim(),
                        resultSet.getDate("ReceiveDate".trim()),
                        resultSet.getString("SupplierID").trim(),
                        resultSet.getString("EmployeeID").trim()
                );
                supplyRequestList.add(supplyRequest);
            }
            return supplyRequestList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(SupplyRequest supplyRequest) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO SupplyRequest (SupplyRequestID, SupplyRequestDate, SupplyRequestStatus, SupplierID, EmployeeID) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, supplyRequest.getSupplyRequestID());
            preparedStatement.setDate(2, new java.sql.Date(supplyRequest.getSupplyRequestDate().getTime()));
            preparedStatement.setString(3, supplyRequest.getSupplyRequestStatus());
            preparedStatement.setString(4, supplyRequest.getSupplierID());
            preparedStatement.setString(5, supplyRequest.getEmployeeID());
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(SupplyRequest supplyRequest) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE SupplyRequest SET SupplyRequestDate = ?, SupplyRequestStatus = ?, ReceiveDate = ?, SupplierID = ?, EmployeeID = ? WHERE SupplyRequestID = ?");
            preparedStatement.setDate(1, new java.sql.Date(supplyRequest.getSupplyRequestDate().getTime()));
            preparedStatement.setString(2, supplyRequest.getSupplyRequestStatus());
            preparedStatement.setDate(3, new java.sql.Date(supplyRequest.getReceiveDate().getTime()));
            preparedStatement.setString(4, supplyRequest.getSupplierID());
            preparedStatement.setString(5, supplyRequest.getEmployeeID());
            preparedStatement.setString(6, supplyRequest.getSupplyRequestID());
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(String supplyRequestID, String supplyRequestStatus) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE SupplyRequest SET SupplyRequestStatus = ? WHERE SupplyRequestID = ?");
            preparedStatement.setString(1, supplyRequestStatus);
            preparedStatement.setString(2, supplyRequestID);
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int update(String supplyRequestID, String supplyRequestStatus, Date receiveDate) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE SupplyRequest SET SupplyRequestStatus = ?, ReceiveDate = ? WHERE SupplyRequestID = ?");
            preparedStatement.setString(1, supplyRequestStatus);
            preparedStatement.setDate(2, new java.sql.Date(receiveDate.getTime()));
            preparedStatement.setString(3, supplyRequestID);
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }


    @Override
    public int delete(String supplyRequestID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM SupplyRequest WHERE SupplyRequestID = ?");
            preparedStatement.setString(1, supplyRequestID);
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public SupplyRequest getBySupplyRequestID(String supplyRequestID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM SupplyRequest WHERE SupplyRequestID = ?;");
            preparedStatement.setString(1, supplyRequestID);
            resultSet = preparedStatement.executeQuery();
            SupplyRequest supplyRequest = null;
            if (resultSet.next()) {
                supplyRequest = new SupplyRequest(
                        resultSet.getString("SupplyRequestID").trim(),
                        resultSet.getDate("SupplyRequestDate"),
                        resultSet.getString("SupplyRequestStatus").trim(),
                        resultSet.getDate("ReceiveDate".trim()),
                        resultSet.getString("SupplierID").trim(),
                        resultSet.getString("EmployeeID").trim()
                );
            }
            return supplyRequest;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<SupplyRequest> getBySupplyRequestStatus(String status) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM SupplyRequest WHERE SupplyRequestStatus = ?;");
            preparedStatement.setString(1, status);
            resultSet = preparedStatement.executeQuery();
            List<SupplyRequest> supplyRequestList = new ArrayList<>();
            while (resultSet.next()) {
                SupplyRequest supplyRequest = new SupplyRequest(
                        resultSet.getString("SupplyRequestID").trim(),
                        resultSet.getDate("SupplyRequestDate"),
                        resultSet.getString("SupplyRequestStatus").trim(),
                        resultSet.getDate("ReceiveDate".trim()),
                        resultSet.getString("SupplierID").trim(),
                        resultSet.getString("EmployeeID").trim()
                );
                supplyRequestList.add(supplyRequest);
            }
            return supplyRequestList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }
}
