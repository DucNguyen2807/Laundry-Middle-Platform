/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.ConnectDB;
import Model.Order;
import Model.Staff;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author khait
 */
public class OrderService implements Serializable {

    private List<Order> listOrder;

    public List<Order> getListOrder() {
        return listOrder;
    }

    private Order createOrderFromResultSet(ResultSet rs) throws SQLException {
        String orderID = String.valueOf(rs.getInt("OrderID"));
        String serviceDetail = rs.getString("ServiceDetail");
        String weight = String.valueOf(rs.getDouble("Weight"));
        String totalPrice = rs.getString("Price");
        String phoneCus = String.valueOf(rs.getInt("PhoneCus"));
        String addressCus = rs.getString("AddressCus");
        String addressSto = rs.getString("AddressSto");
        String note = rs.getString("Note");
        String timeDesired = rs.getString("TimeDesired");
        String dateDesired;
        Date dateDesiredValueDate = rs.getDate("DateDesired");
        if (rs.wasNull()) {
            dateDesired = "NULL";
        } else {
            dateDesired = dateDesiredValueDate.toString();
        }
        String dateApprove;
        Date dateApproveValueDate = rs.getDate("DateApprove");
        if (rs.wasNull()) {
            dateApprove = "NULL";
        } else {
            dateApprove = dateApproveValueDate.toString();
        }
        String dateComplete;
        Date dateCompleteValue = rs.getDate("DateCompleted");
        if (rs.wasNull()) {
            dateComplete = "NULL";
        } else {
            dateComplete = dateCompleteValue.toString();
        }

        String timeComplete;
        String timeCompleteValue = rs.getString("TimeComplete");
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
                timeDesired, dateDesired, dateApprove, dateComplete, timeComplete, customerName, storeName, staffName, stOrderDetail);
        return order;
    }

    public void viewOrder(int userId, String btAction) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listOrder = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight,  FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
                        + " od.AddressCus, od.AddressSto, od.Note,\n"
                        + " o.TimeDesired, o.DateDesired, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                        + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
                        + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                        + " LEFT JOIN [OrderDetail] od ON o.OrderID = od.OrderID\n"
                        + " LEFT JOIN Service se ON se.ServiceID = od.ServiceID\n"
                        + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
                        + " WHERE us.UserID = ? AND o.StOrderID = ? \n"
                        + " ORDER BY o.OrderID DESC ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, Integer.parseInt(btAction));

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

    private Order createOrderFromResultSetCus(ResultSet rs) throws SQLException {
        String orderID = String.valueOf(rs.getInt("OrderID"));
        String storeID = String.valueOf(rs.getInt("StoreID"));
        String serviceDetail = rs.getString("ServiceDetail");
        String weight = String.valueOf(rs.getDouble("Weight"));
        String totalPrice = rs.getString("Price");
        String phoneCus = String.valueOf(rs.getInt("PhoneCus"));
        String addressCus = rs.getString("AddressCus");
        String addressSto = rs.getString("AddressSto");
        String note = rs.getString("Note");
        String timeDesired = rs.getString("TimeDesired");
        String dateDesired;
        Date dateDesiredValueDate = rs.getDate("DateDesired");
        if (rs.wasNull()) {
            dateDesired = "NULL";
        } else {
            dateDesired = dateDesiredValueDate.toString();
        }
        String dateApprove;
        Date dateApproveValueDate = rs.getDate("DateApprove");
        if (rs.wasNull()) {
            dateApprove = "NULL";
        } else {
            dateApprove = dateApproveValueDate.toString();
        }
        String dateComplete;
        Date dateCompleteValue = rs.getDate("DateCompleted");
        if (rs.wasNull()) {
            dateComplete = "NULL";
        } else {
            dateComplete = dateCompleteValue.toString();
        }

        String timeComplete;
        String timeCompleteValue = rs.getString("TimeComplete");
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
                timeDesired, dateDesired, dateApprove, dateComplete, timeComplete, customerName, storeName, staffName, stOrderDetail, Integer.parseInt(storeID));
        return order;
    }
    
    public void viewOrderCustomer(int userId, String btAction) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listOrder = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT o.OrderID, o.StoreID ,se.ServiceDetail, od.Weight,  FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
                        + " od.AddressCus, od.AddressSto, od.Note,\n"
                        + " o.TimeDesired, o.DateDesired, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                        + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
                        + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                        + " LEFT JOIN [OrderDetail] od ON o.OrderID = od.OrderID\n"
                        + " LEFT JOIN Service se ON se.ServiceID = od.ServiceID\n"
                        + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
                        + " WHERE u.UserID = ? AND o.StOrderID = ?  \n"
                        + " ORDER BY o.OrderID DESC";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, Integer.parseInt(btAction));

                rs = ps.executeQuery();
                while (rs.next()) {
                    Order order = createOrderFromResultSetCus(rs);
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

    public void viewTaskOrder(int userId, String btAction) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listOrder = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight,  FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
                        + " od.AddressCus, od.AddressSto, od.Note,\n"
                        + " o.TimeDesired, o.DateDesired, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                        + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
                        + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                        + " LEFT JOIN [OrderDetail] od ON o.OrderID = od.OrderID\n"
                        + " LEFT JOIN Service se ON se.ServiceID = od.ServiceID\n"
                        + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
                        + " WHERE uf.UserID = ? AND o.StOrderID = ? \n"
                        + " ORDER BY o.OrderID DESC";
                ps = con.prepareStatement(sql);
                ps.setInt(1, userId);
                ps.setInt(2, Integer.parseInt(btAction));

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

    public Order getOrderDetail(int orderID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order orderDetail = new Order();
        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight,  FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
                        + " od.AddressCus, od.AddressSto, od.Note,\n"
                        + " o.TimeDesired, o.DateDesired, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                        + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
                        + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                        + " LEFT JOIN [OrderDetail] od ON o.OrderID = od.OrderID\n"
                        + " LEFT JOIN Service se ON se.ServiceID = od.ServiceID\n"
                        + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                        + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
                        + " WHERE o.OrderID = ? ";
                ps = con.prepareStatement(sql);
                ps.setInt(1, orderID);

                rs = ps.executeQuery();
                while (rs.next()) {
                    orderDetail = createOrderFromResultSet(rs);
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
        return orderDetail;
    }

    public boolean updateOrder(int orderId, int newStatusId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "UPDATE [Laundry-Middle-Platform].[dbo].[Order] SET StOrderID = ? WHERE OrderID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, newStatusId);
                ps.setInt(2, orderId);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateDateApprove(int orderId, Date dateApprove) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "UPDATE [Laundry-Middle-Platform].[dbo].[Order] SET DateApprove = ? WHERE OrderID = ?";
                ps = con.prepareStatement(sql);
                ps.setDate(1, dateApprove);
                ps.setInt(2, orderId);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateDateCompleted(int orderId, Date dateCompleted) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "UPDATE [Laundry-Middle-Platform].[dbo].[Order] SET DateCompleted = ? WHERE OrderID = ?";
                ps = con.prepareStatement(sql);
                ps.setDate(1, dateCompleted);
                ps.setInt(2, orderId);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateTimeCompleted(int orderId, String timeCompleted) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "UPDATE [Laundry-Middle-Platform].[dbo].[Order] SET TimeComplete = ? WHERE OrderID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, timeCompleted);
                ps.setInt(2, orderId);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updateStaffOrder(int orderId, int staffId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "UPDATE [Laundry-Middle-Platform].[dbo].[Order] SET StaffID = ? WHERE OrderID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, staffId);
                ps.setInt(2, orderId);

                int row = ps.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }

        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public List<Staff> getNearestStaff(String addCus, List<Staff> Staffs) {
        List<Staff> nearestStaff = new ArrayList<>();

        // Chia địa chỉ khách hàng thành các phần
        String[] CusAddressParts = addCus.split(", ");
//    String cusStreet = CusAddressParts[0];
//    String cusWard = CusAddressParts[1];
        String cusDistrict = CusAddressParts[2];
        String cusCity = CusAddressParts[3];

        for (Staff staff : Staffs) {
            // Chia địa chỉ của nhân viên thành các phần
            String[] staffAddressParts = staff.getAddress().split(", ");
//        String staffStreet = staffAddressParts[0];
//        String staffWard = staffAddressParts[1];
            String staffDistrict = staffAddressParts[2];
            String staffCity = staffAddressParts[3];

            // So sánh thành phố và quận của khách hàng và nhân viên
            if (cusCity.equalsIgnoreCase(staffCity) && cusDistrict.equalsIgnoreCase(staffDistrict)) {
                nearestStaff.add(staff);
            }
        }

        return nearestStaff;
    }

    public int getTotalOrders() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT COUNT(*) AS TotalOrders FROM [Laundry-Middle-Platform].[dbo].[Order];";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt("TotalOrders");
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
        return 0; 
    }
    
    public int getTotalOrderCompleteds() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT COUNT(*) AS TotalOrderCompleteds FROM [Laundry-Middle-Platform].[dbo].[Order] o WHERE o.StOrderID = 5;";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt("TotalOrderCompleteds");
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
        return 0; 
    }
    
    public int getTotalOrderProcessings() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT COUNT(*) AS TotalOrderProcessings FROM [Laundry-Middle-Platform].[dbo].[Order] o "
                                      + " WHERE o.StOrderID IN (1, 2, 4, 6, 7);";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt("TotalOrderProcessings");
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
        return 0; 
    }

}
