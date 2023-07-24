package view;


import controller.SearchProcessManager;
import model.*;

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

public class View extends JFrame {
    private final int PADDING = 60;
    private final int BORDER = 20;
    private final int NO_BORDER = 0;
    private final int HALF_BORDER = BORDER / 2;
    //column name
    private final Object[] productColumnNames = {"Product ID", "Barcode", "Product Name", "Retail Price", "Quantity In Stock", "Product Type", "Description"};
    private final Object[] employeeColumnNames = {"Employee ID", "Employee Name", "ID Number", "Phone Number", "Email", "Date Of Birth", "Gender", "Address", "Position", "Salary"};
    private final Object[] salesInvoiceColumnNames = {"Invoice ID", "Invoice Date", "Customer ID", "Payment Method"};
    private final Object[] invoiceProductColumnNames = {"Invoice ID", "Product ID", "Quantity"};
    private final Object[] customerColumnNames = {"Customer ID", "Customer Name", "Date Of Birth", "Phone Number", "Email", "LoyaltyPoints"};
    private final Object[] supplierColumnNames = {"SupplierID", "Supplier Name", "Phone Number", "Email", "Address"};
    private final Object[] orderColumnNames = {"Order ID", "Invoice ID", "Expected Delivery Date", "Delivery Address", "Notes"};
    private final Object[] deliveryReceiptColumnNames = {"Delivery Receipt ID", "Delivery Date", "Delivery Status", "Order ID", "Delivery Employee ID"};
    private final Object[] supplyRequestColumnNames = {"Supply Request ID", "Supply Request Date", "Supply Request Status", "Receive Date", "Supplier ID", "Employee ID"};
    private final Object[] productRequestColumnNames = {"Supply Request ID", "Product ID", "Quantity Received", "Unit Price"};
    //login component
    private JFrame loginFrame;
    private JButton loginButton;
    private JTextField userTextField;
    private JPasswordField userPasswordField;

    // component
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private final String[] tableNames = new String[]{"", "Employee", "Product", "SalesInvoice", "InvoiceProduct", "Customer", "Supplier", "Order", "DeliveryReceipt", "SupplyRequest", "ProductRequest"};
    private final JComboBox<String> tableChooser = new JComboBox<>(tableNames);
    private JTextField findField;
    private final JTable table = new JTable();
    private JScrollPane scrollPane = new JScrollPane(table);
    private final JLabel welcomeLabel = new JLabel("Welcome to Ảo Ma supermarket management", SwingConstants.CENTER);
    private final JButton findButton = new JButton("Find");
    private final JButton refreshButton = new JButton("Refresh");

    //function component
    private final JButton outOfStockButton = new JButton("Out of stock");
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

    // sales invoice detail component
    private final JFrame salesInvoiceDetailFrame = new JFrame("Detail");
    private final JButton getSalesInvoiceDetailButton = new JButton("Detail");
    private final JButton closeSaleInvoiceDetailFrameButton = new JButton("Close");

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
        loginButton.setFocusable(true);

        userTextField = new JTextField("AccountTest");
        userTextField.setFocusable(false);
        userPasswordField = new JPasswordField("AccountTest");
        userPasswordField.setFocusable(false);

        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 0, 5));
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();

        centerPanel.add(welcomeLabel);
        centerPanel.add(new JLabel("Username: "));
        centerPanel.add(userTextField);
        centerPanel.add(new JLabel("Password: "));
        centerPanel.add(userPasswordField);
        centerPanel.add(loginButton);

        loginFrame.add(topPanel, BorderLayout.SOUTH);
        loginFrame.add(bottomPanel, BorderLayout.NORTH);
        loginFrame.add(rightPanel, BorderLayout.WEST);
        loginFrame.add(leftPanel, BorderLayout.EAST);
        loginFrame.add(centerPanel, BorderLayout.CENTER);


        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public void createFrame() {
        new JFrame();
        setTitle("Supermarket management");
        setSize(1200, 675);
        setLayout(new FlowLayout());
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/supermarkets.png"))).getImage());

        topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setPreferredSize(new Dimension(getWidth() - PADDING, PADDING));

        midPanel = new JPanel(new FlowLayout());
        midPanel.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));

        bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(getWidth() - PADDING, PADDING));

        findField = new JTextField(10);
        welcomeLabel.setPreferredSize(midPanel.getPreferredSize());
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

        topPanel.add(tableChooser);
        topPanel.add(findField);
        topPanel.add(findButton);
        topPanel.add(refreshButton);
        midPanel.add(welcomeLabel);

        bottomPanel.add(addButton);
        bottomPanel.add(updateButton);
        bottomPanel.add(deleteButton);


        add(topPanel);
        add(midPanel);
        add(bottomPanel);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void setupManagementFrame() {
        if (midPanel.isAncestorOf(welcomeLabel)) {
            midPanel.remove(welcomeLabel);
        }
        if (scrollPane.isAncestorOf(table)) {
            scrollPane.remove(table);
        }
        if (midPanel.isAncestorOf(scrollPane)) {
            midPanel.remove(scrollPane);
        }
        if (topPanel.isAncestorOf(getSalesInvoiceDetailButton)) {
            topPanel.remove(getSalesInvoiceDetailButton);
        }

        if (topPanel.isAncestorOf(outOfStockButton)) {
            topPanel.remove(outOfStockButton);
        }
        if (!findButton.isEnabled()) {
            findButton.setEnabled(true);
            addButton.setEnabled(true);
            updateButton.setEnabled(true);
            deleteButton.setEnabled(true);
            refreshButton.setEnabled(true);
        }
    }

    public void createProductManagementFrame() {
        setupManagementFrame();

        outOfStockButton.setActionCommand("outOfStockButtonClicked");
        topPanel.add(outOfStockButton);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createEmployeeManagementFrame() {
        setupManagementFrame();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createSaleInvoicesManagementFrame() {
        setupManagementFrame();

        getSalesInvoiceDetailButton.setActionCommand("getSalesInvoiceDetailButtonClicked");
        topPanel.add(getSalesInvoiceDetailButton);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createInvoiceProductManagementFrame() {
        setupManagementFrame();

        getSalesInvoiceDetailButton.setActionCommand("getSalesInvoiceDetailButtonClicked");
        topPanel.add(getSalesInvoiceDetailButton);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createCustomerManagementFrame() {
        setupManagementFrame();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createSupplierManagementFrame() {
        setupManagementFrame();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createOrderManagementFrame() {
        setupManagementFrame();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createDeliveryReceiptManagementFrame() {
        setupManagementFrame();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createSupplyRequestManagementFrame() {
        setupManagementFrame();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createProductRequestManagementFrame() {
        setupManagementFrame();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(getWidth() - PADDING, (getHeight() - 200)));
        midPanel.add(scrollPane);

        revalidate();
        repaint();
    }

    public void createInfoFrame(Object[] titles, Object[] data) {
        infoFrame = new JFrame("Information");
        infoFrame.setLayout(new BorderLayout());
        infoFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/info.png"))).getImage());

        int rowCount = titles.length;

        JPanel topInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topInfoPanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        topInfoPanel.add(new JLabel("INFORMATION"));

        JPanel insideMidInfoPanel = new JPanel(new GridBagLayout());
        insideMidInfoPanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.ipadx = PADDING;
        gridBagConstraints.ipady = PADDING / 2;
        for (int i = 0; i < rowCount; i++) {
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy++;
            insideMidInfoPanel.add(new JLabel(String.valueOf(titles[i]), SwingConstants.LEFT), gridBagConstraints);

            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridwidth = 2;
            if (data[i] == null) {
                insideMidInfoPanel.add(new JLabel(), gridBagConstraints);
            } else {
                insideMidInfoPanel.add(new JLabel(data[i].toString()), gridBagConstraints);
            }
        }

        JPanel bottomInfoPanel = new JPanel();
        bottomInfoPanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));

        closeInfoFrameButton.setActionCommand("closeInfoFrameButtonClicked");

        bottomInfoPanel.add(closeInfoFrameButton);

        infoFrame.add(topInfoPanel, BorderLayout.NORTH);
        infoFrame.add(insideMidInfoPanel, BorderLayout.CENTER);
        infoFrame.add(bottomInfoPanel, BorderLayout.SOUTH);

        infoFrame.pack();
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setVisible(true);
    }

    public void createInfoFrameWithMultipleRows(Object[] titles, Object[][] data) {
        infoFrame = new JFrame("Information");
        infoFrame.setLayout(new BorderLayout());
        infoFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/info.png"))).getImage());

        JPanel topInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topInfoPanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
        topInfoPanel.add(new JLabel("INFORMATION " + tableChooser.getSelectedItem().toString().toUpperCase() + ": " + data[0][0]));

        JPanel midInfoPanel = new JPanel(new GridBagLayout());
        midInfoPanel.setBorder(BorderFactory.createEmptyBorder(NO_BORDER, BORDER, NO_BORDER, BORDER));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.ipadx = PADDING;
        gridBagConstraints.ipady = PADDING / 2;

        for (int i = 1; i < titles.length; i++) {
            gridBagConstraints.gridx = i - 1;
            gridBagConstraints.gridy = 0;
            midInfoPanel.add(new JLabel(String.valueOf(titles[i])), gridBagConstraints);
        }
        for (Object[] datum : data) {
            gridBagConstraints.gridy++;
            for (int j = 1; j < titles.length; j++) {
                gridBagConstraints.gridx = j - 1;
                if (datum[j] == null) {
                    midInfoPanel.add(new JLabel(), gridBagConstraints);
                } else {
                    midInfoPanel.add(new JLabel(String.valueOf(datum[j])), gridBagConstraints);
                }
            }
        }

        JPanel bottomInfoPanel = new JPanel();
        bottomInfoPanel.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));


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
            int quantityInStock = (int) model.getValueAt(row, 3);
            if (quantityInStock < 10) {
                Object[] rowData = new Object[model.getColumnCount()];
                for (int col = 0; col < model.getColumnCount(); col++) {
                    rowData[col] = model.getValueAt(row, col);
                }
                filteredModel.addRow(rowData);
            }
        }

        table.setModel(filteredModel);
        scrollPane.setViewportView(table);
        if (midPanel.isAncestorOf(scrollPane)) {
            midPanel.remove(scrollPane);
        }

        midPanel.add(scrollPane);
        midPanel.revalidate();
        midPanel.repaint();
    }


    public void createSalesInvoiceDetailFrame(SalesInvoiceDetail salesInvoiceDetail) {
        JFrame salesInvoiceDetailFrame = new JFrame("Sales Invoice Detail");
        salesInvoiceDetailFrame.setLayout(new BorderLayout());
        salesInvoiceDetailFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/info.png"))).getImage());

        JPanel topSIDPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        topSIDPanel.setBorder(BorderFactory.createEmptyBorder(PADDING / 2, PADDING / 2, PADDING / 2, PADDING / 2));

        topSIDPanel.add(new JLabel("INVOICE " + salesInvoiceDetail.getSalesInvoice().getInvoiceID(), SwingConstants.CENTER));
        topSIDPanel.add(new JLabel("Customer: " + salesInvoiceDetail.getCustomer().getCustomerName(), SwingConstants.LEFT));
        JPanel insideTopSIDPanel = new JPanel(new GridLayout(1, 2));
        insideTopSIDPanel.add(new JLabel("Date: " + salesInvoiceDetail.getSalesInvoice().getInvoiceDate(), SwingConstants.LEFT));
        insideTopSIDPanel.add(new JLabel("Payment method: " + salesInvoiceDetail.getSalesInvoice().getPaymentMethod(), SwingConstants.RIGHT));
        topSIDPanel.add(insideTopSIDPanel);

        int rowDataCount = salesInvoiceDetail.getProductList().size();
        JPanel detailPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.gridwidth = 2;
        detailPanel.add(new JLabel("Product Name", SwingConstants.LEFT), gridBagConstraints);

        gridBagConstraints.gridwidth = 1;

        gridBagConstraints.gridx = 2;
        detailPanel.add(new JLabel("Price", SwingConstants.RIGHT), gridBagConstraints);

        gridBagConstraints.gridx = 3;
        detailPanel.add(new JLabel("Quantity", SwingConstants.RIGHT), gridBagConstraints);

        gridBagConstraints.gridx = 4;
        detailPanel.add(new JLabel("Amount", SwingConstants.RIGHT), gridBagConstraints);

        for (int i = 0; i < rowDataCount; i++) {
            Product product = salesInvoiceDetail.getProductList().get(i);
            InvoiceProduct invoiceProduct = salesInvoiceDetail.getInvoiceProductList().get(i);
            int amount = product.getRetailPrice() * invoiceProduct.getQuantity();

            gridBagConstraints.gridy++;
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridwidth = 2;
            detailPanel.add(new JLabel(product.getProductName(), SwingConstants.LEFT), gridBagConstraints);

            gridBagConstraints.gridwidth = 1;

            gridBagConstraints.gridx = 2;
            detailPanel.add(new JLabel(String.valueOf(product.getRetailPrice()), SwingConstants.RIGHT), gridBagConstraints);

            gridBagConstraints.gridx = 3;
            detailPanel.add(new JLabel(String.valueOf(invoiceProduct.getQuantity()), SwingConstants.RIGHT), gridBagConstraints);

            gridBagConstraints.gridx = 4;
            detailPanel.add(new JLabel(String.valueOf(amount), SwingConstants.RIGHT), gridBagConstraints);
        }

        gridBagConstraints.gridy += 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        detailPanel.add(new JLabel("TOTAL AMOUNT: " + salesInvoiceDetail.getTotalAmount(), SwingConstants.RIGHT), gridBagConstraints);

        gridBagConstraints.gridy++;
        detailPanel.add(new JLabel("Loyalty points are added: " + Customer.getIncreaseLoyaltyPoints(salesInvoiceDetail.getTotalAmount()), SwingConstants.RIGHT), gridBagConstraints);

        JScrollPane SIDScrollPane = new JScrollPane(detailPanel);
        SIDScrollPane.setBorder(BorderFactory.createEmptyBorder());
        SIDScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        SIDScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel midSIDPanel = new JPanel(new GridBagLayout());
        midSIDPanel.setBorder(BorderFactory.createEmptyBorder(0, PADDING / 2, 0, PADDING / 2));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        midSIDPanel.add(SIDScrollPane, gridBagConstraints);

        JPanel bottomSIDPanel = new JPanel();
        bottomSIDPanel.setBorder(BorderFactory.createEmptyBorder(PADDING / 3, PADDING / 2, PADDING / 3, PADDING / 2));

        //TODO: BUTTON DOESN'T WORK
        closeSaleInvoiceDetailFrameButton.setActionCommand("closeSaleInvoiceDetailFrameButtonClicked");
        bottomSIDPanel.add(closeSaleInvoiceDetailFrameButton);

        salesInvoiceDetailFrame.add(topSIDPanel, BorderLayout.NORTH);
        salesInvoiceDetailFrame.add(midSIDPanel, BorderLayout.CENTER);
        salesInvoiceDetailFrame.add(bottomSIDPanel, BorderLayout.SOUTH);

        salesInvoiceDetailFrame.pack();
        salesInvoiceDetailFrame.setLocationRelativeTo(null);
        salesInvoiceDetailFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        salesInvoiceDetailFrame.setVisible(true);
    }


    public void createAddFrame() {
        addFrame = new JFrame("Add");
        addFrame.setSize(new Dimension(400, 400));
        addFrame.setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/resource/add.png"))).getImage());

        int rowCount = table.getColumnCount();
        addFrame.setLayout(new FlowLayout());
        JPanel topAddPanel = new JPanel();
        topAddPanel.setPreferredSize(new Dimension(addFrame.getWidth(), PADDING));
        topAddPanel.add(new JLabel("ADD", SwingConstants.CENTER));

        JPanel midAddPanel = new JPanel(new GridLayout(rowCount, 2));

        addInAddFrameButton.setActionCommand("addInAddFrameButtonClicked");
        closeAddFrameButton.setActionCommand("closeAddFrameButtonClicked");

        JPanel bottomAddPanel = new JPanel();
        bottomAddPanel.setPreferredSize(new Dimension(addFrame.getWidth(), PADDING));
        bottomAddPanel.add(addInAddFrameButton);
        bottomAddPanel.add(closeAddFrameButton);

        newValuesField = new JTextField[rowCount];
        for (int i = 0; i < rowCount; i++) {
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
        topUpdatePanel.add(new JLabel("UPDATE", SwingConstants.CENTER));
        updateFrame.add(topUpdatePanel, BorderLayout.NORTH);

        JPanel midUpdatePanel = new JPanel(new GridLayout(rowCount, 2));
        String selectedTable = Objects.requireNonNull(tableChooser.getSelectedItem()).toString();
        Object[] oldValue;

        String ID = JOptionPane.showInputDialog("Enter " + selectedTable.toLowerCase() + " ID you want to update");
        oldValue = new SearchProcessManager().processSearch(ID, selectedTable, (DefaultTableModel) table.getModel(), updateFrame);
        if(oldValue == null) return;
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

        String ID = JOptionPane.showInputDialog("Enter " + selectedTable.toLowerCase() + " ID you want to delete");
        oldValue = new SearchProcessManager().processSearch(ID, selectedTable, (DefaultTableModel) table.getModel(), deleteFrame);

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

    public Class<?> getClassByTableName(String selectedTable) {
        return switch (selectedTable) {
            case "Employee" -> Employee.class;
            case "Product" -> Product.class;
            case "SalesInvoice" -> String.class;
            case "InvoiceProduct" -> InvoiceProduct.class;
            case "Customer" -> Customer.class;
            case "Supplier" -> Supplier.class;
            case "Order" -> Order.class;
            case "DeliveryReceipt" -> DeliveryReceipt.class;
            case "SupplyRequest" -> SupplyRequest.class;
            case "ProductRequest" -> ProductRequest.class;
            default -> null;
        };
    }

    public <T> T createObject(String selectedTable, JTextField[] _properties) {
        Class<?> _class = getClassByTableName(selectedTable);
        if (_class == null) {
            throw new IllegalArgumentException("Invalid table name: " + selectedTable);
        }
        try {
            Constructor<?> constructor = switch (selectedTable) {
                case "Product" ->
                        _class.getConstructor(String.class, String.class, String.class, int.class, int.class, String.class, String.class);
                case "Employee" ->
                        _class.getConstructor(String.class, String.class, String.class, String.class, String.class, Date.class, String.class, String.class, String.class, int.class);
                case "SalesInvoice" -> _class.getConstructor(String.class, Date.class, String.class, String.class);
                case "SupplyRequest" ->
                        _class.getConstructor(String.class, Date.class, String.class, Date.class, String.class, String.class);
                case "DeliveryReceipt" ->
                        _class.getConstructor(String.class, Date.class, String.class, String.class, String.class);
                case "InvoiceProduct" -> _class.getConstructor(String.class, String.class, int.class);
                case "Customer" ->
                        _class.getConstructor(String.class, String.class, Date.class, String.class, String.class, int.class);
                case "Supplier" ->
                        _class.getConstructor(String.class, String.class, String.class, String.class, String.class);
                case "Order" ->
                        _class.getConstructor(String.class, String.class, Date.class, String.class, String.class);
                case "ProductRequest" -> _class.getConstructor(String.class, String.class, int.class, int.class);
                default -> throw new IllegalStateException("Invalid table name: " + selectedTable);
            };
            String[] properties = new String[_properties.length];
            for (int i = 0; i < _properties.length; i++) {
                properties[i] = _properties[i].getText();
            }
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            Object[] convertedProperties = convertProperties(properties, parameterTypes);
            return (T) constructor.newInstance(convertedProperties);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                 InvocationTargetException e) {
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

    public static void showMessage(Component cmp, String message) {
        JOptionPane.showMessageDialog(cmp, message);
    }

    public void clearNewsValue() {
        for (JTextField newValue : newValuesField) {
            newValue.setText("");
        }
    }

    public JPasswordField getUserPasswordField() {
        return userPasswordField;
    }

    public JFrame getFrame() {
        return this;
    }


    public JTable getTable() {
        return table;
    }

    public JFrame getLoginFrame() {
        return loginFrame;
    }

    public JButton getFindButton() {
        return findButton;
    }

    public JButton getOutOfStockButton() {
        return outOfStockButton;
    }

    public JComboBox<String> getTableChooser() {
        return tableChooser;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JPanel getMidPanel() {
        return midPanel;
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public JFrame getInfoFrame() {
        return infoFrame;
    }

    public JTextField getFindField() {
        return findField;
    }

    public JButton getCloseInfoFrameButton() {
        return closeInfoFrameButton;
    }

    public String getTableName(int index) {
        return tableNames[index];
    }

    public Object[] getProductColumnNames() {
        return productColumnNames;
    }

    public Object[] getEmployeeColumnNames() {
        return employeeColumnNames;
    }

    public Object[] getSalesInvoiceColumnNames() {
        return salesInvoiceColumnNames;
    }

    public Object[] getInvoiceProductColumnNames() {
        return invoiceProductColumnNames;
    }

    public Object[] getCustomerColumnNames() {
        return customerColumnNames;
    }

    public Object[] getSupplierColumnNames() {
        return supplierColumnNames;
    }

    public Object[] getProductRequestColumnNames() {
        return productRequestColumnNames;
    }

    public Object[] getColumnNames(String selectedTable) {
        return switch (selectedTable) {
            case "Employee" -> getEmployeeColumnNames();
            case "Product" -> getProductColumnNames();
            case "SalesInvoice" -> getSalesInvoiceColumnNames();
            case "InvoiceProduct" -> getInvoiceProductColumnNames();
            case "Customer" -> getCustomerColumnNames();
            case "Supplier" -> getSupplierColumnNames();
            case "Order" -> getOrderColumnNames();
            case "DeliveryReceipt" -> getDeliveryReceiptColumnNames();
            case "SupplyRequest" -> getSupplyRequestColumnNames();
            case "ProductRequest" -> getProductRequestColumnNames();
            default -> new Object[0];
        };
    }

    public JTextField[] getNewFieldValues() {
        return newValuesField;
    }

    public JFrame getAddFrame() {
        return addFrame;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getAddInAddFrameButton() {
        return addInAddFrameButton;
    }

    public JButton getCloseAddFrameButton() {
        return closeAddFrameButton;
    }

    public JFrame getUpdateFrame() {
        return updateFrame;
    }

    public JFrame getDeleteFrame() {
        return deleteFrame;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JButton getDeleteInDeleteFrameButton() {
        return deleteInDeleteFrameButton;
    }

    public JButton getCloseDeleteFrameButton() {
        return closeDeleteFrameButton;
    }

    public JButton getCloseUpdateFrameButton() {
        return closeUpdateFrameButton;
    }

    public JButton getUpdateInUpdateFrameButton() {
        return updateInUpdateFrameButton;
    }

    public Object[] getOrderColumnNames() {
        return orderColumnNames;
    }

    public Object[] getDeliveryReceiptColumnNames() {
        return deliveryReceiptColumnNames;
    }

    public Object[] getSupplyRequestColumnNames() {
        return supplyRequestColumnNames;
    }

    public JFrame getSalesInvoiceDetailFrame() {
        return salesInvoiceDetailFrame;
    }

    public JButton getGetSalesInvoiceDetailButton() {
        return getSalesInvoiceDetailButton;
    }

    public JButton getCloseSaleInvoiceDetailFrameButton() {
        return closeSaleInvoiceDetailFrameButton;
    }
}