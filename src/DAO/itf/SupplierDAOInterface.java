package DAO.itf;

import model.Supplier;

import java.util.List;

public interface SupplierDAOInterface {
    List<Supplier> read();
    int create(Supplier supplier);
    int update(Supplier supplier);
    int delete(String supplierID);
}
