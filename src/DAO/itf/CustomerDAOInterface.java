package DAO.itf;

import model.Customer;

import java.util.List;

public interface CustomerDAOInterface {
    List<Customer> read();
    int create(Customer customer);
    int update(Customer customer);
    int delete(String customerID);
}
