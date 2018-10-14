package com.borreguin.cafeteriapointofsale.DBHelper;

public class ReciptTable {
    public static final String RECIPT = "recipt";
    public static final String  COLUMN_ID = "id";
    public static final String  COLUMN_TOTAL = "total";
    public static final String  COLUMN_DATE = "date";

    public static final String[] ALL_PRODUCTS =
            {COLUMN_ID, COLUMN_TOTAL, COLUMN_DATE};

    public static final String SQL_CREATE =
            "CREATE TABLE " + RECIPT + "(" +
                    COLUMN_ID + " TEXT PRIMARY KEY," +
                    COLUMN_TOTAL + " TEXT," +
                    COLUMN_DATE + " INTEGER" + ");";

    public static final String SQL_DELETE =
            "DROP TABLE " + RECIPT;

}
