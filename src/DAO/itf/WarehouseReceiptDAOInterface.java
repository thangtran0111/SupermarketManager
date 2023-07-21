package DAO.itf;

import model.WarehouseReceipt;
import java.util.List;

public interface WarehouseReceiptDAOInterface {
    List<WarehouseReceipt> read();
    int create(WarehouseReceipt warehouseReceipt);
    int update(WarehouseReceipt warehouseReceipt);
    int delete(String warehouseReceiptID);
}
