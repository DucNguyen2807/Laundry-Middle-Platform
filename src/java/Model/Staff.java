/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author khait
 */
public class Staff implements Serializable{
    private int staffID;
    private String username;
    private String password;
    private String address;
    private String fullname;
    private String phone;
    private String email;
    private String statusDetail;

    public Staff() {
    }

    public Staff(int staffID, String username, String password, String address, String fullname, String phone, String email, String statusDetail) {
        this.staffID = staffID;
        this.username = username;
        this.password = password;
        this.address = address;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.statusDetail = statusDetail;
    }

    public int getstaffID() {
        return staffID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setstaffID(int staffID) {
        this.staffID = staffID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

  
    
    
}
