package com.borreguin.cafeteriapointofsale.DBHelper;

public class OrdersTable {
    public static final String ORDER = "order_tbl";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_BUYER = "buyer";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_STATUS= "status";
    public static final String COLUMN_QUANTITY = "quantity";

    public static final String[] ALL_PRODUCTS =
            {COLUMN_ID, COLUMN_PRODUCT_ID, COLUMN_BUYER, COLUMN_NOTES, COLUMN_STATUS, COLUMN_QUANTITY};

    public static final String SQL_CREATE =
            "CREATE TABLE " + ORDER + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_PRODUCT_ID + " TEXT," +
                    COLUMN_BUYER + " TEXT," +
                    COLUMN_NOTES + " TEXT," +
                    COLUMN_STATUS + " INTEGER," +
                    COLUMN_QUANTITY + " INTEGER" + "," +
                    "FOREIGN KEY(" + COLUMN_PRODUCT_ID + ") REFERENCES " + ProductTable.PRODUCT + "("+ ProductTable.COLUMN_ID +  "));";

    public static final String SQL_DELETE =
            "DROP TABLE " + ORDER;
}
