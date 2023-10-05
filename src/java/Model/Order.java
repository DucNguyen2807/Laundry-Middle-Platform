/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class Order implements Serializable {

    private int OrderID;
    private String ServiceDetail;
    private int Weight;
    private int TotalPrice;
    private String NOTE;
    private String DateApproved;
    private String DateComplete;
    private String TimeComplete;
    private String CustomerName;
    private String StoreName;
    private String StaffName;
    private String stOrderDetail;

    public Order(String orderID, String serviceDetail, String weight, String totalPrice, String note, String dateApprove, String dateComplete, String timeComplete, String customerName, String storeName, String staffName, String stOrderDetail) {
        this.OrderID = Integer.parseInt(orderID);
        this.ServiceDetail = serviceDetail;
        this.Weight = (int) Double.parseDouble(weight);
        this.TotalPrice = (int) Double.parseDouble(totalPrice);
        this.NOTE = note;
        this.DateApproved = dateApprove;
        this.DateComplete = dateComplete;
        this.TimeComplete = timeComplete;
        this.CustomerName = customerName;
        this.StoreName = storeName;
        this.StaffName = staffName;
        this.stOrderDetail = stOrderDetail;
    }

    public Order() {
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getServiceDetail() {
        return ServiceDetail;
    }

    public void setServiceDetail(String ServiceDetail) {
        this.ServiceDetail = ServiceDetail;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int Weight) {
        this.Weight = Weight;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public String getDateApproved() {
        return DateApproved;
    }

    public void setDateApproved(String DateApproved) {
        this.DateApproved = DateApproved;
    }

    public String getDateComplete() {
        return DateComplete;
    }

    public void setDateComplete(String DateComplete) {
        this.DateComplete = DateComplete;
    }

    public String getTimeComplete() {
        return TimeComplete;
    }

    public void setTimeComplete(String TimeComplete) {
        this.TimeComplete = TimeComplete;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }

    public String getStaffName() {
        return StaffName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    public String getStOrderDetail() {
        return stOrderDetail;
    }

    public void setStOrderDetail(String stOrderDetail) {
        this.stOrderDetail = stOrderDetail;
    }

}
