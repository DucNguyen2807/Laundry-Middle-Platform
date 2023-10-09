/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.ConnectDB;
import Model.Order;
import Model.User;
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
public class UserService implements Serializable {

    public boolean CheckLogin(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "Select * From [User] Where Username = ? And Password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    /*check role id*/
    public int CheckRole(String username, String password) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "Select RoleID From [User] Where Username = ? And Password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("RoleID");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    private List<Order> listOrder;

    public List<Order> getListOrder() {
        return listOrder;
    }

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

    public void searchByOrderID(String searchValue, int userId, int userRole) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listOrder = new ArrayList<>(); // Đảm bảo listOrder là một danh sách rỗng

        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql;

                if (userRole < 4) {
                    // Nếu vai trò của người dùng là nhỏ hơn 4 (có quyền xem đơn hàng của chính họ)
                    sql = "SELECT o.OrderID, se.ServiceDetail, o.Weight, o.TotaPrice, o.Note, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                            + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
                            + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                            + " LEFT JOIN Service se ON se.ServiceID = o.ServiceID\n"
                            + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
                            + " WHERE OrderID = ? AND CustomerID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(searchValue));
                    stm.setInt(2, userId);
                } else if (userRole >= 4) {
                    // Nếu vai trò của người dùng là lớn hơn hoặc bằng 4 (quản trị viên)
                    sql = "SELECT o.OrderID, se.ServiceDetail, o.Weight, o.TotaPrice, o.Note, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                            + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
                            + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                            + " LEFT JOIN Service se ON se.ServiceID = o.ServiceID\n"
                            + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
                            + " WHERE OrderID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(searchValue));
                }

                rs = stm.executeQuery();
                while (rs.next()) {
                    // Xử lý kết quả và tạo danh sách đơn hàng
                    Order order = createOrderFromResultSet(rs);
                    listOrder.add(order);
                }
            }
        } finally {
            // Đóng tài nguyên
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getAllOrders(int userId, int userRole) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listOrder = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql;

                if (userRole < 4) {
                    sql = "SELECT o.OrderID, se.ServiceDetail, o.Weight, o.TotaPrice, o.Note, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                            + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
                            + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                            + " LEFT JOIN Service se ON se.ServiceID = o.ServiceID\n"
                            + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n"
                            + " WHERE CustomerID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, userId);
                } else if (userRole >= 4) {
                    sql = "SELECT o.OrderID, se.ServiceDetail, o.Weight, o.TotaPrice, o.Note, o.DateApprove, o.DateCompleted, o.TimeComplete,\n"
                            + " u.Fullname AS CustomerName, us.Fullname AS StoreName, uf.Fullname AS StaffName, StOrderDetail\n"
                            + " FROM [Laundry-Middle-Platform].[dbo].[Order] o\n"
                            + " LEFT JOIN Service se ON se.ServiceID = o.ServiceID\n"
                            + " LEFT JOIN StatusOrder st ON st.StOrderID = o.StOrderID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] u ON u.UserID = o.CustomerID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] us ON us.UserID = o.StoreID\n"
                            + " LEFT JOIN [Laundry-Middle-Platform].[dbo].[User] uf ON uf.UserID = o.StaffID\n";
                    stm = con.prepareStatement(sql);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    // Xử lý kết quả và tạo danh sách đơn hàng
                    Order order = createOrderFromResultSet(rs);
                    listOrder.add(order);
                }
            }
        } finally {
            // Đóng tài nguyên
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public static boolean insert(String username, String password, String phone, String fullname, String roleid) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "INSERT INTO [User](Username, Password, Phone, Fullname, RoleID) VALUES (?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, phone);
                ps.setString(4, fullname);
                ps.setString(5, roleid);

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

    public static boolean checkExisPhone(String Phone) throws SQLException, ClassNotFoundException {
        boolean duplicate = false;
        Connection con = ConnectDB.getConnection();
        try {
            String query = "select * from [User] where Phone =?";

            PreparedStatement psmt = con.prepareStatement(query);
            psmt.setString(1, Phone);
            ResultSet resultSet = psmt.executeQuery();
            if (resultSet.next()) {
                duplicate = true;
            }
            psmt.close();
            con.close();
        } catch (SQLException e) {

        }
        return duplicate;
    }

    public static boolean checkExistUsername(String username) throws ClassNotFoundException, SQLException {
        boolean duplicate = false;
        Connection conn = ConnectDB.getConnection();
        try {
            String query = "select * from [User] where username = ?";

            PreparedStatement psmt = conn.prepareStatement(query);

            psmt.setString(1, username);

            ResultSet resultSet = psmt.executeQuery();

            if (resultSet.next()) {
                duplicate = true;
            }
            psmt.close();
            conn.close();
        } catch (SQLException ex) {
        }
        return duplicate;
    }

    public static User getUser(String username) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT * FROM [User] WHERE Username = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();

                if (rs.next()) {
                    user = new User();
                    user.setUsername(rs.getString("Username"));
                    user.setPhone(rs.getString("Phone"));
                    user.setFullname(rs.getString("Fullname"));
                    user.setEmail(rs.getString("Email"));
                    user.setaddress(rs.getString("address"));
                    user.setRoleId(Integer.parseInt(rs.getString("RoleID")));
                    user.setUserId(Integer.parseInt(rs.getString("UserID")));

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

        return user;
    }

    public static boolean UpdateUser(String fullname, String email, String phone, String address, String password, String username) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "UPDATE [User] SET fullname=?, email=?, phone=?, address=?, password=? WHERE username=?";
                ps = con.prepareStatement(sql);
                ps.setString(1, fullname);
                ps.setString(2, email);
                ps.setString(3, phone);
                ps.setString(4, address);
                ps.setString(5, password);
                ps.setString(6, username);

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

    public static String getPasswordByUsername(String username) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String password = null;

        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "SELECT Password FROM [User] WHERE Username = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                rs = ps.executeQuery();
                if (rs.next()) {
                    password = rs.getString("password");
                }
            }
        } finally {
            // Close database resources in the reverse order of their creation
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

        return password;
    }

}
