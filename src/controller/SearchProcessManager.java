package controller;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static view.View.showMessage;

public class SearchProcessManager {
    public SearchProcessManager() {
    }

    public Object[] processSearch(String findText, String selectedTable, DefaultTableModel model, Component cmp) {
        Message message = Controller.checkCode(selectedTable, findText);
        Object[] rowData = null;

        if (message.equals(Message.ID_ALREADY_EXISTS)) {
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            for (int i = 0; i < rowCount; i++) {
                if (model.getValueAt(i, 0).equals(findText)) {
                    rowData = new Object[columnCount];
                    for (int j = 0; j < columnCount; j++) {
                        rowData[j] = model.getValueAt(i, j);
                    }
                    break;
                }
            }
        } else if (message.equals(Message.BARCODE_ALREADY_EXISTS)) {
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            for (int i = 0; i < rowCount; i++) {
                if (model.getValueAt(i, 1).equals(findText)) {
                    rowData = new Object[columnCount];
                    for (int j = 0; j < columnCount; j++) {
                        rowData[j] = model.getValueAt(i, j);
                    }
                    break;
                }
            }
        } else {
            showMessage(cmp, message.getMessage());
        }

        return rowData;
    }

    public Object[][] processSearchMultipleRows(String searchCode, String selectedTable, DefaultTableModel model, Component cmp) {
        Message message = Controller.checkCode(selectedTable, searchCode);
        List<Object[]> dataList = new ArrayList<>();

        if (message.equals(Message.ID_ALREADY_EXISTS) || message.equals(Message.BARCODE_ALREADY_EXISTS)) {
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            for (int i = 0; i < rowCount; i++) {
                if (model.getValueAt(i, 0).equals(searchCode)) {
                    Object[] rowData = new Object[columnCount];
                    for (int j = 0; j < columnCount; j++) {
                        rowData[j] = model.getValueAt(i, j);
                    }
                    dataList.add(rowData);
                }
            }
        } else {
            showMessage(cmp, message.getMessage());
        }

        Object[][] data = new Object[dataList.size()][];
        dataList.toArray(data);
        return data;
    }
}
