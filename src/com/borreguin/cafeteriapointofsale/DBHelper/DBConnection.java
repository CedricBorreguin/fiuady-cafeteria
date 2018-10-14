package com.borreguin.cafeteriapointofsale.DBHelper;

import java.io.File;
import java.sql.*;

public class DBConnection {

    //private Connection c = null;
    //private Statement stmt = null;

    public static final  String dbName = "cafeteriaFIUADY.db";

    public DBConnection() {
    }

    public static Connection getConnection() throws SQLException{

        boolean fileExists;

        File dbFile = new File(dbName);

        fileExists = dbFile.exists();

        Connection c;
        PreparedStatement st;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:"+dbName);

            if(!fileExists){
                st = c.prepareStatement(ProductTable.SQL_CREATE);
                st.executeUpdate();
                st = c.prepareStatement(ReceiptTable.SQL_CREATE);
                st.executeUpdate();
                st = c.prepareStatement(ReceiptProductTable.SQL_CREATE);
                st.executeUpdate();
                st = c.prepareStatement(OrdersTable.SQL_CREATE);
                st.executeUpdate();
            }

            return c;

        } catch ( ClassNotFoundException exc ) {
            exc.printStackTrace();
        }

        System.out.println("Opened database successfully");
        return null;
    }

}
