/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author nguye
 */
public class Review {

    private String reviewText;
    private int rating;
    private String customerName;
    private String storeID;
    private String storeName;
    private int giatthuong;
    private int giatnhanh;
    private int giatsieutoc;
    private String imageDetail;
    private String address;
    double averageRating;

    public Review(String reviewText, int rating, String customerName, String storeID) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.customerName = customerName;
        this.storeID = storeID;
    }

    public Review(String storeName, int giatthuong, int giatnhanh, int giatsieutoc, String imageDetail, String address, double averageRating) {
        this.storeName = storeName;
        this.giatthuong = giatthuong;
        this.giatnhanh = giatnhanh;
        this.giatsieutoc = giatsieutoc;
        this.imageDetail = imageDetail;
        this.address = address;
        this.averageRating = averageRating;
    }

    public String getstoreName() {
        return storeName;
    }
    
    public void setFullname(String storeName) {
        this.storeName = storeName;
    }

    public int getGiatthuong() {
        return giatthuong;
    }

    public void setGiatthuong(int giatthuong) {
        this.giatthuong = giatthuong;
    }

    public int getGiatnhanh() {
        return giatnhanh;
    }

    public void setGiatnhanh(int giatnhanh) {
        this.giatnhanh = giatnhanh;
    }

    public int getGiatsieutoc() {
        return giatsieutoc;
    }

    public void setGiatsieutoc(int giatsieutoc) {
        this.giatsieutoc = giatsieutoc;
    }

    public String getImageDetail() {
        return imageDetail;
    }

    public void setImageDetail(String imageDetail) {
        this.imageDetail = imageDetail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
    
    
    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

}
