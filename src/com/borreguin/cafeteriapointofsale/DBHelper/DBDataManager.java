package com.borreguin.cafeteriapointofsale.DBHelper;

import com.borreguin.cafeteriapointofsale.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    
    public void insertProduct(Product product) {
        String sql = "INSERT INTO "+ ProductTable.PRODUCT +"("+
                ProductTable.COLUMN_ID +","+
                ProductTable.COLUMN_NAME+") VALUES(?,?)";

        try (PreparedStatement pstmt = c.prepareStatement(sql)) {
            pstmt.setLong(1, product.getProductId());
            pstmt.setString(2, product.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public Product getProductById(long id){
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+ ProductTable.PRODUCT + " WHERE id=" + id);
            if(rs.next())
            {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private List<Product> getAllProducts(){

        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+ ProductTable.PRODUCT);
            List<Product> products = new ArrayList<>();
            while(rs.next())
            {
                Product product = extractUserFromResultSet(rs);
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private Product extractUserFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product(
                rs.getLong(ProductTable.COLUMN_ID),
                rs.getString(ProductTable.COLUMN_NAME),
                rs.getInt(ProductTable.COLUMN_PRICE),
                rs.getInt(ProductTable.COLUMN_TYPE));
        return product;
    }

}
