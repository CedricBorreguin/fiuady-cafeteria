package com.borreguin.cafeteriapointofsale;

import java.sql.*;

public class DBConnection {

   // private Connection c = null;
    //private Statement stmt = null;

    public DBConnection() {

        try {
           // Class.forName("org.sqlite.JDBC");
            //c = DriverManager.getConnection("jdbc:sqlite:cafeteriaFIUADY.db");

            //stmt = c.createStatement();

            /*
            String sql = "CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " AGE            INT     NOT NULL, " +
                    " ADDRESS        CHAR(50), " +
                    " SALARY         REAL)";

            stmt.executeUpdate(sql);
            */

            //stmt.close();
            //c.close();

        } catch ( Exception e ) {
          //  System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           // System.exit(0);
        }
        System.out.println("Opened database successfully");
    }


}
