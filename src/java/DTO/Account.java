/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class Account implements Serializable{
    private String username;
    private String password;
    private String fullname;
    private boolean isAdmin;
    private String number;
    private boolean isBan;
    public Account() {
    }

    public Account(String username, String password, String fullname, boolean isAdmin, String number, boolean isBan) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.isAdmin = isAdmin;
        this.number = number;
        this.isBan = isBan;
    }

    public boolean isIsBan() {
        return isBan;
    }

    public void setIsBan(boolean isBan) {
        this.isBan = isBan;
    }

    

   

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    
 
    
}
