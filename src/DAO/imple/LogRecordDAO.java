package DAO.imple;

import DAO.itf.LogRecordDAOInterface;
import databaseConnection.DatabaseConnection;
import model.LogRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class LogRecordDAO implements LogRecordDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public int create(LogRecord logRecord) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO LogRecord(LogMessage, LogDateTime, Username) VALUES (?, ? ,?)");

            preparedStatement.setString(1, logRecord.getLogMessage());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(logRecord.getLogTime()));
            preparedStatement.setString(3, logRecord.getUsername());
            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, null);
        }
    }

}
