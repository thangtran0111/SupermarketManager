package controller;

import DAO.DAOFactory;
import view.View;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Controller implements ActionListener {
    private static View view;
    private final LoginProcessManager loginProcessManager;
    private final SearchProcessManager searchProcessManager;
    private final TableSelectionProcessManager tableSelectionProcessManager;
    private final AddProcessManager addProcessManager;
    private final DeleteProcessManager deleteProcessManager;
    private final UpdateProcessManager updateProcessManager;
    private final SalesInvoiceDetailController salesInvoiceDetailController;
    private final SupplyRequestDetailController supplyRequestDetailController;
    private final DeliveryReceiptDetailController deliveryReceiptDetailController;

    public Controller() {
        view = new View();
        DAOFactory daoFactory = new DAOFactory();
        this.loginProcessManager = new LoginProcessManager(daoFactory);
        this.searchProcessManager = new SearchProcessManager();
        this.addProcessManager = new AddProcessManager(daoFactory);
        this.tableSelectionProcessManager = new TableSelectionProcessManager(daoFactory);
        this.deleteProcessManager = new DeleteProcessManager(daoFactory);
        this.updateProcessManager = new UpdateProcessManager(daoFactory);
        this.salesInvoiceDetailController = new SalesInvoiceDetailController(daoFactory);
        this.supplyRequestDetailController = new SupplyRequestDetailController(daoFactory);
        this.deliveryReceiptDetailController = new DeliveryReceiptDetailController(daoFactory);
        addActionListener();
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("loginButtonClicked".equals(actionCommand)) {
            loginProcessManager.processLogin(view);
        }

        if ("findButtonClicked".equals(actionCommand)) {
            String findText = view.getFindField().getText();
            String selectedTable = (String) view.getTableChooser().getSelectedItem();
            if (Objects.requireNonNull(selectedTable).equals(view.getTableName(0))) return;
            if (selectedTable.equals(view.getTableName(4)) || selectedTable.equals(view.getTableName(10))) {
                Object[][] searchObject = searchProcessManager.processSearchMultipleRows(findText, selectedTable, (DefaultTableModel) view.getTable().getModel(), view.getFrame());
                view.createInfoFrameWithMultipleRows(view.getColumnNames(Objects.requireNonNull(selectedTable)), searchObject);

            } else {
                Object[] searchObject = searchProcessManager.processSearch(findText, selectedTable, (DefaultTableModel) view.getTable().getModel(), view.getFrame());
                view.createInfoFrame(view.getColumnNames(Objects.requireNonNull(selectedTable)), searchObject);
            }

        }

        if ("closeInfoFrameButtonClicked".equals(actionCommand)) {
            view.getInfoFrame().dispose();
        }

        if ("outOfStockButtonClicked".equals(actionCommand)) {
            view.filterOutOfStockItems();
        }

        if ("addButtonClicked".equals(actionCommand)) {
            view.createAddFrame();
        }

        if ("addInAddFrameButtonClicked".equals(actionCommand)) {
            addProcessManager.processAdd(view);
        }

        if ("closeAddFrameButtonClicked".equals(actionCommand)) {
            view.getAddFrame().dispose();
        }

        if ("updateInUpdateFrameButtonClicked".equals(actionCommand)) {
            updateProcessManager.processUpdate(view);
        }

        if ("closeUpdateFrameButtonClicked".equals(actionCommand)) {
            view.getUpdateFrame().dispose();
        }

        if ("updateButtonClicked".equals(actionCommand)) {
            view.createUpdateFrame();
        }

        if ("deleteButtonClicked".equals(actionCommand)) {
            view.createDeleteFrame();
        }

        if ("closeDeleteFrameButtonClicked".equals(actionCommand)) {
            view.getDeleteFrame().dispose();
        }

        if (("deleteInDeleteFrameButtonClicked").equals(actionCommand)) {
            deleteProcessManager.processDelete(view);
        }

        if ("refreshButtonClicked".equals(actionCommand)) {
            tableSelectionProcessManager.processTableSelection(view);
        }

        if ("getSalesInvoiceDetailButtonClicked".equals(actionCommand)) {
            salesInvoiceDetailController.process(view);
        }

        if ("getSupplyRequestDetailButtonClicked".equals(actionCommand)) {
            supplyRequestDetailController.process(view);
        }

        if ("getDeliveryReceiptDetailButtonClicked".equals(actionCommand)) {
            deliveryReceiptDetailController.process(view);
        }
        if ("closeDetailFrameButtonClicked".equals(actionCommand)) {
            view.getDetailFrame().dispose();
        }

        if (e.getSource() == view.getTableChooser()) {
            tableSelectionProcessManager.processTableSelection(view);
        }

    }

    public static Message checkCode(String selectedTable, String ID) {
        if (ID == null) return Message.NULL_ID;
        if (selectedTable.equals(view.getTableName(2))) {
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                if (model.getValueAt(rowIndex, 0).equals(ID)) {
                    return Message.ID_ALREADY_EXISTS;
                } else if (model.getValueAt(rowIndex, 1).equals(ID)) {
                    return Message.BARCODE_ALREADY_EXISTS;
                }
            }
        } else {
            if (!ID.matches("^\\d{5}")) return Message.ID_FORMAT_INCORRECT;
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                if (model.getValueAt(rowIndex, 0).equals(ID)) {
                    return Message.ID_ALREADY_EXISTS;
                }
            }
        }

        return Message.ID_NOT_EXIST;
    }

    private void addActionListener() {
        view.getLoginButton().addActionListener(this);
        view.getFindButton().addActionListener(this);
        view.getTableChooser().addActionListener(this);
        view.getCloseInfoFrameButton().addActionListener(this);
        view.getOutOfStockButton().addActionListener(this);
        view.getAddButton().addActionListener(this);
        view.getAddInAddFrameButton().addActionListener(this);
        view.getCloseAddFrameButton().addActionListener(this);
        view.getUpdateButton().addActionListener(this);
        view.getUpdateInUpdateFrameButton().addActionListener(this);
        view.getCloseUpdateFrameButton().addActionListener(this);
        view.getDeleteButton().addActionListener(this);
        view.getDeleteInDeleteFrameButton().addActionListener(this);
        view.getCloseDeleteFrameButton().addActionListener(this);
        view.getRefreshButton().addActionListener(this);
        view.getGetDetailButton().addActionListener(this);
        view.getCloseDetailFrame().addActionListener(this);
    }
}
