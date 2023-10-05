/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author khait
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String Address;
    private String fullname;
    private int phone;
    private String email;
    private Date birthday;
    private char gender;
    private int roleId;
    private int statusId;

    public User() {
    }

    public User(int userId, String username, String password, int roleId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public User(String Address, String fullname, int phone, String email, Date birthday, char gender, int statusId) {
        this.Address = Address;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.statusId = statusId;
    }

    public User(int userId, String username, String password, String Address, String fullname, int phone, String email, Date birthday, char gender, int roleId, int statusId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.Address = Address;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
        this.roleId = roleId;
        this.statusId = statusId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return Address;
    }

    public String getFullname() {
        return fullname;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public char getGender() {
        return gender;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
    
       
}
