package DAO.itf;


import model.Employee;

import java.util.List;

public interface EmployeeDAOInterface {
    List<Employee> read();

    int create(Employee employee);

    int update(Employee employee);

    int delete(String employeeID);

    Employee getByEmployeeID(String employeeID);

    List<Employee> getByPhoneNumber(String phoneNumber);

    List<Employee> getByEmail(String email);

    List<Employee> getByIDNumber(String idNumber);
}
