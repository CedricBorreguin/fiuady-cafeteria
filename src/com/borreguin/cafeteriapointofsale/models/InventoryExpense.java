package com.borreguin.cafeteriapointofsale.models;

public class InventoryExpense {

    private long inventoryExpenseId;
    private long productId;
    private int quantity;
    private int unitCostInt;
    private int unitCostCents;
    private int dayOfWeek;
    private int date;
    private int time;

    public InventoryExpense(long inventoryExpenseId, long productId, int quantity, int unitCostInt, int unitCostCents, int dayOfWeek, int day, int month, int year, int time) {
        this.inventoryExpenseId = inventoryExpenseId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitCostInt = unitCostInt;
        this.unitCostCents = unitCostCents;
        this.dayOfWeek = dayOfWeek;
        this.date = year*10000 + month*100 + day;
        this.time = time;
    }

    public long getInventoryExpenseId() {
        return inventoryExpenseId;
    }

    public long getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitCostInt() {
        return unitCostInt;
    }

    public int getUnitCostCents() {
        return unitCostCents;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getDate() {
        return date;
    }

    public int getDay(){
        return date%100;
    }

    public int getMonth(){
        return (date/100)%100;
    }

    public int getYear(){
        return date/10000;
    }

    public int getTime() {
        return time;
    }
}
