package com.borreguin.cafeteriapointofsale.models;

import java.util.Date;

public class Receipt {

    private long receiptId;
    private int total;
    private Date date;

    public Receipt(long receiptId, int total, Date date) {
        this.receiptId = receiptId;
        this.total = total;
        this.date = date;
    }

    public long getReceiptId() {
        return receiptId;
    }

    public int getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }
}
