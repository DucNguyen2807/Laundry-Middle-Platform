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
public class Customer implements Serializable{
    private int customerID;
    private String username;
    private String password;
    private String address;
    private String fullname;
    private String phone;
    private String email;

    public Customer() {
    }

    public Customer(int customerID, String username, String password, String address, String fullname, String phone, String email) {
        this.customerID = customerID;
        this.username = username;
        this.password = password;
        this.address = address;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
    }

    public int getCustomerID() {
        return customerID;
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

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
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

    

}
