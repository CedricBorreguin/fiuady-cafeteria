package com.borreguin.cafeteriapointofsale.DBHelper;

public class ProductTable {
    public static final String PRODUCT = "product";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_PRICE = "price";

    public static final String[] ALL_PRODUCTS =
            {COLUMN_ID, COLUMN_NAME, COLUMN_TYPE, COLUMN_PRICE};

    public static final String SQL_CREATE =
            "CREATE TABLE " + PRODUCT + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_TYPE + " INTEGER" +
                    COLUMN_PRICE + " INTEGER" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + PRODUCT;
}
