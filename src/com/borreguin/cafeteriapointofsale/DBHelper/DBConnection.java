package com.borreguin.cafeteriapointofsale.DBHelper;

import java.sql.*;

public class DBConnection {

    //private Connection c = null;
    //private Statement stmt = null;

    public DBConnection() {

    }

    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:cafeteriaFIUADY.db");

        } catch ( ClassNotFoundException exc ) {
            exc.printStackTrace();
        }
        System.out.println("Opened database successfully");
        return null;
    }

}
