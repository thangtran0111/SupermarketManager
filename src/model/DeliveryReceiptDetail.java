package model;

public class DeliveryReceiptDetail {
    private DeliveryReceipt deliveryReceipt;
    private Order order;
    private Employee employee;
    private SalesInvoiceDetail salesInvoiceDetail;

    public DeliveryReceiptDetail(DeliveryReceipt deliveryReceipt, Order order, Employee employee, SalesInvoiceDetail salesInvoiceDetail) {
        this.deliveryReceipt = deliveryReceipt;
        this.order = order;
        this.employee = employee;
        this.salesInvoiceDetail = salesInvoiceDetail;
    }

    public DeliveryReceiptDetail(){
        deliveryReceipt = new DeliveryReceipt();
        order = new Order();
        employee = new Employee();
        salesInvoiceDetail = new SalesInvoiceDetail();
    }

    public DeliveryReceipt getDeliveryReceipt() {
        return deliveryReceipt;
    }

    public void setDeliveryReceipt(DeliveryReceipt deliveryReceipt) {
        this.deliveryReceipt = deliveryReceipt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public SalesInvoiceDetail getSalesInvoiceDetail() {
        return salesInvoiceDetail;
    }

    public void setSalesInvoiceDetail(SalesInvoiceDetail salesInvoiceDetail) {
        this.salesInvoiceDetail = salesInvoiceDetail;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
