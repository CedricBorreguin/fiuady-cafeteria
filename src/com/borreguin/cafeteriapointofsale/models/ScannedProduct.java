package com.borreguin.cafeteriapointofsale.models;

public class ScannedProduct {

    private long productId;
    private int quantity;

    public ScannedProduct(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        this.quantity += 1;
    }}
