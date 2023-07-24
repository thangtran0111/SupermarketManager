package DAO.itf;

import model.DeliveryReceipt;

import java.util.List;

public interface DeliveryReceiptDAOInterface {
    int create(DeliveryReceipt deliveryReceipt);

    List<DeliveryReceipt> read();

    int update(DeliveryReceipt deliveryReceipt);

    int delete(String deliveryReceiptID);

    DeliveryReceipt get(String deliveryReceiptID);
}
