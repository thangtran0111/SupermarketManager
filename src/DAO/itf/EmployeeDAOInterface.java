package DAO.itf;


import model.Employee;
import java.util.List;

public interface EmployeeDAOInterface {
    List<Employee> read();
    int create(Employee employee);
    int update(Employee employee);
    int delete(String employeeID);
}
