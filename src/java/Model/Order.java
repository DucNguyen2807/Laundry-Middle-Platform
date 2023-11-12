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
    private String Weight;
    private String TotalPrice;
    private String phoneCus;
    private String AddressCus;
    private String AddressSto;
    private String Note;
    private String TimeDesired;
    private String DateDesired;
    private String DateApproved;
    private String DateComplete;
    private String TimeComplete;
    private String CustomerName;
    private String StoreName;
    private String StaffName;
    private String stOrderDetail;
    private int StoreID;

//    public Order(String orderID, String serviceDetail, String weight, String totalPrice, String note, String dateApprove, String dateComplete, String timeComplete, String customerName, String storeName, String staffName, String stOrderDetail) {
//        this.OrderID = Integer.parseInt(orderID);
//        this.ServiceDetail = serviceDetail;
//        this.Weight = (int) Double.parseDouble(weight);
//        this.TotalPrice = (int) Double.parseDouble(totalPrice);
//        this.NOTE = note;
//        this.DateApproved = dateApprove;
//        this.DateComplete = dateComplete;
//        this.TimeComplete = timeComplete;
//        this.CustomerName = customerName;
//        this.StoreName = storeName;
//        this.StaffName = staffName;
//        this.stOrderDetail = stOrderDetail;
//    }

    public Order() {
    }

    public Order(int OrderID, String ServiceDetail, String Weight, String TotalPrice, String phoneCus, String AddressCus, String AddressSto, String Note, String TimeDesired, String DateDesired, String DateApproved, String DateComplete, String TimeComplete, String CustomerName, String StoreName, String StaffName, String stOrderDetail) {
        this.OrderID = OrderID;
        this.ServiceDetail = ServiceDetail;
        this.Weight = Weight;
        this.TotalPrice = TotalPrice;
        this.phoneCus = phoneCus;
        this.AddressCus = AddressCus;
        this.AddressSto = AddressSto;
        this.Note = Note;
        this.TimeDesired = TimeDesired;
        this.DateDesired = DateDesired;
        this.DateApproved = DateApproved;
        this.DateComplete = DateComplete;
        this.TimeComplete = TimeComplete;
        this.CustomerName = CustomerName;
        this.StoreName = StoreName;
        this.StaffName = StaffName;
        this.stOrderDetail = stOrderDetail;
    }

    public Order(int OrderID, String ServiceDetail, String Weight, String TotalPrice, String phoneCus, String AddressCus, String AddressSto, String Note, String TimeDesired, String DateDesired, String DateApproved, String DateComplete, String TimeComplete, String CustomerName, String StoreName, String StaffName, String stOrderDetail, int StoreID) {
        this.OrderID = OrderID;
        this.ServiceDetail = ServiceDetail;
        this.Weight = Weight;
        this.TotalPrice = TotalPrice;
        this.phoneCus = phoneCus;
        this.AddressCus = AddressCus;
        this.AddressSto = AddressSto;
        this.Note = Note;
        this.TimeDesired = TimeDesired;
        this.DateDesired = DateDesired;
        this.DateApproved = DateApproved;
        this.DateComplete = DateComplete;
        this.TimeComplete = TimeComplete;
        this.CustomerName = CustomerName;
        this.StoreName = StoreName;
        this.StaffName = StaffName;
        this.stOrderDetail = stOrderDetail;
        this.StoreID = StoreID;
    }

    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }
    

    public int getOrderID() {
        return OrderID;
    }

    public String getServiceDetail() {
        return ServiceDetail;
    }

    public String getWeight() {
        return Weight;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public String getPhoneCus() {
        return phoneCus;
    }

    public String getAddressCus() {
        return AddressCus;
    }

    public void setDateDesired(String DateDesired) {
        this.DateDesired = DateDesired;
    }

    public String getDateDesired() {
        return DateDesired;
    }

    public String getAddressSto() {
        return AddressSto;
    }

    public String getNote() {
        return Note;
    }

    public String getTimeDesired() {
        return TimeDesired;
    }

    public String getDateApproved() {
        return DateApproved;
    }

    public String getDateComplete() {
        return DateComplete;
    }

    public String getTimeComplete() {
        return TimeComplete;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public String getStaffName() {
        return StaffName;
    }

    public String getStOrderDetail() {
        return stOrderDetail;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public void setServiceDetail(String ServiceDetail) {
        this.ServiceDetail = ServiceDetail;
    }

    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    public void setTotalPrice(String TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public void setPhoneCus(String phoneCus) {
        this.phoneCus = phoneCus;
    }

    public void setAddressCus(String AddressCus) {
        this.AddressCus = AddressCus;
    }

    public void setAddressSto(String AddressSto) {
        this.AddressSto = AddressSto;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public void setTimeDesired(String TimeDesired) {
        this.TimeDesired = TimeDesired;
    }

    public void setDateApproved(String DateApproved) {
        this.DateApproved = DateApproved;
    }

    public void setDateComplete(String DateComplete) {
        this.DateComplete = DateComplete;
    }

    public void setTimeComplete(String TimeComplete) {
        this.TimeComplete = TimeComplete;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }

    public void setStaffName(String StaffName) {
        this.StaffName = StaffName;
    }

    public void setStOrderDetail(String stOrderDetail) {
        this.stOrderDetail = stOrderDetail;
    }

  
}
