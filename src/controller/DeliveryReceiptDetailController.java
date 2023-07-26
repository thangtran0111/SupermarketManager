package controller;

import DAO.DAOFactory;
import model.DeliveryReceiptDetail;
import view.View;

import javax.swing.*;
import java.util.Objects;

public class DeliveryReceiptDetailController {
    private final DAOFactory daoFactory;
    private final SalesInvoiceDetailController salesInvoiceDetailController;

    DeliveryReceiptDetailController(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
        salesInvoiceDetailController = new SalesInvoiceDetailController(this.daoFactory);
    }


    void process(View view) {
        String ID = JOptionPane.showInputDialog("Enter delivery receipt id you want to see details: ");
        Message message = Controller.checkCode(Objects.requireNonNull(view.getTableChooser().getSelectedItem()).toString(), ID);
        if (message.equals(Message.ID_ALREADY_EXISTS)) {
            DeliveryReceiptDetail deliveryReceiptDetail = getDeliveryReceiptDetail(ID);
            if (deliveryReceiptDetail == null) {
                View.showMessage(view.getContentPane(), Message.ERROR_OCCURRED.getMessage());
                return;
            }
            view.createDeliveryReceiptDetailFrame(deliveryReceiptDetail);
        } else {
            View.showMessage(view.getDetailFrame(), message.getMessage());
        }
    }

    DeliveryReceiptDetail getDeliveryReceiptDetail(String deliveryReceiptID) {
        DeliveryReceiptDetail deliveryReceiptDetail = new DeliveryReceiptDetail();
        deliveryReceiptDetail.setDeliveryReceipt(daoFactory.getDeliveryReceiptDAO().getByDeliveryReceiptID(deliveryReceiptID));
        if (deliveryReceiptDetail.getDeliveryReceipt() == null) {
            return null;
        }
        deliveryReceiptDetail.setEmployee(daoFactory.getEmployeeDAO().getByEmployeeID(deliveryReceiptDetail.getDeliveryReceipt().getDeliveryEmployeeID()));
        deliveryReceiptDetail.setOrder(daoFactory.getOrderDAO().get(deliveryReceiptDetail.getDeliveryReceipt().getOrderID()));
        deliveryReceiptDetail.setSalesInvoiceDetail(salesInvoiceDetailController.getSalesInvoiceDetail(deliveryReceiptDetail.getOrder().getInvoiceID()));
        return deliveryReceiptDetail;
    }
}
