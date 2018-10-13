package com.borreguin.cafeteriapointofsale.models;

public class Receipt {

    private long receiptId;
    private String seller;
    private int totalInt;
    private int totalCents;
    private int dayOfWeek;
    private int date;
    private int time;

    public Receipt(long receiptId, String seller, int totalInt, int totalCents, int dayOfWeek, int day, int month, int year, int time) {
        this.receiptId = receiptId;
        this.seller = seller;
        this.totalInt = totalInt;
        this.totalCents = totalCents;
        this.dayOfWeek = dayOfWeek;
        this.date = year*10000 + month*100 + day;
        this.time = time;
    }

    public long getReceiptId() {
        return receiptId;
    }

    public String getSeller() {
        return seller;
    }

    public int getTotalInt() {
        return totalInt;
    }

    public int getTotalCents() {
        return totalCents;
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
