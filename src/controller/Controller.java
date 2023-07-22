package controller;

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

    public Controller() {
        view = new View();
        this.loginProcessManager = new LoginProcessManager();
        this.searchProcessManager = new SearchProcessManager();
        this.addProcessManager = new AddProcessManager();
        this.tableSelectionProcessManager = new TableSelectionProcessManager();
        this.deleteProcessManager = new DeleteProcessManager();
        this.updateProcessManager = new UpdateProcessManager();
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
            if (selectedTable != null && selectedTable.equals(view.getTableName(4))) {
                Object[][] searchObject = searchProcessManager.processSearchMultipleRows(findText, selectedTable, (DefaultTableModel) view.getTable().getModel(), view.getFrame());
                view.createInfoFrameWithMultipleRows(view.getColumnNames(Objects.requireNonNull(selectedTable)), searchObject);

            } else if (selectedTable != null) {
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

        if (e.getSource() == view.getTableChooser()) {
            tableSelectionProcessManager.processTableSelection(view);
        }

    }

    public static MessageCode checkCode(String selectedTable, String ID) {
        if (ID == null) return MessageCode.NULL_ID;
        if (selectedTable.equals(view.getTableName(2))) {
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                if (model.getValueAt(rowIndex, 0).equals(ID)) {
                    return MessageCode.ID_ALREADY_EXISTS;
                } else if (model.getValueAt(rowIndex, 1).equals(ID)) {
                    return MessageCode.BARCODE_ALREADY_EXISTS;
                }
            }
        } else {
            if (!ID.matches("^\\d{5}")) return MessageCode.ID_FORMAT_INCORRECT;
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            for (int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++) {
                if (model.getValueAt(rowIndex, 0).equals(ID)) {
                    return MessageCode.ID_ALREADY_EXISTS;
                }
            }
        }

        return MessageCode.ID_NOT_EXIST;
    }

    public void addActionListener() {
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
    }
}
