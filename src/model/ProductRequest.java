package model;

public class ProductRequest {
    private String supplyRequestID;
    private String productID;
    private int quantityReceived;
    private int unitPrice;

    public ProductRequest(String supplyRequestID,String productID, int quantityReceived, int unitPrice){
        this.supplyRequestID = supplyRequestID;
        this.productID = productID;
        this.quantityReceived = quantityReceived;
        this.unitPrice = unitPrice;
    }

    public ProductRequest(){

    }
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSupplyRequestID() {
        return supplyRequestID;
    }

    public void setSupplyRequestID(String supplyRequestID) {
        this.supplyRequestID = supplyRequestID;
    }

    public int getQuantityReceived() {
        return quantityReceived;
    }

    public void setQuantityReceived(int quantityReceived) {
        this.quantityReceived = quantityReceived;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
