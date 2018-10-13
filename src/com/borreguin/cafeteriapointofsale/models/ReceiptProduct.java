package com.borreguin.cafeteriapointofsale.models;

public class ReceiptProduct {

    private long productId;
    private long receiptId;
    private int quantity;

    public ReceiptProduct(long productId, long receiptId, int quantity) {
        this.productId = productId;
        this.receiptId = receiptId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public long getReceiptId() {
        return receiptId;
    }

    public int getQuantity() {
        return quantity;
    }
}
