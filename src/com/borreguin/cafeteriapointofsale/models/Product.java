package com.borreguin.cafeteriapointofsale.models;

public class Product {

    private long productId;
    private String name;
    private int priceInt;
    private int priceCents;
    private int type;

    public Product(long productId, String name, int priceInt, int priceCents, int type) {
        this.productId = productId;
        this.name = name;
        this.priceInt = priceInt;
        this.priceCents = priceCents;
        this.type = type;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPriceInt() {
        return priceInt;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public int getType() {
        return type;
    }
}
