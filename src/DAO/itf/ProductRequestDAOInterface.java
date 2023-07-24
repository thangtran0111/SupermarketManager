package DAO.itf;

import model.ProductRequest;

import java.util.List;

public interface ProductRequestDAOInterface {
    int create(ProductRequest productRequest);
    List<ProductRequest> read();
    ProductRequest get(String supplyRequestID);
    ProductRequest get(String supplyRequestID, String productID);
    int update(ProductRequest productRequest);
    int delete(String supplyRequestID);

    int delete(String supplyProductID, String productID);
}
