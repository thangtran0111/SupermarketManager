package DAO.itf;

import model.Product;

import java.util.List;

public interface ProductDAOInterface {
    int create(Product mh);

    List<Product> read();

    Product getByProductID(String code);

    int update(Product product);

    int delete(String productID);

    List<Product> getByProductType(String productType);

    List<Product> getByProductTypeAndStockStatus(String productType, boolean inStock);
}
