package model;

public class Product {
    private String productID;
    private String barcode;
    private String productName;
    private int retailPrice;
    private int quantityInStock;
    private String productType;
    private String description;

    // Constructor
    public Product(String productID, String barcode, String productName, int retailPrice, int quantityInStock, String productType, String description) {
        this.productID = productID;
        this.barcode = barcode;
        this.productName = productName;
        this.retailPrice = retailPrice;
        this.quantityInStock = quantityInStock;
        this.productType = productType;
        this.description = description;
    }

    // Getter methods
    public String getProductID() {
        return productID;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getProductName() {
        return productName;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public String getProductType() {
        return productType;
    }

    public String getDescription() {
        return description;
    }


}