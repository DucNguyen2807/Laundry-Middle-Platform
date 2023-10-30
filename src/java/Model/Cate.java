/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
public class Cate {
    private int totalPrice;
    private String storeID;
    private String storeName;
    private String address;
    private int price;
    private String service;
    private int rating;
    private String review;
    private String image;
    private String phone;
    private String email;
    private String averagePrice;
    private String customerName;

    public Cate(String address, String storeName, String phone, String email, int price, int rating, String image, String service, String review, String storeID) {
        this.address = address;
        this.storeName = storeName;
        this.phone = phone;
        this.email = email;
        this.price = price;
        this.rating = rating;
        this.image = image;
        this.service = service;
        this.review = review;
        this.storeID = storeID;
    }

    public Cate(String storeID, String storeName, String address, int price, String service, int rating, String review, String image) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.address = address;
        this.price = price;
        this.service = service;
        this.rating = rating;
        this.review = review;
        this.image = image;
    }

    public Cate(String storeID, int rating, String review) {
        this.storeID = storeID;
        this.rating = rating;
        this.review = review;
    }

    public Cate(String storeID, int rating, String review, String customerName) {
        this.storeID = storeID;
        this.rating = rating;
        this.review = review;
        this.customerName = customerName;
    }


    
    
    public Cate() {
    }

    public String getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(String averagePrice) {
        this.averagePrice = averagePrice;
    }
    
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
