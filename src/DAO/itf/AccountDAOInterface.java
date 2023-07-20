package DAO.itf;

import model.Account;

import java.util.List;

public interface AccountDAOInterface {
    boolean update();
    boolean create();
    boolean delete();
    Account get(String userName);
    List<Account> read();
}
