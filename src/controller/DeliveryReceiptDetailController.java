package controller;

import DAO.imple.DeliveryReceiptDAO;
import DAO.imple.EmployeeDAO;
import DAO.imple.OrderDAO;
import DAO.itf.DeliveryReceiptDAOInterface;
import DAO.itf.EmployeeDAOInterface;
import DAO.itf.OrderDAOInterface;
import model.DeliveryReceiptDetail;
import model.SupplyRequestDetail;
import view.View;

import javax.swing.*;
import java.util.Objects;

public class DeliveryReceiptDetailController {
    private final DeliveryReceiptDAOInterface deliveryReceiptDAO;
    private final OrderDAOInterface orderDAO;
    private final SalesInvoiceDetailController salesInvoiceDetailController;
    private final EmployeeDAOInterface employeeDAO;

    DeliveryReceiptDetailController() {
        deliveryReceiptDAO = new DeliveryReceiptDAO();
        orderDAO = new OrderDAO();
        salesInvoiceDetailController = new SalesInvoiceDetailController();
        employeeDAO = new EmployeeDAO();
    }


    void process(View view) {
        String ID = JOptionPane.showInputDialog("Enter delivery receipt id you want to see details: ");
        MessageCode message = Controller.checkCode(Objects.requireNonNull(view.getTableChooser().getSelectedItem()).toString(), ID);
        if(message.equals(MessageCode.ID_ALREADY_EXISTS)){
            DeliveryReceiptDetail deliveryReceiptDetail = getDeliveryReceiptDetail(ID);
            if(deliveryReceiptDetail == null){
                View.showMessage(view.getContentPane(), MessageCode.ERROR_OCCURRED.getMessage());
                return;
            }
            view.createDeliveryReceiptDetailFrame(deliveryReceiptDetail);
        }else{
            View.showMessage(view.getDetailFrame(), message.getMessage());
        }
    }

    DeliveryReceiptDetail getDeliveryReceiptDetail(String deliveryReceiptID) {
        DeliveryReceiptDetail deliveryReceiptDetail = new DeliveryReceiptDetail();
        deliveryReceiptDetail.setDeliveryReceipt(deliveryReceiptDAO.get(deliveryReceiptID));
        if (deliveryReceiptDetail.getDeliveryReceipt() == null){
            return null;
        }
        deliveryReceiptDetail.setEmployee(employeeDAO.get(deliveryReceiptDetail.getDeliveryReceipt().getDeliveryEmployeeID()));
        deliveryReceiptDetail.setOrder(orderDAO.get(deliveryReceiptDetail.getDeliveryReceipt().getOrderID()));
        deliveryReceiptDetail.setSalesInvoiceDetail(salesInvoiceDetailController.getSalesInvoiceDetail(deliveryReceiptDetail.getOrder().getInvoiceID()));
        return deliveryReceiptDetail;
    }
}
