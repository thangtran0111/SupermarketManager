package controller;

import view.View;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.regex.Pattern;

public class Controller implements ActionListener {
    private static View view;
    private LoginProcessManager loginProcessManager;
    private SearchProcessManager searchProcessManager;
    private TableSelectionProcessManager tableSelectionProcessManager;
    private AddProcessManager addProcessManager;
    private DeleteProcessManager deleteProcessManager;
    private UpdateProcessManager updateProcessManager;
    public Controller() {
        view = new View();
        this.loginProcessManager = new LoginProcessManager();
        this.searchProcessManager = new SearchProcessManager();
        this.addProcessManager = new AddProcessManager();
        this.tableSelectionProcessManager = new TableSelectionProcessManager();
        this.deleteProcessManager = new DeleteProcessManager();
        this.updateProcessManager = new UpdateProcessManager();

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

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("loginButtonClicked".equals(actionCommand)) {
            loginProcessManager.processLogin(view);
        }

        if ("findButtonClicked".equals(actionCommand)) {
            String findText = view.getFindField().getText();
            String selectedTable = (String) view.getTableChooser().getSelectedItem();
                if(selectedTable.equals(view.getTableName(4))){
                    Object[][] searchObject = searchProcessManager.processSearchMultipleRows(findText, selectedTable, (DefaultTableModel) view.getTable().getModel(), view.getHomeFrame());
                    view.createInfoFrameWithMultipleRows(view.getColumnNames(Objects.requireNonNull(view.getTableChooser().getSelectedItem()).toString()), searchObject);

                }else {
                    Object[] searchObject = searchProcessManager.processSearch(findText, selectedTable, (DefaultTableModel) view.getTable().getModel(), view.getHomeFrame());
                    if (searchObject != null) {
                        view.createInfoFrame(view.getColumnNames(Objects.requireNonNull(view.getTableChooser().getSelectedItem()).toString()), searchObject);
                    }
                }

        }

        if ("closeInfoFrameButtonClicked".equals(actionCommand)) {
            view.getInfoFrame().dispose();
        }

        if("outOfStockButtonClicked".equals(actionCommand)){
            view.filterOutOfStockItems();
        }

        if("addButtonClicked".equals(actionCommand)){
            view.createAddFrame();
        }

        if("addInAddFrameButtonClicked".equals(actionCommand)){
            addProcessManager.processAdd(view);
        }

        if("closeAddFrameButtonClicked".equals(actionCommand)){
            view.getAddFrame().dispose();
        }

        if("updateInUpdateFrameButtonClicked".equals(actionCommand)){
            updateProcessManager.processUpdate(view);
        }

        if("closeUpdateFrameButtonClicked".equals(actionCommand)){
            view.getUpdateFrame().dispose();
        }

        if("updateButtonClicked".equals(actionCommand)){
            view.createUpdateFrame();
        }


        if("deleteButtonClicked".equals(actionCommand)){
            view.createDeleteFrame();
        }

        if("closeDeleteFrameButtonClicked".equals(actionCommand)){
            view.getDeleteFrame().dispose();
        }
        
        if (("deleteInDeleteFrameButtonClicked").equals(actionCommand)){
            deleteProcessManager.processDelete(view);
        }

        if ("refreshButtonClicked".equals(actionCommand)) {
            tableSelectionProcessManager.processTableSelection(view);
        }

        if (e.getSource() == view.getTableChooser()) {
            tableSelectionProcessManager.processTableSelection(view);
        }

    }

    public static String checkMa(String selectedTable, String ma){
        if(ma == null) return "Mã null";
        if(ma.length() != 5) return "Mã không đúng định dạng";
        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
        for(int rowIndex = 0; rowIndex < model.getRowCount(); rowIndex++){
            if(model.getValueAt(rowIndex, 0).equals(ma)){
                return "Mã đã tồn tại";
            }
        }
        Pattern pattern = null;
        //TODO: thêm các bảng còn lại các bảng hiện có Nhân Viên, Mặt Hàng, Hoá Đơn Bán Hàng
        if(selectedTable.equals(view.getTableName(1))){
            pattern = Pattern.compile("^NV\\d{3}");
        }else if(selectedTable.equals(view .getTableName(2))){
            pattern = Pattern.compile("^MH\\d{3}");
        }else if(selectedTable.equals(view.getTableName(3))){
            pattern = Pattern.compile("^HD\\d{3}");
        }

        if(!Objects.requireNonNull(pattern).matcher(ma).find()) return "Mã không đúng định dạng";
        return "Mã không tồn tại";
    }

}
