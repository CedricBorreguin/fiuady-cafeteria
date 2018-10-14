package com.borreguin.cafeteriapointofsale.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;

public class DBDataManager {

    private Connection c = null;

    public DBDataManager() {
        try {
            this.c = DBConnection.getConnection();
        } catch (SQLException exc){
            exc.printStackTrace();
        }
        if(this.c==null){
            System.exit(1);
        }

    }

    public void close() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
