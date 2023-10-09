/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Order;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

/**
 *
 * @author nguye
 */
public class NewClass {
    private Order createOrderFromResultSet(ResultSet rs) throws SQLException {
    String orderID = String.valueOf(rs.getInt("OrderID"));
                    String serviceDetail = rs.getString("ServiceDetail");
                    String weight = String.valueOf(rs.getDouble("Weight"));
                    String totalPrice = String.valueOf(rs.getDouble("TotaPrice"));
                    String note = rs.getString("Note");
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

                    Order order = new Order(orderID, serviceDetail, weight, totalPrice, note, dateApprove, dateComplete, timeComplete, customerName, storeName, staffName, stOrderDetail);
                    return order;
}

}
