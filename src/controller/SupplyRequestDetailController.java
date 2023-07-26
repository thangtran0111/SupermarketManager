package controller;

import DAO.DAOFactory;
import model.ProductRequest;
import model.SupplyRequestDetail;
import view.View;

import javax.swing.*;
import java.util.Objects;

public class SupplyRequestDetailController {
    private final DAOFactory daoFactory;

    SupplyRequestDetailController(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;
    }

    void process(View view) {
        String ID = JOptionPane.showInputDialog("Enter supply request id you want to see details: ");
        Message message = Controller.checkCode(Objects.requireNonNull(view.getTableChooser().getSelectedItem()).toString(), ID);
        if (message.equals(Message.ID_ALREADY_EXISTS)) {
            SupplyRequestDetail supplyRequestDetail = getSupplyRequestDetail(ID);
            if (supplyRequestDetail == null) {
                View.showMessage(view.getContentPane(), Message.ERROR_OCCURRED.getMessage());
                return;
            }
            view.createSupplyRequestDetailFrame(supplyRequestDetail);
        } else {
            View.showMessage(view.getDetailFrame(), message.getMessage());
        }
    }

    SupplyRequestDetail getSupplyRequestDetail(String supplyRequestID) {
        SupplyRequestDetail supplyRequestDetail = new SupplyRequestDetail();
        supplyRequestDetail.setSupplyRequest(daoFactory.getSupplyRequestDAO().getBySupplyRequestID(supplyRequestID));
        if (supplyRequestDetail.getSupplyRequest() == null)
            return null;
        supplyRequestDetail.setProductRequestList(daoFactory.getProductRequestDAO().get(supplyRequestID));
        for (ProductRequest productRequest : supplyRequestDetail.getProductRequestList()) {
            supplyRequestDetail.setTotal(supplyRequestDetail.getTotal() + (productRequest.getUnitPrice() * productRequest.getQuantityReceived()));
            supplyRequestDetail.getProductList().add(daoFactory.getProductDAO().getByProductID(productRequest.getProductID()));
        }
        supplyRequestDetail.setSupplier(daoFactory.getSupplierDAO().getBySupplierID(supplyRequestDetail.getSupplyRequest().getSupplierID()));
        supplyRequestDetail.setEmployee(daoFactory.getEmployeeDAO().getByEmployeeID(supplyRequestDetail.getSupplyRequest().getEmployeeID()));
        return supplyRequestDetail;
    }
}
