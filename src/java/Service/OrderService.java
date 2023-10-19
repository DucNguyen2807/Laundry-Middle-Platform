/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.ConnectDB;
import Model.Order;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khait
 */
public class OrderService implements Serializable{
    
    private List<Order> listOrder;

    public List<Order> getListOrder() {
        return listOrder;
    }
    
   private Order createOrderFromResultSet(ResultSet rs) throws SQLException {
        String orderID = String.valueOf(rs.getInt("OrderID"));
        String serviceDetail = rs.getString("ServiceDetail");
        String weight = String.valueOf(rs.getDouble("Weight"));
        String totalPrice = String.valueOf(rs.getDouble("TotaPrice"));
        String phoneCus = String.valueOf(rs.getInt("PhoneCus"));
        String addressCus = rs.getString("AddressCus");
        String addressSto = rs.getString("AddressSto");
        String note = rs.getString("Note");
        String timeDesired = rs.getString("TimeDesired");
        String dateApprove = rs.getDate("DateApprove").toString();
        String dateComplete;
        Date dateCompleteValue = rs.getDate("DateCompleted");
        if (rs.wasNull()) {
            dateComplete = "NULL";
        } else {
            dateComplete = dateCompleteValue.toString();
        }

        String timeComplete;
        Time timeCompleteValue = rs.getTime("TimeComplete");
        if (rs.wasNull()) {
            timeComplete = "NULL";
        } else {
            timeComplete = timeCompleteValue.toString();
        }
        String customerName = rs.getString("CustomerName");
        String storeName = rs.getString("StoreName");
        String staffName;
        String staffNameValue = rs.getString("StaffName");
        if (rs.wasNull()) {
            staffName = "NULL";
        } else {
            staffName = staffNameValue;
        }

        String stOrderDetail = rs.getString("StOrderDetail");

        Order order = new Order(Integer.parseInt(orderID), serviceDetail, weight, totalPrice, phoneCus, addressCus, addressSto, note, 
                timeDesired, dateApprove, dateComplete, timeComplete, customerName, storeName, staffName, stOrderDetail);
        return order;
    }
    
    public void viewNewOrder(int userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listOrder = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String  sql = "SELECT o.OrderID, se.ServiceDetail, o.Weight, o.TotaPrice, o.Note, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                            + " u.Fullname AS CustomerName, uf.Fullname AS StaffName, StOrderDetail\n"
                            + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                            + " LEFT JOIN Service se ON se.ServiceID = o.ServiceID\n"
                            + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
                            + " WHERE  us.UserID = ? AND st.StOrderID = 1";

                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Order order = createOrderFromResultSet(rs);
                    listOrder.add(order);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
//    public void viewNewOrder(int userId) throws ClassNotFoundException, SQLException {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        listOrder = new ArrayList<>();
//
//        try {
//            con = ConnectDB.getConnection();
//
//            if (con != null) {
//                String  sql = "SELECT o.OrderID, se.ServiceDetail, o.Weight, o.TotaPrice, o.Note, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
//                            + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
//                            + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
//                            + " LEFT JOIN Service se ON se.ServiceID = o.ServiceID\n"
//                            + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
//                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
//                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
//                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
//                            + " WHERE  u.Fullname = ? OR us.Fullname = ? OR uf.Fullname = ?";
//
//                ps = con.prepareStatement(sql);
//                ps.setInt(1, userId);
//                rs = ps.executeQuery();
//
//                while (rs.next()) {
//                    Order order = createOrderFromResultSet(rs);
//                    listOrder.add(order);
//                }
//            }
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (ps != null) {
//                ps.close();
//            }
//            if (con != null) {
//                con.close();
//            }
//        }
//    }
}
