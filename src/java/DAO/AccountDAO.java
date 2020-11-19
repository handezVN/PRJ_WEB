/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Account;
import DTO.DetailHistory;
import DTO.HistoryOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utils.MyConnection;

/**
 *
 * @author DELL
 */
public class AccountDAO {
    public static Account getAccount(String u, String p) throws SQLException{
        Account a=null;
        Connection cn = MyConnection.makeConnection();
        if(cn!=null){
            String sql="select *\n" +
                        "from UserTbl\n" +
                        "where ID=? and Pass=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, u);
            pst.setString(2, p);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                a = new Account(rs.getString(1), rs.getString(2), rs.getString(3), 
                        rs.getBoolean(5),rs.getString(4),rs.getBoolean(6));
            }
            cn.close();
        }
        return a;
    }
    public static ArrayList<Account> getAllAccount() throws SQLException{
        ArrayList<Account> list = new ArrayList<>();
        Connection cn = MyConnection.makeConnection();
        if(cn!=null){
            String sql="select *\n" +
                        "from UserTbl\n"
                    ;
                        
            PreparedStatement pst = cn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3), 
                        rs.getBoolean(5),rs.getString(4),rs.getBoolean(6)));
            }
            cn.close();
        }
        return list;
    }
    public static int createAccount(Account a) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="insert UserTbl\n" +
                       "values(?,?,?,?,?,?)";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, a.getUsername());
            pst.setString(2, a.getPassword());
            pst.setString(3, a.getFullname());
            pst.setBoolean(5, a.isIsAdmin());
            pst.setString(4, a.getNumber());
            pst.setBoolean(6,a.isIsBan());
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int payment(String idOrder,String User, String Date,double total) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="insert OrderTbl\n" +
                       "values(?,?,(Select SYSDATETIME() as date),?)";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, User);
            pst.setString(2, idOrder);
            
             pst.setDouble(3, total);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int Detailpayment(String idOrder,String Productid, double Price,int quantity, String img ) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="insert DetailOrder\n" +
                       "values(?,?,?,?,?)";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setString(1, idOrder);
            pst.setString(2, Productid);
            pst.setDouble(3, Price);
             pst.setInt(4, quantity);
             pst.setString(5, img);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static ArrayList<HistoryOrder> getHistory(String u) throws SQLException{
        ArrayList<HistoryOrder> list = new ArrayList();
        Connection cn = MyConnection.makeConnection();
        if(cn!=null){
            String sql="Select Date,ProductId,price,quantity,Photo,o.OrderID from OrderTbl o , DetailOrder  d \n" +
"            where o.OrderID =d.OrderID and o.ID= ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, u);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add( new HistoryOrder(rs.getString(6),rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(1),rs.getString(5)));
            }
            cn.close();
        }
        return list;
    }
    public static ArrayList<DetailHistory> getDetailHistory(String u) throws SQLException{
        ArrayList<DetailHistory> list = new ArrayList();
        Connection cn = MyConnection.makeConnection();
        if(cn!=null){
            String sql="Select * from DetailOrder where OrderID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, u);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add(new DetailHistory(rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5)));
            }
            cn.close();
        }
        return list;
    }
    public static ArrayList<DTO.AdmimHistory> getAllHistory() throws SQLException{
        ArrayList<DTO.AdmimHistory> list = new ArrayList();
        Connection cn = MyConnection.makeConnection();
        if(cn!=null){
            String sql="Select * from OrderTbl";
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add( new DTO.AdmimHistory(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
            cn.close();
        }
        return list;
    }
    public static ArrayList<DTO.AdmimHistory> SearchHistory(String date) throws SQLException{
        ArrayList<DTO.AdmimHistory> list = new ArrayList();
        Connection cn = MyConnection.makeConnection();
        if(cn!=null){
            String sql="Select * from OrderTbl where Date between ? and (select SYSDATETIME() as date)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, date);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                list.add( new DTO.AdmimHistory(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
            cn.close();
        }
        return list;
    }
    public static Account getAccountByUn(String u) throws SQLException{
        Account a=null;
        Connection cn=MyConnection.makeConnection();
        if(cn!=null){
           String sql="select *\n" +
                        "from UserTbl\n" +
                        "where ID=?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, u);
            
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                a = new Account(rs.getString(1), rs.getString(2), rs.getString(3), 
                        rs.getBoolean(5), rs.getString(4),rs.getBoolean(6));
            }
            cn.close();
        }
        return a; 
        
    }
    public static int updateAccountAdmin(String username, int a) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="update UserTbl set isAdmin=? where ID=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setInt(1,a);
            pst.setString(2,username);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
    public static int updateAccountBan(String username, int a) throws SQLException{
        Connection cn=MyConnection.makeConnection();
        int result=0;
        if(cn!=null){
            String sql="update UserTbl set isBan=? where ID=?";
            PreparedStatement pst=cn.prepareStatement(sql);
            pst.setInt(1,a);
            pst.setString(2,username);
            result=pst.executeUpdate();
            cn.close();
        }
        return result;
    }
}
