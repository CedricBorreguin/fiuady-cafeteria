package com.borreguin.cafeteriapointofsale.models;

public class Expense {

    private long expenseId;
    private long paymentId;
    private String comment;
    private int payInt;
    private int payCents;
    private int dayOfWeek;
    private int date;
    private int time;

    public Expense(long expenseId, long paymentId, String comment, int payInt, int payCents, int dayOfWeek, int day, int month, int year, int time) {
        this.expenseId = expenseId;
        this.paymentId = paymentId;
        this.comment = comment;
        this.payInt = payInt;
        this.payCents = payCents;
        this.dayOfWeek = dayOfWeek;
        this.date = year*10000 + month*100 + day;
        this.time = time;
    }

    public long getExpenseId() {
        return expenseId;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public String getComment() {
        return comment;
    }

    public int getPayInt() {
        return payInt;
    }

    public int getPayCents() {
        return payCents;
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
