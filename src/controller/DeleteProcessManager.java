package controller;

import DAO.DAOFactory;
import view.View;

import java.util.Objects;

public class DeleteProcessManager {
    private final DAOFactory daoFactory;

    DeleteProcessManager(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    void processDelete(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        if (Objects.requireNonNull(selectedTable).equals(view.getTableName(0))) return;
        String ID = view.getNewFieldValues()[0].getText();
        MessageCode message = Controller.checkCode(selectedTable, view.getNewFieldValues()[0].getText());
        int count = 0;
        if (message.equals(MessageCode.ID_ALREADY_EXISTS) || message.equals(MessageCode.BARCODE_ALREADY_EXISTS)) {
            if (Objects.requireNonNull(selectedTable).equals(view.getTableName(1))) {
                if (!ID.equals("99999")) {
                    count = daoFactory.getEmployeeDAO().delete(ID);
                }
            } else if (selectedTable.equals(view.getTableName(2))) {
                count = daoFactory.getProductDAO().delete(ID);
            } else if (selectedTable.equals(view.getTableName(3))) {
                count = daoFactory.getInvoiceProductDAO().delete(ID);
                count += daoFactory.getSalesInvoiceDAO().delete(ID);
            } else if (selectedTable.equals(view.getTableName(4))) {
                count = daoFactory.getInvoiceProductDAO().delete(ID, view.getNewFieldValues()[1].getText());
            } else if (selectedTable.equals(view.getTableName(5))) {
                count = daoFactory.getCustomerDAO().delete(ID);
            } else if (selectedTable.equals(view.getTableName(6))) {
                count = daoFactory.getSupplierDAO().delete(ID);
            } else if (selectedTable.equals(view.getTableName(7))) {
                count = daoFactory.getDeliveryReceiptDAO().deleteByOrderID(ID);
                count += daoFactory.getOrderDAO().delete(ID);
            } else if (selectedTable.equals(view.getTableName(8))) {
                count = daoFactory.getDeliveryReceiptDAO().deleteByDeliveryReceiptID(ID);
            } else if (selectedTable.equals(view.getTableName(9))) {
                count = daoFactory.getProductRequestDAO().delete(ID);
                count += daoFactory.getSupplyRequestDAO().delete(ID);
            } else if (selectedTable.equals(view.getTableName(10))) {
                count = daoFactory.getProductRequestDAO().delete(ID, view.getNewFieldValues()[1].getText());
            }
        } else {
            View.showMessage(view.getAddFrame(), message.getMessage());
            view.getDeleteFrame().dispose();
            view.createDeleteFrame();
        }
        if (count == 0) {
            View.showMessage(view.getDeleteFrame(), MessageCode.ERROR_OCCURRED.getMessage());
        } else {
            View.showMessage(view.getDeleteFrame(), MessageCode.SUCCESS.getMessage());
        }
    }
}
