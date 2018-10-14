package com.borreguin.cafeteriapointofsale.DBHelper;

public class ReceiptTable {
    public static final String RECEIPT = "receipt";
    public static final String  COLUMN_ID = "id";
    public static final String  COLUMN_TOTAL = "total";
    public static final String  COLUMN_DATE = "date";

    public static final String[] ALL_PRODUCTS =
            {COLUMN_ID, COLUMN_TOTAL, COLUMN_DATE};

    public static final String SQL_CREATE =
            "CREATE TABLE " + RECEIPT + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_TOTAL + " TEXT," +
                    COLUMN_DATE + " TEXT" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + RECEIPT;

}
