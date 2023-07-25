package DAO.imple;

import DAO.itf.EmployeeDAOInterface;
import databaseConnection.DatabaseConnection;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO implements EmployeeDAOInterface {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Override
    public List<Employee> read() {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Employee;");
            resultSet = preparedStatement.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                employeeList.add(new Employee(resultSet.getString("EmployeeID").trim(),
                        resultSet.getString("EmployeeName").trim(),
                        resultSet.getString("IDNumber").trim(),
                        resultSet.getString("PhoneNumber").trim(),
                        resultSet.getString("Email").trim(),
                        resultSet.getDate("DateOfBirth"),
                        resultSet.getString("Gender").trim(),
                        resultSet.getString("Address").trim(),
                        resultSet.getString("Position").trim(),
                        resultSet.getInt("Salary")));
            }
            return employeeList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public int create(Employee employee) {
        int count;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("INSERT INTO Employee (EmployeeID, EmployeeName, IDNumber, PhoneNumber, Email, DateOfBirth, Gender, Address, Position, Salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            preparedStatement.setString(1, employee.getEmployeeID());
            preparedStatement.setString(2, employee.getEmployeeName());
            preparedStatement.setString(3, employee.getIDNumber());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getEmail());
            preparedStatement.setDate(6, new java.sql.Date(employee.getDateOfBirth().getTime()));
            preparedStatement.setString(7, employee.getGender());
            preparedStatement.setString(8, employee.getAddress());
            preparedStatement.setString(9, employee.getPosition());
            preparedStatement.setInt(10, employee.getSalary());

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int update(Employee employee) {
        int count;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("UPDATE Employee SET EmployeeName = ?, IDNumber = ?, PhoneNumber = ?, Email = ?, DateOfBirth = ?, Gender = ?, Address = ?, Position = ?, Salary = ? WHERE EmployeeID = ?");

            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setString(2, employee.getIDNumber());
            preparedStatement.setString(3, employee.getPhoneNumber());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setDate(5, new java.sql.Date(employee.getDateOfBirth().getTime()));
            preparedStatement.setString(6, employee.getGender());
            preparedStatement.setString(7, employee.getAddress());
            preparedStatement.setString(8, employee.getPosition());
            preparedStatement.setInt(9, employee.getSalary());
            preparedStatement.setString(10, employee.getEmployeeID());

            count = preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public int delete(String EmployeeID) {
        int count;
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("DELETE FROM Employee WHERE EmployeeID = ?");

            preparedStatement.setString(1, EmployeeID);

            count = preparedStatement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
        return count;
    }

    @Override
    public Employee getByEmployeeID(String employeeID) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE EmployeeID = ?");
            preparedStatement.setString(1, employeeID);
            resultSet = preparedStatement.executeQuery();
            Employee employee = null;
            if (resultSet.next()) {
                employee = new Employee(resultSet.getString("EmployeeID").trim(),
                        resultSet.getString("EmployeeName").trim(),
                        resultSet.getString("IDNumber").trim(),
                        resultSet.getString("PhoneNumber").trim(),
                        resultSet.getString("Email").trim(),
                        resultSet.getDate("DateOfBirth"),
                        resultSet.getString("Gender").trim(),
                        resultSet.getString("Address").trim(),
                        resultSet.getString("Position").trim(),
                        resultSet.getInt("Salary"));
            }
            return employee;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Employee> getByPhoneNumber(String phoneNumber) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE PhoneNumber = ?");
            preparedStatement.setString(1, phoneNumber);
            resultSet = preparedStatement.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getString("EmployeeID").trim(),
                        resultSet.getString("EmployeeName").trim(),
                        resultSet.getString("IDNumber").trim(),
                        resultSet.getString("PhoneNumber").trim(),
                        resultSet.getString("Email").trim(),
                        resultSet.getDate("DateOfBirth"),
                        resultSet.getString("Gender").trim(),
                        resultSet.getString("Address").trim(),
                        resultSet.getString("Position").trim(),
                        resultSet.getInt("Salary"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Employee> getByEmail(String email) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE Email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getString("EmployeeID").trim(),
                        resultSet.getString("EmployeeName").trim(),
                        resultSet.getString("IDNumber").trim(),
                        resultSet.getString("PhoneNumber").trim(),
                        resultSet.getString("Email").trim(),
                        resultSet.getDate("DateOfBirth"),
                        resultSet.getString("Gender").trim(),
                        resultSet.getString("Address").trim(),
                        resultSet.getString("Position").trim(),
                        resultSet.getInt("Salary"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Employee> getByIDNumber(String idNumber) {
        try {
            connection = DatabaseConnection.connect();
            preparedStatement = connection.prepareStatement("SELECT * FROM Employee WHERE IDNumber = ?");
            preparedStatement.setString(1, idNumber);
            resultSet = preparedStatement.executeQuery();
            List<Employee> employeeList = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee(resultSet.getString("EmployeeID").trim(),
                        resultSet.getString("EmployeeName").trim(),
                        resultSet.getString("IDNumber").trim(),
                        resultSet.getString("PhoneNumber").trim(),
                        resultSet.getString("Email").trim(),
                        resultSet.getDate("DateOfBirth"),
                        resultSet.getString("Gender").trim(),
                        resultSet.getString("Address").trim(),
                        resultSet.getString("Position").trim(),
                        resultSet.getInt("Salary"));
                employeeList.add(employee);
            }
            return employeeList;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(connection, preparedStatement, resultSet);
        }
    }

}

