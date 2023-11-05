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
    private String imageDetail;
    private String address;
    double averageRating;
    private String price;
    private String serviceDetail;
    private String serviceID;
    
    public Review(String reviewText, int rating, String customerName, String storeID) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.customerName = customerName;
        this.storeID = storeID;
    }

    public Review(String storeID, String price, String serviceDetail, String serviceID) {
        this.storeID = storeID;
        this.price = price;
        this.serviceDetail = serviceDetail;
        this.serviceID = serviceID;
    }

    public Review(String storeID, String storeName, String imageDetail, String address, double averageRating) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.imageDetail = imageDetail;
        this.address = address;
        this.averageRating = averageRating;
    }
public Review(String storeID, String storeName, String address, double averageRating) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.address = address;
        this.averageRating = averageRating;
    }
    
    
    public Review(String imageDetail) {
       
        this.imageDetail = imageDetail;
        
    }
    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getServiceDetail() {
        return serviceDetail;
    }

    public void setServiceDetail(String serviceDetail) {
        this.serviceDetail = serviceDetail;
    }
    
    
    public String getstoreName() {
        return storeName;
    }
    
    public void setFullname(String storeName) {
        this.storeName = storeName;
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
