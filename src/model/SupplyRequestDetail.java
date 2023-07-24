package model;

import java.util.ArrayList;
import java.util.List;

public class SupplyRequestDetail {
    private SupplyRequest supplyRequest;
    private List<ProductRequest> productRequestList;
    private List<Product> productList;
    private Supplier supplier;
    private Employee employee;
    private int total;

    public SupplyRequestDetail(SupplyRequest supplyRequest, List<ProductRequest> productRequestList, List<Product> productList, Supplier supplier, Employee employee, int total){
        this.supplyRequest = supplyRequest;
        this.productRequestList = productRequestList;
        this.productList = productList;
        this.supplier = supplier;
        this.employee = employee;
        this.total = total;
    }

    public SupplyRequestDetail(){
        supplyRequest = new SupplyRequest();
        productRequestList = new ArrayList<>();
        productList = new ArrayList<>();
        supplier = new Supplier();
        employee = new Employee();
        total = 0;
    }
    public SupplyRequest getSupplyRequest() {
        return supplyRequest;
    }

    public void setSupplyRequest(SupplyRequest supplyRequest) {
        this.supplyRequest = supplyRequest;
    }

    public List<ProductRequest> getProductRequestList() {
        return productRequestList;
    }

    public void setProductRequestList(List<ProductRequest> productRequestList) {
        this.productRequestList = productRequestList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
