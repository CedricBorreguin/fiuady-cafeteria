package com.borreguin.cafeteriapointofsale.models;

public class Product {

    private long productId;
    private String name;
    private int price;
    private int type;

    public Product(long productId, String name, int priceInt, int priceCents, int type) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getType() {
        return type;
    }
}
