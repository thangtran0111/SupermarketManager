package controller;

import DAO.imple.DaoHoaDonBanHang;
import DAO.imple.DaoHoaDonMatHang;
import DAO.imple.ProductDAO;
import DAO.imple.EmployeeDAO;
import DAO.itf.IDaoHoaDonBanHang;
import DAO.itf.IDaoHoaDonMatHang;
import DAO.itf.ProductDAOInterface;
import DAO.itf.EmployeeDAOInterface;

import model.HoaDonBanHang;
import model.HoaDonMatHang;
import model.Product;
import model.Employee;
import view.View;

import javax.swing.table.DefaultTableModel;
import java.util.List;

public class TableSelectionProcessManager {
    private ProductDAOInterface productDAO;
    private EmployeeDAOInterface employeeDAO;
    private IDaoHoaDonBanHang daoHoaDonBanHang;
    private IDaoHoaDonMatHang daoHoaDonMatHang;

    public TableSelectionProcessManager(){
        productDAO = new ProductDAO();
        employeeDAO = new EmployeeDAO();
        daoHoaDonBanHang = new DaoHoaDonBanHang();
        daoHoaDonMatHang = new DaoHoaDonMatHang();
    }

    //TODO: thêm các bảng khác các bảng hiện có Employee, Product, Hoá Đơn Bán Hàng, Hoá Đơn Mặt Hàng
    public void processTableSelection(View view) {
        String selectedTable = (String) view.getTableChooser().getSelectedItem();
        DefaultTableModel oldModel = (DefaultTableModel) view.getTable().getModel();
        oldModel.setRowCount(0);
        Object[] columnNames = view.getColumnNames(selectedTable);;

        if (selectedTable.equals(view.getTableName(1))) {
            List<Employee> employeeList = employeeDAO.read();
            view.createEmployeeManagementFrame();
        } else if (selectedTable.equals(view.getTableName(2))) {
            List<Product> productList = productDAO.read();
            view.createProductManagementFrame();
        } else if(selectedTable.equals(view.getTableName(3))){
            List<HoaDonBanHang> hoaDonBanHangList = daoHoaDonBanHang.read();
            view.createHoaDonBanHangManagementFrame();
        } else if(selectedTable.equals(view.getTableName(4))){
            List<HoaDonMatHang> hoaDonBanHangList = daoHoaDonMatHang.read();
            view.createHoaDonMatHangManagementFrame();
        }
        else {
            return;
        }
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        if (selectedTable.equals(view.getTableName(1))) {
            List<Employee> employeeList = employeeDAO.read();
            for (Employee employee : employeeList) {
                model.addRow(new Object[]{String.valueOf(employee.getEmployeeID()), String.valueOf(employee.getEmployeeName()), String.valueOf(employee.getEmployeeID()), String.valueOf(employee.getPhoneNumber()), String.valueOf(employee.getEmail()), employee.getDateOfBirth(), String.valueOf(employee.getGender()), String.valueOf(employee.getAddress()), String.valueOf(employee.getPosition()), employee.getSalary()});
            }
        }else if (selectedTable.equals(view.getTableName(2))) {
                List<Product> productList = productDAO.read();
                for (Product product : productList) {
                    model.addRow(new Object[]{String.valueOf(product.getProductID()),String.valueOf(product.getBarcode()) ,String.valueOf(product.getProductName()), product.getRetailPrice(), product.getQuantityInStock(), String.valueOf(product.getProductType()), String.valueOf(product.getDescription())});
                }
        }else if (selectedTable.equals(view.getTableName(3))) {
            List<HoaDonBanHang> hoaDonBanHangList = daoHoaDonBanHang.read();
            for (HoaDonBanHang hoaDonBanHang : hoaDonBanHangList) {
                model.addRow(new Object[]{String.valueOf(hoaDonBanHang.getMaHoaDon()), hoaDonBanHang.getNgayLap(), String.valueOf(hoaDonBanHang.getMaKhachHang()), String.valueOf(hoaDonBanHang.getPhuongThucThanhToan())});
            }
        }else if (selectedTable.equals(view.getTableName(4))) {
            List<HoaDonMatHang> hoaDonMatHangList = daoHoaDonMatHang.read();
            for (HoaDonMatHang hoaDonMatHang : hoaDonMatHangList) {
                model.addRow(new Object[]{String.valueOf(hoaDonMatHang.getMaHoaDon()), String.valueOf(hoaDonMatHang.getMaMatHang()), hoaDonMatHang.getSoLuong()});
            }
        }

        view.getTable().setModel(model);

    }
}
