package DAO.itf;

import model.Customer;

import java.util.List;

public interface CustomerDAOInterface {
    List<Customer> read();

    int create(Customer customer);

    int update(Customer customer);

    int delete(String customerID);

    Customer getByCustomerID(String _customerID);

    List<Customer> getByPhoneNumber(String phoneNumber);

    List<Customer> getByEmail(String email);
}
