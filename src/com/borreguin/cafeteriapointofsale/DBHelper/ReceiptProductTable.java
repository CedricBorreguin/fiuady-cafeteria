package com.borreguin.cafeteriapointofsale.DBHelper;

public class ReceiptProductTable {
    public static final String RECEIPT_PRODUCT = "receipt_product";
    public static final String COLUMN_QUANTITY = "qty";
    public static final String COLUMN_PRODUCT = "product_id";
    public static final String COLUMN_RECIPT = "recipt_id";

    public static final String[] ALL_COLUMNS =
            {COLUMN_PRODUCT, COLUMN_QUANTITY};

    public static final String SQL_CREATE =
            "CREATE TABLE " + RECEIPT_PRODUCT + "(" +
                    COLUMN_QUANTITY + " INTEGER ," +
                    "FOREIGN KEY(" + COLUMN_PRODUCT + ") REFERENCES " + ProductTable.PRODUCT + "("+ ProductTable.COLUMN_ID +  ")," +
                    "FOREIGN KEY(" + COLUMN_RECIPT + ") REFERENCES " + ReciptTable.RECIPT + "("+ ReciptTable.COLUMN_ID +  ")," +
                    "PRIMARY KEY(" + COLUMN_PRODUCT + " , "  + COLUMN_RECIPT + "));" ;

    public static final String SQL_DELETE =
            "DROP TABLE " + RECEIPT_PRODUCT;
}
