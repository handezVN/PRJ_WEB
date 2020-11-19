/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class MyConnection implements Serializable{
    public static Connection makeConnection(){
        try {
              Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url="jdbc:sqlserver://localhost:1433;databaseName=ProductChocolate";
            Connection con = DriverManager.getConnection(url,"sa","hoiconcac");
            return con;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
