package com.borreguin.cafeteriapointofsale.models;

public class Oders {
    private int orderId;
    private int productId;
    private String buyer;
    private String notes;
    private int status;
    private int qty;

    public Oders(int orderId, int productId, String buyer, String notes, int status, int qty) {
        this.orderId = orderId;
        this.productId = productId;
        this.buyer = buyer;
        this.notes = notes;
        this.status = status;
        this.qty = qty;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public String getBuyer() {
        return buyer;
    }

    public String getNotes() {
        return notes;
    }

    public int getStatus() {
        return status;
    }

    public int getQty() {
        return qty;
    }
}
