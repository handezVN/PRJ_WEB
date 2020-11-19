package DAO;

import java.util.List;
import DTO.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import utils.MyConnection;

public class ProductDAO {

    private List<Product> products;

    public ProductDAO() {
        try {

            this.products = getAllProducts();
        } catch (Exception e) {
            e.printStackTrace();;
        }
    }

    public List<Product> findAll() {
        return this.products;
    }

    public Product find(String id) {
        for (Product product : this.products) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> getAllProducts() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Product";

        ArrayList<Product> lst = new ArrayList<>();

        try {
            con = MyConnection.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String id = rs.getString("ProductId");
                    String name = rs.getString("ProductName");
                    double price = rs.getDouble("ProductPrice");
                    String imgURL = rs.getString("ProductURL");

                    Product p = new Product(id, name, imgURL, price); //DTO

                    lst.add(p);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return lst;
    }
    
    public static int updateProduct(String name , Double price , String photo , String id) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="update Product set ProductName=? , ProductPrice=?, ProductURL=? "
                    + "where ProductId=? ";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setDouble(2, price);
            pst.setString(3, photo);
            pst.setString(4, id);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
     public static Product ProductByID(String id) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        Product p= null;
        if(cn!=null){
            String sql="select * from Product where ProductId=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                 p = new Product(id, rs.getString(2), rs.getString(4),rs.getDouble(3));
            }
            cn.close();
        }
        return p;
    }
     public static int DeleteProduct(String id) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="Delete Product where ProductId=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1,id);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
     public static int ADDProduct(String id,String name, Double price , String photo) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="insert Product values(?,?,?,?) ";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1,id);
            pst.setString(2,name);
            pst.setDouble(3,price);
            pst.setString(4,photo);
            
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static boolean checkIDProduct(String id) throws SQLException{
        Connection cn = MyConnection.makeConnection();
        int result =0 ;
        if(cn!=null){
            String sql = "Select * from Product where ProductId=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
        }
        cn.close();
        return false;
    } 
}
