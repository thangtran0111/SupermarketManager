package DAO.itf;

import model.Order;

import java.util.List;

public interface OrderDAOInterface {
    int create(Order order);
    List<Order> read();
    int update(Order order);
    int delete(String orderID);
}
