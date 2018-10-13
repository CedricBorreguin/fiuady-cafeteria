package com.borreguin.cafeteriapointofsale.DBHelper;

public class ReceiptProductTable {
    public static final String RECEIPT_PRODUCT = "receipt_product";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT = "product_id";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String[] ALL_COLUMNS =
            {COLUMN_ID, COLUMN_PRODUCT, COLUMN_QUANTITY};

    public static final String SQL_CREATE =
            "CREATE TABLE " + RECEIPT_PRODUCT + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_PRODUCT + " TEXT," +
                    COLUMN_QUANTITY + " INTEGER" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + RECEIPT_PRODUCT;
}
