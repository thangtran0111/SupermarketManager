package view;

import controller.SearchProcessManager;
import model.SalesInvoice;
import model.InvoiceProduct;
import model.Product;
import model.Employee;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class View {
    private final int PADDING = 60;

    //column name
    //TODO thêm các bảng khác các bảng hiện có Product, Employee, SalesInvoice, InvoiceProduct
    private final Object[] productColumnNames = {"Product ID", "Barcode", "Product Name", "Retail Price", "Quantity In Stock", "Product Type", "Description"};
    private final Object[] employeeColumnNames = {"Employee ID", "Employee Name", "Phone Number", "Email", "Date Of Birth",  "Gender", "Address", "Position", "Salary"};
    private final Object[] salesInvoiceColumnNames = {"Invoice ID", "Invoice Date", "CustomerI D", "Payment method"};
    private final Object[] invoiceProductColumnNames = {"Invoice ID", "Product ID", "Quantity"};

    //login component
    private JFrame loginFrame;
    private JButton loginButton;
    private JTextField userTextField;
    private JPasswordField userPasswordField;

    //home component
    private JFrame homeFrame;
    private JPanel topHomePanel;
    private JPanel midHomePanel;
    private JPanel bottomHomePanel;
    private final String[] tableNames = new String[]{"", "Employee", "Product", "Hoá Đơn Bán Hàng", "Hoá Đơn Mặt Hàng"};
    private final JComboBox<String> tableChooser = new JComboBox<>(tableNames);
    private JTextField findField;
    private final JTable table = new JTable();
    private JScrollPane scrollPane = new JScrollPane(table);
    private final JLabel welcomeLabel = new JLabel("Welcome to Ảo Ma supermarket management", SwingConstants.CENTER);
    private final JButton findButton = new JButton("Find");
    private final JButton refreshButton = new JButton("Refresh");

    //function component
    private final JButton outOfStockButton = new JButton("Out of stock");
    private final JButton billDetailButton = new JButton("Detail");
    private JFrame addFrame;
    private final JButton addButton = new JButton("Add");
    private final JButton addInAddFrameButton = new JButton("Add");
    private final JButton closeAddFrameButton = new JButton("Close");
    private JFrame updateFrame;
    private final JButton updateButton = new JButton("Update");
    private final JButton updateInUpdateFrameButton = new JButton("Update");
    private final JButton closeUpdateFrameButton = new JButton("Close");
    private JFrame deleteFrame;
    private final JButton deleteButton = new JButton("Delete");
    private final JButton deleteInDeleteFrameButton = new JButton("Delete");
    private final JButton closeDeleteFrameButton = new JButton("Close");
    private JTextField[] newValuesField;

    // info component
    private JFrame infoFrame;
    private final JButton closeInfoFrameButton = new JButton("Close");



    public View() {
        createLogFrame();
    }

    public JTextField getUserTextField() {
        return userTextField;
    }
    private void createLogFrame() {
        loginFrame = new JFrame("Log in");
        loginFrame.setSize(300, 300);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/supermarkets.png"))).getImage());

        loginButton = new JButton("LOG IN");
        loginButton.setActionCommand("loginButtonClicked");

        userTextField = new JTextField("TestAccount");
        userPasswordField = new JPasswordField("TestAccount");

        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 0, 5));
        JPanel topHomePanel = new JPanel();
        JPanel bottomHomePanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();

        centerPanel.add(welcomeLabel);
        centerPanel.add(new JLabel("Username: "));
        centerPanel.add(userTextField);
        centerPanel.add(new JLabel("Password: "));
        centerPanel.add(userPasswordField);
        centerPanel.add(loginButton);

        loginFrame.add(topHomePanel, BorderLayout.SOUTH);
        loginFrame.add(bottomHomePanel, BorderLayout.NORTH);
        loginFrame.add(rightPanel, BorderLayout.WEST);
        loginFrame.add(leftPanel, BorderLayout.EAST);
        loginFrame.add(centerPanel, BorderLayout.CENTER);

        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public void createHomeFrame() {
        homeFrame = new JFrame("Supermarket management");
        homeFrame.setSize(1200, 675);
        homeFrame.setLayout(new FlowLayout());
        homeFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/supermarkets.png"))).getImage());

        topHomePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topHomePanel.setPreferredSize(new Dimension(homeFrame.getWidth() - PADDING, PADDING));

        midHomePanel = new JPanel(new FlowLayout());
        midHomePanel.setPreferredSize(new Dimension(homeFrame.getWidth() - PADDING, (homeFrame.getHeight() - 200)));

        bottomHomePanel = new JPanel();
        bottomHomePanel.setPreferredSize(new Dimension(homeFrame.getWidth() - PADDING, PADDING));

        findField = new JTextField(10);
        welcomeLabel.setPreferredSize(midHomePanel.getPreferredSize());
        findButton.setActionCommand("findButtonClicked");
        addButton.setActionCommand("addButtonClicked");
        updateButton.setActionCommand(("updateButtonClicked"));
        deleteButton.setActionCommand("deleteButtonClicked");
        refreshButton.setActionCommand("refreshButtonClicked");

        findButton.setEnabled(false);
        addButton.setEnabled(false);
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        refreshButton.setEnabled(false);

        topHomePanel.add(tableChooser);
        topHomePanel.add(findField);
        topHomePanel.add(findButton);
        topHomePanel.add(refreshButton);
        midHomePanel.add(welcomeLabel);

        bottomHomePanel.add(addButton);
        bottomHomePanel.add(updateButton);
        bottomHomePanel.add(deleteButton);


        homeFrame.add(topHomePanel);
        homeFrame.add(midHomePanel);
        homeFrame.add(bottomHomePanel);

        homeFrame.setLocationRelativeTo(null);
        homeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        homeFrame.setVisible(true);
    }

    public void createProductManagementFrame() {
        if (midHomePanel.isAncestorOf(welcomeLabel)) {
            midHomePanel.remove(welcomeLabel);
        }
        if(scrollPane.isAncestorOf(table)){
            scrollPane.remove(table);
        }
        if(midHomePanel.isAncestorOf(scrollPane)){
            midHomePanel.remove(scrollPane);
        }
        if(topHomePanel.isAncestorOf(billDetailButton)){
            topHomePanel.remove(billDetailButton);
        }

        if (!findButton.isEnabled()) {
            findButton.setEnabled(true);
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            refreshButton.setEnabled(true);
        }
        if (!topHomePanel.isAncestorOf(outOfStockButton)) {
            outOfStockButton.setActionCommand("outOfStockButtonClicked");
            topHomePanel.add(outOfStockButton);
        }

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(homeFrame.getWidth() - PADDING, (homeFrame.getHeight() - 200)));
        midHomePanel.add(scrollPane);

        homeFrame.revalidate();
        homeFrame.repaint();
    }

    public void createEmployeeManagementFrame() {
        if (midHomePanel.isAncestorOf(welcomeLabel)) {
            midHomePanel.remove(welcomeLabel);
        }
        if(scrollPane.isAncestorOf(table)) {
            scrollPane.remove(table);
        }
        if(midHomePanel.isAncestorOf(scrollPane)){
            midHomePanel.remove(scrollPane);
        }
        if (topHomePanel.isAncestorOf(outOfStockButton)) {
            topHomePanel.remove(outOfStockButton);
        }
        if(topHomePanel.isAncestorOf(billDetailButton)){
            topHomePanel.remove(billDetailButton);
        }
        if (!findButton.isEnabled()) {
            findButton.setEnabled(true);
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            refreshButton.setEnabled(true);
        }


        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(homeFrame.getWidth() - PADDING, (homeFrame.getHeight() - 200)));
        midHomePanel.add(scrollPane);

        homeFrame.revalidate();
        homeFrame.repaint();
    }

    public void createSaleInvoicesManagementFrame() {
        if (midHomePanel.isAncestorOf(welcomeLabel)) {
            midHomePanel.remove(welcomeLabel);
        }
        if(scrollPane.isAncestorOf(table)) {
            scrollPane.remove(table);
        }
        if(midHomePanel.isAncestorOf(scrollPane)){
            midHomePanel.remove(scrollPane);
        }
        if(topHomePanel.isAncestorOf(outOfStockButton)) {
            topHomePanel.remove(outOfStockButton);
        }
        if (!findButton.isEnabled()) {
            findButton.setEnabled(true);
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            refreshButton.setEnabled(true);
        }

        billDetailButton.setActionCommand("billDetailButtonClicked");
        topHomePanel.add(billDetailButton);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(homeFrame.getWidth() - PADDING, (homeFrame.getHeight() - 200)));
        midHomePanel.add(scrollPane);

        homeFrame.revalidate();
        homeFrame.repaint();
    }

    public void createInvoiceProductManagementFrame() {
        if (midHomePanel.isAncestorOf(welcomeLabel)) {
            midHomePanel.remove(welcomeLabel);
        }
        if(scrollPane.isAncestorOf(table)) {
            scrollPane.remove(table);
        }
        if(midHomePanel.isAncestorOf(scrollPane)){
            midHomePanel.remove(scrollPane);
        }
        if(topHomePanel.isAncestorOf(billDetailButton)){
            topHomePanel.remove(billDetailButton);
        }

        if (topHomePanel.isAncestorOf(outOfStockButton)) {
            topHomePanel.remove(outOfStockButton);
        }
        if (!findButton.isEnabled()) {
            findButton.setEnabled(true);
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            refreshButton.setEnabled(true);
        }

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(homeFrame.getWidth() - PADDING, (homeFrame.getHeight() - 200)));
        midHomePanel.add(scrollPane);

        homeFrame.revalidate();
        homeFrame.repaint();
    }


    public void createInfoFrame(Object[] titles, Object[] data) {
        infoFrame = new JFrame("formation");
        infoFrame.setLayout(new BorderLayout());
        infoFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/info.png"))).getImage());

        int rowCount = titles.length;

        JPanel topInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topInfoPanel.add(new JLabel("THÔNG TIN"));

        JPanel midInfoPanel = new JPanel(new GridLayout(rowCount, 2, 5, 5));
        midInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JPanel bottomInfoPanel = new JPanel();
        bottomInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < rowCount; i++) {
            String title = String.valueOf(titles[i]);
            midInfoPanel.add(new JLabel(title));
            if (data[i] == null) {
                midInfoPanel.add(new JLabel());
            } else {
                midInfoPanel.add(new JLabel(data[i].toString()));
            }
        }

        closeInfoFrameButton.setActionCommand("closeInfoFrameButtonClicked");

        bottomInfoPanel.add(closeInfoFrameButton);

        infoFrame.add(topInfoPanel, BorderLayout.NORTH);
        infoFrame.add(midInfoPanel, BorderLayout.CENTER);
        infoFrame.add(bottomInfoPanel, BorderLayout.SOUTH);

        infoFrame.pack();
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setVisible(true);
    }

    public void createInfoFrameWithMultipleRows(Object[] titles, Object[][] data) {
        infoFrame = new JFrame("formation");
        infoFrame.setLayout(new BorderLayout());
        infoFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/info.png"))).getImage());

        int rowCount = data.length;
        int columnCount = titles.length - 1;

        JPanel topInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        topInfoPanel.add(new JLabel("THÔNG TIN" + data[0][0]));

        JPanel midInfoPanel = new JPanel(new GridLayout(rowCount + 1, columnCount, 5, 5));
        midInfoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        JPanel bottomInfoPanel = new JPanel();
        bottomInfoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        for (int i = 1; i < titles.length; i++) {
            String title = String.valueOf(titles[i]);
            midInfoPanel.add(new JLabel(title));
        }
        for (Object[] datum : data) {
            for (int j = 1; j < titles.length; j++) {
                if (datum[j] == null) {
                    midInfoPanel.add(new JLabel());
                } else {
                    midInfoPanel.add(new JLabel(String.valueOf(datum[j])));
                    System.out.println(datum[j]);

                }
            }
        }

        closeInfoFrameButton.setActionCommand("closeInfoFrameButtonClicked");

        bottomInfoPanel.add(closeInfoFrameButton);

        infoFrame.add(topInfoPanel, BorderLayout.NORTH);
        infoFrame.add(midInfoPanel, BorderLayout.CENTER);
        infoFrame.add(bottomInfoPanel, BorderLayout.SOUTH);

        infoFrame.pack();
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setVisible(true);
    }


    public void filterOutOfStockItems() {
        DefaultTableModel filteredModel = new DefaultTableModel();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int col = 0; col < model.getColumnCount(); col++) {
            filteredModel.addColumn(model.getColumnName(col));
        }

        if (model.getRowCount() == 0) {
            return;
        }

        for (int row = 0; row < model.getRowCount(); row++) {
            int soLuongTonKho = (int) model.getValueAt(row, 3);
            if (soLuongTonKho < 10) {
                Object[] rowData = new Object[model.getColumnCount()];
                for (int col = 0; col < model.getColumnCount(); col++) {
                    rowData[col] = model.getValueAt(row, col);
                }
                filteredModel.addRow(rowData);
            }
        }

        table.setModel(filteredModel);
        scrollPane.setViewportView(table);
        if(midHomePanel.isAncestorOf(scrollPane)){
            midHomePanel.remove(scrollPane);
        }

        midHomePanel.add(scrollPane);
        midHomePanel.revalidate();
        midHomePanel.repaint();
    }

    public void createAddFrame(){
        addFrame = new JFrame("Add");
        addFrame.setSize(new Dimension(400, 400));
        addFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/add.png"))).getImage());

        int rowCount = table.getColumnCount();
        addFrame.setLayout(new FlowLayout());
        JPanel topAddPanel = new JPanel();
        topAddPanel.setPreferredSize(new Dimension(addFrame.getWidth(), PADDING));
        topAddPanel.add(new JLabel("THÊM", SwingConstants.CENTER));

        JPanel midAddPanel = new JPanel(new GridLayout(rowCount, 2));

        addInAddFrameButton.setActionCommand("addInAddFrameButtonClicked");
        closeAddFrameButton.setActionCommand("closeAddFrameButtonClicked");
        
        JPanel bottomAddPanel = new JPanel();
        bottomAddPanel.setPreferredSize(new Dimension(addFrame.getWidth(), PADDING));
        bottomAddPanel.add(addInAddFrameButton);
        bottomAddPanel.add(closeAddFrameButton);

        newValuesField = new JTextField[rowCount];
        for(int i = 0; i < rowCount; i++){
            midAddPanel.add(new JLabel(table.getModel().getColumnName(i)));
            newValuesField[i] = new JTextField();
            midAddPanel.add(newValuesField[i]);
        }

        addFrame.add(topAddPanel);
        addFrame.add(midAddPanel);
        addFrame.add(bottomAddPanel);

        addFrame.setLocationRelativeTo(null);
        addFrame.setVisible(true);
    }

    public void createUpdateFrame() {
        updateFrame = new JFrame("Update");
        updateFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/exchange.png"))).getImage());
        int rowCount = table.getColumnCount();
        updateFrame.setLayout(new BorderLayout());

        JPanel topUpdatePanel = new JPanel();
        topUpdatePanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        topUpdatePanel.add(new JLabel("CẬP NHẬT", SwingConstants.CENTER));
        updateFrame.add(topUpdatePanel, BorderLayout.NORTH);

        JPanel midUpdatePanel = new JPanel(new GridLayout(rowCount, 2));
        String selectedTable = Objects.requireNonNull(tableChooser.getSelectedItem()).toString();
        Object[] oldValue;

        String ma = JOptionPane.showInputDialog("Nhập mã " + selectedTable.toLowerCase() + " bạn muốn cập nhật");
        oldValue = new SearchProcessManager().processSearch(ma, selectedTable, (DefaultTableModel) table.getModel(), updateFrame);

        newValuesField = new JTextField[rowCount];
        for (int i = 0; i < rowCount; i++) {
            midUpdatePanel.add(new JLabel(table.getModel().getColumnName(i)));
            newValuesField[i] = new JTextField(String.valueOf(oldValue[i]));
            midUpdatePanel.add(newValuesField[i]);
        }

        newValuesField[0].setEnabled(false);

        updateFrame.add(midUpdatePanel, BorderLayout.CENTER);

        JPanel bottomUpdatePanel = new JPanel();
        bottomUpdatePanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        updateInUpdateFrameButton.setActionCommand("updateInUpdateFrameButtonClicked");
        closeUpdateFrameButton.setActionCommand("closeUpdateFrameButtonClicked");
        bottomUpdatePanel.add(updateInUpdateFrameButton);
        bottomUpdatePanel.add(closeUpdateFrameButton);
        updateFrame.add(bottomUpdatePanel, BorderLayout.SOUTH);

        updateFrame.pack();
        updateFrame.setLocationRelativeTo(null);
        updateFrame.setVisible(true);
    }


    public void createDeleteFrame() {
        deleteFrame = new JFrame("Delete");
        deleteFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/delete.png"))).getImage());
        int rowCount = table.getColumnCount();
        deleteFrame.setLayout(new BorderLayout());

        JPanel topDeletePanel = new JPanel();
        topDeletePanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        topDeletePanel.add(new JLabel("XOÁ", SwingConstants.CENTER));
        deleteFrame.add(topDeletePanel, BorderLayout.NORTH);

        JPanel midDeletePanel = new JPanel(new GridLayout(rowCount, 2));
        String selectedTable = Objects.requireNonNull(tableChooser.getSelectedItem()).toString();
        Object[] oldValue;

        String ma = JOptionPane.showInputDialog("Nhập mã " + selectedTable.toLowerCase() + " bạn muốn xoá");
        oldValue = new SearchProcessManager().processSearch(ma, selectedTable, (DefaultTableModel) table.getModel(), deleteFrame);

        newValuesField = new JTextField[rowCount];
        for (int i = 0; i < rowCount; i++) {
            midDeletePanel.add(new JLabel(table.getModel().getColumnName(i)));
            newValuesField[i] = new JTextField(String.valueOf(oldValue[i]));
            newValuesField[i].setEnabled(false);
            midDeletePanel.add(new JLabel(newValuesField[i].getText()));
        }

        deleteFrame.add(midDeletePanel, BorderLayout.CENTER);

        JPanel bottomDeletePanel = new JPanel();
        bottomDeletePanel.setBorder(BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING));
        deleteInDeleteFrameButton.setActionCommand("deleteInDeleteFrameButtonClicked");
        closeDeleteFrameButton.setActionCommand("closeDeleteFrameButtonClicked");
        bottomDeletePanel.add(deleteInDeleteFrameButton);
        bottomDeletePanel.add(closeDeleteFrameButton);
        deleteFrame.add(bottomDeletePanel, BorderLayout.SOUTH);

        deleteFrame.pack();
        deleteFrame.setLocationRelativeTo(null);
        deleteFrame.setVisible(true);
    }

    //TODO thêm các bảng khác các bảng hiện có Product, Employee, SalesInvoice, InvoiceProduct
    public Class<?> getClassByTableName(String selectedTable){
        return switch (selectedTable) {
            case "Employee" -> Employee.class;
            case "Product" -> Product.class;
            case "SalesInvoice" -> SalesInvoice.class;
            case "InvoiceProduct" -> InvoiceProduct.class;
            default -> null;
        };
    }
    //TODO thêm các bảng khác các bảng hiện có Product, Employee, SaleInvoices, InvoiceProduct
    public <T> T createObject(String selectedTable, JTextField[] _properties) {
        Class<?> cl = getClassByTableName(selectedTable);
        if (cl == null) {
            throw new IllegalArgumentException("Invalid table name: " + selectedTable);
        }
        try {
            Constructor<?> constructor = switch (selectedTable) {
                case "Product" ->
                        cl.getConstructor(String.class, String.class, String.class, Integer.class, int.class, String.class, String.class);
                case "Employee"->
                        cl.getConstructor(String.class, String.class, String.class, String.class, String.class, Date.class, String.class, String.class, String.class, int.class);
                case "SaleInvoices" ->
                        cl.getConstructor(String.class, Date.class, String.class, String.class);
                case "InvoiceProduct" ->
                        cl.getConstructor(String.class, String.class, Integer.class);
                default -> throw new IllegalStateException("Invalid table name: " + selectedTable);
            };
            String[] properties = new String[_properties.length];
            for (int i = 0; i < _properties.length; i++) {
                properties[i] = _properties[i].getText();
            }
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] convertedProperties = convertProperties(properties, parameterTypes);
            System.out.println(convertedProperties.length);
            return (T) constructor.newInstance(convertedProperties);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Object[] convertProperties(String[] properties, Class<?>[] parameterTypes) {
        Object[] convertedProperties = new Object[properties.length];
        for (int i = 0; i < properties.length; i++) {
            if (parameterTypes[i] == Date.class) {
                convertedProperties[i] = convertToDate(properties[i]);
            } else if (parameterTypes[i] == String.class) {
                convertedProperties[i] = properties[i];
            } else if (parameterTypes[i] == float.class || parameterTypes[i] == Float.class) {
                convertedProperties[i] = Float.parseFloat(properties[i]);
            } else if (parameterTypes[i] == int.class || parameterTypes[i] == Integer.class) {
                convertedProperties[i] = Integer.parseInt(properties[i]);
            }
        }
        return convertedProperties;
    }



    private static Date convertToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void showMessage(Component cmp, String message){
        JOptionPane.showMessageDialog(cmp, message);
    }

    public void clearNewsValue(){
        for (JTextField newValue : newValuesField) {
            newValue.setText("");
        }
    }

    public JPasswordField getUserPasswordField() {
        return userPasswordField;
    }

    public JFrame getHomeFrame() {
        return homeFrame;
    }


    public JTable getTable(){
        return table;
    }

    public JFrame getLoginFrame() { return loginFrame; }

    public JButton getFindButton() { return findButton; }

    public JButton getOutOfStockButton() { return outOfStockButton; }

    public JComboBox<String> getTableChooser() { return tableChooser; }

    public JPanel getTopHomePanel(){ return topHomePanel; }

    public JPanel getMidHomePanel(){ return midHomePanel; }

    public JPanel getBottomHomePanel(){ return bottomHomePanel; }

    public JFrame getInfoFrame(){ return infoFrame; }

    public JTextField getFindField(){ return findField; }

    public JButton getCloseInfoFrameButton(){ return closeInfoFrameButton; }

    public String getTableName(int index){ return tableNames[index]; }

    public Object[] getProductColumnNames(){ return productColumnNames; }

    public Object[] getEmployeeColumnNames(){ return employeeColumnNames; }

    public Object[] getSalesInvoiceColumnNames(){ return salesInvoiceColumnNames; }
    
    public Object[] getInvoiceProductColumnNames(){ return invoiceProductColumnNames; }

    //TODO thêm các bảng khác các bảng hiện có Product, Employee, SaleInvoices, InvoiceProduct
    public Object[] getColumnNames(String selectedTable){
        if(selectedTable.equals(tableNames[1])){
            return getEmployeeColumnNames();
        }else if(selectedTable.equals(tableNames[2])){
            return getProductColumnNames();
        }else if(selectedTable.equals(tableNames[3])){
            return getSalesInvoiceColumnNames();
        }else if(selectedTable.equals(tableNames[4])){
            return getInvoiceProductColumnNames();
        }
        return new Object[0];
    }

    public JTextField[] getNewFieldValues(){ return newValuesField; }

    public JFrame getAddFrame(){ return addFrame; }

    public JButton getAddButton(){ return addButton; }

    public JButton getUpdateButton(){ return updateButton; }

    public JButton getDeleteButton(){ return deleteButton; }

    public JButton getAddInAddFrameButton(){return addInAddFrameButton; }

    public JButton getCloseAddFrameButton(){ return closeAddFrameButton; }

    public JFrame getUpdateFrame() { return updateFrame; }

    public JFrame getDeleteFrame() { return deleteFrame; }

    public JButton getLoginButton() { return loginButton; }

    public JButton getRefreshButton() { return refreshButton; }

    public JButton getDeleteInDeleteFrameButton() { return deleteInDeleteFrameButton; }
    
    public JButton getCloseDeleteFrameButton(){ return closeDeleteFrameButton; }
    
    public JButton getCloseUpdateFrameButton(){ return closeUpdateFrameButton; }

    public JButton getUpdateInUpdateFrameButton(){return updateInUpdateFrameButton;}


}