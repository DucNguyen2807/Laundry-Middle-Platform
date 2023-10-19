/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Cate;
import DBConnect.ConnectDB;
import Model.Store;
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
public class StoreService implements Serializable {

    public List<Store> listStore;

    public List<Store> getListStore() {
        return listStore;
    }

    private Store createStoreFromResultSet(ResultSet rs) throws SQLException {
        String storeID = String.valueOf(rs.getInt("UserID"));
        String username = rs.getString("Username");
        String password = rs.getString("Password");
        String address = rs.getString("Address");
        String storeName = rs.getString("StoreName");
        String phone = rs.getString("Phone");
        String email = rs.getString("Email");

        Store store = new Store(Integer.parseInt(storeID), username, password, address, storeName, phone, email);
        return store;
    }

    public void getStoref() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listStore = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT u.UserID, u.Username, u.Password, u.Address, u.Fullname AS StoreName,\n"
                        + " u.Phone, u.Email\n"
                        + " FROM [Laundry-Middle-Platform].[dbo].[User] u\n"
                        + " WHERE u.RoleID = 3";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Store store = createStoreFromResultSet(rs);
                    listStore.add(store);
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

    public void searchStoreByName(String name) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listStore = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT u.UserID, u.Username, u.Password, u.Address, u.Fullname AS StoreName,\n"
                        + " u.Phone, u.Email\n"
                        + " FROM [Laundry-Middle-Platform].[dbo].[User] u\n"
                        + " WHERE u.RoleID = 3 AND u.Fullname  LIKE ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Store store = createStoreFromResultSet(rs);
                    listStore.add(store);
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
    public List<Cate> listCate;

    public List<Cate> getListStoreCate() {
        return listCate;
    }

    private Cate createCateFromResultSet(ResultSet rs) throws SQLException {
        Cate cate = new Cate();
        cate.setStoreID(Integer.parseInt(rs.getString("UserID")));
        cate.setStoreName(rs.getString("Fullname"));
        cate.setAddress(rs.getString("Address"));
        cate.setPriceGiatThuong(Integer.parseInt(rs.getString("giatthuong")));
        cate.setPriceGiatNhanh(Integer.parseInt(rs.getString("giatnhanh")));
        cate.setPriceGiatSieuToc(Integer.parseInt(rs.getString("giatsieutoc")));
        cate.setService(rs.getString("ServiceDetail"));
        cate.setRating(Integer.parseInt(rs.getString("AverageRating")));
        cate.setReview(rs.getString("ReviewText"));
        cate.setImage(rs.getString("ImageDetail"));

        return cate;

    }

    public void getAllStore() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listCate = new ArrayList<>();
        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT u.UserID, u.Fullname, u.Address, "
                        + "p.PriceDetail AS giatthuong, p1.PriceDetail AS giatnhanh, p2.PriceDetail AS giatsieutoc, "
                        + "s.ServiceDetail, AVG(r.Rating) AS AverageRating, r.ReviewText, i.ImageDetail "
                        + "FROM [User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID AND p.ServiceID = 1 "
                        + "INNER JOIN Price p1 ON p1.StoreID = u.UserID AND p1.ServiceID = 2 "
                        + "INNER JOIN Price p2 ON p2.StoreID = u.UserID AND p2.ServiceID = 3 "
                        + "INNER JOIN Service s ON s.ServiceID = p.ServiceID "
                        + "INNER JOIN Review r ON r.StoreID = u.UserID "
                        + "INNER JOIN Image i ON i.StoreID = u.UserID "
                        + "INNER JOIN Favorite f ON f.StoreID = u.UserID "
                        + "GROUP BY u.UserID, u.Fullname, u.Address, p.PriceDetail, p1.PriceDetail, p2.PriceDetail, s.ServiceDetail, r.ReviewText, i.ImageDetail";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Cate cate = createCateFromResultSet(rs);
                    listCate.add(cate);
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

    public boolean BookingOrder(String phone, String fullname, int storeId, int serviceId,
            int kilos, int totalPrice, String customerAddress, String note, int userId, String session) {
        Connection conn = null;
        PreparedStatement orderPreparedStatement = null;
        PreparedStatement orderDetailPreparedStatement = null;

        try {
            conn = ConnectDB.getConnection();
            if (conn != null) {
                String insertOrderQuery = "INSERT INTO [Order] (DateApprove, TimeDesired, CustomerID, StoreID, StOrderID) VALUES (?, ?, ?, ?, 1)";
                orderPreparedStatement = conn.prepareStatement(insertOrderQuery);
                orderPreparedStatement.setDate(1, new Date(System.currentTimeMillis()));
                orderPreparedStatement.setString(2, session);
                orderPreparedStatement.setInt(3, userId);
                orderPreparedStatement.setInt(4, storeId); // StoreID
                int rowsInserted = orderPreparedStatement.executeUpdate();

                if (rowsInserted <= 0) {
                    return false; // Trả về false nếu không thêm dòng nào.
                }

                String insertOrderDetailQuery = "INSERT INTO OrderDetail (Weight, ServiceID, TotaPrice, Phone, AddressCus, AddressSto, Note) VALUES (?, ?, ?, ?, ?, ?, ?)";
                orderDetailPreparedStatement = conn.prepareStatement(insertOrderDetailQuery);
                orderDetailPreparedStatement.setInt(1, kilos); // Weight
                orderDetailPreparedStatement.setInt(2, serviceId); // ServiceID
                orderDetailPreparedStatement.setInt(3, totalPrice); // TotaPrice
                orderDetailPreparedStatement.setString(4, phone); // Phone
                orderDetailPreparedStatement.setString(5, customerAddress); // AddressCus
                orderDetailPreparedStatement.setInt(6, storeId); // AddressSto
                orderDetailPreparedStatement.setString(7, note); // Note
                rowsInserted = orderDetailPreparedStatement.executeUpdate();

                // Kiểm tra xem liệu câu truy vấn đã thêm dữ liệu vào bảng OrderDetail chưa
                return rowsInserted > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Xử lý lỗi
        } finally {
            try {
                if (orderDetailPreparedStatement != null) {
                    orderDetailPreparedStatement.close();
                }
                if (orderPreparedStatement != null) {
                    orderPreparedStatement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Trả về false nếu có lỗi xảy ra.
    }

}
