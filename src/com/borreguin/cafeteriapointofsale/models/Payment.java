package com.borreguin.cafeteriapointofsale.models;

public class Payment {

    private long paymentId;
    private String name;
    private int costInt;
    private int costCents;

    public Payment(long paymentId, String name, int costInt, int costCents) {
        this.paymentId = paymentId;
        this.name = name;
        this.costInt = costInt;
        this.costCents = costCents;
    }

    public long getPaymentId() {
        return paymentId;
    }

    public String getName() {
        return name;
    }

    public int getCostInt() {
        return costInt;
    }

    public int getCostCents() {
        return costCents;
    }

}
