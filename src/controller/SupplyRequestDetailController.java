package controller;

import DAO.imple.*;
import DAO.itf.*;
import model.ProductRequest;
import model.SupplyRequestDetail;
import view.View;

import javax.swing.*;
import java.util.Objects;

public class SupplyRequestDetailController {
    private final SupplyRequestDAOInterface supplyRequestDAO;
    private final ProductRequestDAOInterface productRequestDAO;
    private final ProductDAOInterface productDAO;
    private final SupplierDAOInterface supplierDAO;
    private final EmployeeDAOInterface employeeDAO;

    public SupplyRequestDetailController() {
        this.supplyRequestDAO = new SupplyRequestDAO();
        this.productRequestDAO = new ProductRequestDAO();
        this.productDAO = new ProductDAO();
        this.supplierDAO = new SupplierDAO();
        this.employeeDAO = new EmployeeDAO();
    }

    void process(View view){
        String ID = JOptionPane.showInputDialog("Enter supply request id you want to see details: ");
        MessageCode message = Controller.checkCode(Objects.requireNonNull(view.getTableChooser().getSelectedItem()).toString(), ID);
        if(message.equals(MessageCode.ID_ALREADY_EXISTS)){
            SupplyRequestDetail supplyRequestDetail = getSupplyRequestDetail(ID);
            if(supplyRequestDetail == null){
                View.showMessage(view.getContentPane(), MessageCode.ERROR_OCCURRED.getMessage());
                return;
            }
            view.createSupplyRequestDetailFrame(supplyRequestDetail);
        }else{
            View.showMessage(view.getDetailFrame(), message.getMessage());
        }
    }
    SupplyRequestDetail getSupplyRequestDetail(String supplyRequestID){
        SupplyRequestDetail supplyRequestDetail = new SupplyRequestDetail();
        supplyRequestDetail.setSupplyRequest(supplyRequestDAO.get(supplyRequestID));
        if(supplyRequestDetail.getSupplyRequest() == null)
            return null;
        supplyRequestDetail.setProductRequestList(productRequestDAO.get(supplyRequestID));
        for(ProductRequest productRequest : supplyRequestDetail.getProductRequestList()){
            supplyRequestDetail.setTotal(supplyRequestDetail.getTotal() + (productRequest.getUnitPrice() * productRequest.getQuantityReceived()));
            supplyRequestDetail.getProductList().add(productDAO.get(productRequest.getProductID()));
        }
        supplyRequestDetail.setSupplier(supplierDAO.get(supplyRequestDetail.getSupplyRequest().getSupplierID()));
        supplyRequestDetail.setEmployee(employeeDAO.get(supplyRequestDetail.getSupplyRequest().getEmployeeID()));
        return supplyRequestDetail;
    }
}
