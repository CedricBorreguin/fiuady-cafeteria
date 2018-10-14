package com.borreguin.cafeteriapointofsale.DBHelper;

public class OrdersTable {
    public static final String ORDER = "product";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT = "name";
    public static final String COLUMN_BUYER = "buyer";
    public static final String COLUMN_NOTES = "notes";
    public static final String COLUMN_STATUS= "status";
    public static final String COLUMN_QTY = "qty";

    public static final String[] ALL_PRODUCTS =
            {COLUMN_ID, COLUMN_PRODUCT, COLUMN_BUYER, COLUMN_NOTES, COLUMN_STATUS, COLUMN_QTY};

    public static final String SQL_CREATE =
            "CREATE TABLE " + ORDER + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    "FOREIGN KEY(" + COLUMN_PRODUCT + ") REFERENCES " + ProductTable.PRODUCT + "("+ ProductTable.COLUMN_ID +  ")," +
                    COLUMN_BUYER + " TEXT," +
                    COLUMN_NOTES + " TEXT," +
                    COLUMN_STATUS + " INTEGER," +
                    COLUMN_QTY + " INTEGER" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + ORDER;
}
