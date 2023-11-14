/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.ConnectDB;
import Model.Cate;
import Model.Order;
import Model.User;
import Model.UserGoogleDto;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                    sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight,  FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
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
                            + " WHERE OrderID = ? AND CustomerID = ? OR o.StoreID = ? OR o.StaffID = ?\";";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(searchValue));
                    stm.setInt(2, userId);
                    stm.setInt(3, userId);
                    stm.setInt(4, userId);
                } else if (userRole >= 4) {
                    // Nếu vai trò của người dùng là lớn hơn hoặc bằng 4 (quản trị viên)
                    sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight,  FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
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

    public void searchOrderByCustomerStoreStaff(String searchValue, int userId, int userRole) throws SQLException, ClassNotFoundException {
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
                    sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight, FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
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
                            + " WHERE  u.Fullname = ? OR us.Fullname = ? OR uf.Fullname = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, Integer.parseInt(searchValue));
                    stm.setInt(2, userId);
                    stm.setInt(3, userId);
                    stm.setInt(4, userId);
                } else if (userRole >= 4) {
                    // Nếu vai trò của người dùng là lớn hơn hoặc bằng 4 (quản trị viên)
                    sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight, FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
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
                            + " WHERE  u.Fullname LIKE ? OR us.Fullname LIKE ? OR uf.Fullname LIKE ?";
                    stm = con.prepareStatement(sql);
                    stm.setString(1, "%" + searchValue + "%");
                    stm.setString(2, "%" + searchValue + "%");
                    stm.setString(3, "%" + searchValue + "%");

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
                    sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight, FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
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
                            + " WHERE CustomerID = ? OR o.StoreID = ? OR o.StaffID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, userId);
                    stm.setInt(2, userId);
                    stm.setInt(3, userId);
                } else if (userRole >= 4) {
                    sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight, FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
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
                            + " ORDER BY o.OrderID DESC";
                    stm = con.prepareStatement(sql);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
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

    public void getOrderWaiting(int userId, int userRole) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listOrder = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql;

                if (userRole < 4) {
                    sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight, FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
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
                            + " WHERE CustomerID = ? OR o.StoreID = ? OR o.StaffID = ?";
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, userId);
                    stm.setInt(2, userId);
                    stm.setInt(3, userId);
                } else if (userRole >= 4) {
                    sql = "SELECT o.OrderID, se.ServiceDetail, od.Weight, FORMAT(od.TotaPrice, 'N0') AS Price, od.Phone AS PhoneCus,\n"
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
                            + " WHERE o.StOrderID = 6\n"
                            + " ORDER BY o.OrderID DESC";
                    stm = con.prepareStatement(sql);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
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

    public static boolean insert(String username, String password, String phone, String fullname, String roleid) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "INSERT INTO [User](Username, Password, Phone, Fullname, RoleID) VALUES (?,?,?,?,?)";
                ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setString(3, phone);
                ps.setString(4, fullname);
                ps.setString(5, roleid);

                int rowsInserted = ps.executeUpdate();
                if (roleid.equals("3")) {
                    if (rowsInserted > 0) {
                        ResultSet generatedKeys = ps.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int userID = generatedKeys.getInt(1);

                            String insert5star = "INSERT INTO [Review](Rating, StoreID) VALUES (?, ?)";
                            PreparedStatement pos = con.prepareStatement(insert5star);
                            pos.setInt(1, 5);
                            pos.setInt(2, userID);

                            int row = pos.executeUpdate();

                            return row > 0;
                    }
                }
            }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Xử lý lỗi hoặc ghi log tại đây
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
                    user.setaddress(rs.getString("Address"));
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

    public List<Cate> listFavoriteStore;

    public List<Cate> getlistFavoriteStore() {
        return listFavoriteStore;
    }

    private Cate createCateFromResultSet(ResultSet rs) throws SQLException {
        Cate cate = new Cate();
        cate.setStoreID(rs.getString("UserID"));
        cate.setStoreName(rs.getString("Fullname"));
        cate.setAddress(rs.getString("Address"));
        cate.setRating(Integer.parseInt(rs.getString("AverageRating")));
        cate.setImage(rs.getString("ImageDetail"));
        cate.setAveragePrice(rs.getString("AveragePrice"));
        return cate;

    }

    public void getAllFavorite(int userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listFavoriteStore = new ArrayList<>();
        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT u.UserID, u.Fullname, i.ImageDetail, u.Address, AVG(r.Rating) AS AverageRating, "
                        + "FORMAT(ROUND(AVG(p.PriceDetail), 0), 'N0') AS AveragePrice "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID "
                        + "INNER JOIN Service s ON s.ServiceID = p.ServiceID "
                        + "INNER JOIN Review r ON r.StoreID = u.UserID "
                        + "LEFT JOIN Image i ON i.StoreID = u.UserID "
                        + "INNER JOIN Favorite f ON f.StoreID = u.UserID "
                        + "WHERE f.CustomerID = ? "
                        + "GROUP BY u.UserID, u.Fullname, u.Address, i.ImageDetail";

                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Cate cate = createCateFromResultSet(rs);
                    listFavoriteStore.add(cate);
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
    }

    public boolean addToFavorites(int userID, String storeID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "INSERT INTO [Favorite] (CustomerID, StoreID) VALUES (?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setString(2, storeID);

                int rowsInserted = stm.executeUpdate();
                return rowsInserted > 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean removeToFavorites(int userID, String storeID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "DELETE FROM [Favorite] WHERE CustomerID = ? AND StoreID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);
                stm.setString(2, storeID);

                int rowsInserted = stm.executeUpdate();
                return rowsInserted > 0;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public static boolean insertUser(User user) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "INSERT INTO [User] (Username, FullName, Email, Password, RoleID) VALUES (?, ?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getFullname());
                ps.setString(3, user.getEmail());
                ps.setString(4, "123456");
                ps.setInt(5, 1);
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

        return false; // Return false if the insertion was not successful
    }
        public static boolean insertRating(String reviewText, int rating, int storeID, int customerID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "INSERT INTO [Laundry-Middle-Platform].[dbo].[Review] (ReviewText, Rating, CustomerID, StoreID) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, reviewText);
                ps.setInt(2, rating);
                ps.setInt(3, customerID);
                ps.setInt(4, storeID);

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
    

    public static User getUserGG(String username) throws ClassNotFoundException, SQLException {
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
                    user.setUserId(Integer.parseInt(rs.getString("userId")));
                    user.setUsername(rs.getString("username"));
                    user.setPhone(rs.getString("phone"));
                    user.setFullname(rs.getString("fullname"));
                    user.setEmail(rs.getString("email"));
                    user.setaddress(rs.getString("address"));
                    user.setRoleId(Integer.parseInt(rs.getString("roleId")));
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

    public static boolean getUserByEmail(String email) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                // Create an SQL query to check if a user with the given email exists
                String sql = "SELECT * FROM [User] WHERE Email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);

                resultSet = ps.executeQuery();
                // If a user with the specified email exists, return true
                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false; // If an exception occurs or no user found, return false
    }

}
