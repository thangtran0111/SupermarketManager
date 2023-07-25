package DAO.itf;

import model.DeliveryReceipt;

import java.util.Date;
import java.util.List;

public interface DeliveryReceiptDAOInterface {
    int create(DeliveryReceipt deliveryReceipt);

    List<DeliveryReceipt> read();

    int update(DeliveryReceipt deliveryReceipt);

    int deleteByDeliveryReceiptID(String deliveryReceiptID);

    int deleteByOrderID(String orderID);

    DeliveryReceipt getByDeliveryReceiptID(String deliveryReceiptID);

    List<DeliveryReceipt> getByOrderID(String orderID);

    List<DeliveryReceipt> getByDeliveryStatus(String deliveryStatus);

    List<DeliveryReceipt> getByDeliveryDate(Date deliveryDate);
}
