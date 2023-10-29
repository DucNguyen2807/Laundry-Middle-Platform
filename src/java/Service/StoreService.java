/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Cate;
import DBConnect.ConnectDB;
import Model.Review;
import Model.Store;
import Model.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private Cate createCateFromResultSet(ResultSet rs) throws SQLException {
        Cate cate = new Cate();
        cate.setStoreID(rs.getString("UserID"));
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
    public List<Cate> listCate;

    public List<Cate> getListStoreCate() {
        return listCate;
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

    public List<Review> allReviews;

    public List<Review> getListCate() {
        return this.allReviews;
    }

    public void getAllReview(int userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        allReviews = new ArrayList<>();
        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "SELECT r.ReviewText, r.Rating, u.Fullname, r.StoreID "
                        + "FROM Review r "
                        + "LEFT JOIN [User] u ON r.CustomerID = u.UserID "
                        + "WHERE r.StoreID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String reviewText = rs.getString("ReviewText");
                    int rating = rs.getInt("Rating");
                    String Fullname = rs.getString("Fullname");
                    String storeID = rs.getString("StoreID");
                    Review review = new Review(reviewText, rating, Fullname, storeID);
                    allReviews.add(review);
                    
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

    public List<Review> listStoreSale;

    public List<Review> getListStoreSale() {
        return this.listStoreSale;
    }

    public void getStoreSale(int userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listStoreSale = new ArrayList<>();
        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "SELECT  u.UserID, u.Fullname,  p.PriceDetail AS giatthuong, p1.PriceDetail AS giatnhanh, p2.PriceDetail AS giatsieutoc, "
                        + "i.ImageDetail, u.Address,  AVG(r.Rating) AS AverageRating "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID AND p.ServiceID = 1 "
                        + "INNER JOIN Price p1 ON p1.StoreID = u.UserID AND p1.ServiceID = 2 "
                        + "INNER JOIN Price p2 ON p2.StoreID = u.UserID AND p2.ServiceID = 3 "
                        + "LEFT JOIN Service s ON s.ServiceID = p.ServiceID "
                        + "INNER JOIN Review r ON r.StoreID = u.UserID "
                        + "LEFT JOIN Image i ON i.StoreID = u.UserID "
                        + "WHERE u.UserID = ? "
                        + "GROUP BY u.UserID, u.Fullname, u.Address, p.PriceDetail, p1.PriceDetail, p2.PriceDetail, s.ServiceDetail, r.ReviewText, i.ImageDetail";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String storeID = rs.getString("UserID");
                    String storeName = rs.getString("Fullname");
                    int giatthuong = rs.getInt("giatthuong");
                    int giatnhanh = rs.getInt("giatnhanh");
                    int giatsieutoc = rs.getInt("giatsieutoc");
                    String imageDetail = rs.getString("ImageDetail");
                    String address = rs.getString("Address");
                    double averageRating = rs.getDouble("AverageRating");
                    
                    Review review = new  Review(storeID, storeName, giatthuong, giatnhanh, giatsieutoc, imageDetail, address, averageRating);
                    listStoreSale.add(review);  
                    
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

    public void sortByFavoriteCount() throws ClassNotFoundException, SQLException {
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
                        + "GROUP BY u.UserID, u.Fullname, u.Address, p.PriceDetail, p1.PriceDetail, p2.PriceDetail, s.ServiceDetail, r.ReviewText, i.ImageDetail "
                        + "ORDER BY COUNT(f.StoreID) DESC";

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

    public void sortByRating() throws ClassNotFoundException, SQLException {
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
                        + "GROUP BY u.UserID, u.Fullname, u.Address, p.PriceDetail, p1.PriceDetail, p2.PriceDetail, s.ServiceDetail, r.ReviewText, i.ImageDetail "
                        + "ORDER BY AverageRating DESC";

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

    public boolean BookingOrder(String phone, String fullname, String storeId, int serviceId,
            int kilos, String totalPrice, String customerAddress, String AddressSto, String note, int userId, String DateDesired, String TimeDesired) {
        Connection conn = null;
        PreparedStatement pos = null;
        PreparedStatement posd = null;

        try {
            conn = ConnectDB.getConnection();
            if (conn != null) {
                String insertOrderQuery = "INSERT INTO [Order] (DateDesired, TimeDesired, CustomerID, StoreID, StOrderID) VALUES (?, ?, ?, ?, 1)";
                pos = conn.prepareStatement(insertOrderQuery, Statement.RETURN_GENERATED_KEYS);
                pos.setString(1, DateDesired);
                pos.setString(2, TimeDesired);
                pos.setInt(3, userId);
                pos.setString(4, storeId);
                int rowsInserted = pos.executeUpdate();

                if (rowsInserted <= 0) {
                    return false; 
                }

                ResultSet generatedKeys = pos.getGeneratedKeys();
                int orderID = -1;
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                }

                String insertOrderDetailQuery = "INSERT INTO OrderDetail (OrderID, Weight, ServiceID, TotaPrice, Phone, AddressCus, AddressSto, Note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                posd = conn.prepareStatement(insertOrderDetailQuery);
                posd.setInt(1, orderID); 
                posd.setInt(2, kilos);
                posd.setInt(3, serviceId);
                posd.setString(4, totalPrice);
                posd.setString(5, phone);
                posd.setString(6, customerAddress);
                posd.setString(7, AddressSto);
                posd.setString(8, note);
                rowsInserted = posd.executeUpdate();

                return rowsInserted > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {

        } finally {
            try {
                if (posd != null) {
                    posd.close();
                }
                if (pos != null) {
                    pos.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Cate> getNearestStores(User user, List<Cate> Cates) {
        String userAddress = user.getaddress();
        Map<String, Cate> storeMap = new HashMap<>();


        String[] userAddressParts = userAddress.split(", ");
        String userStreet = userAddressParts[0];
        String userWard = userAddressParts[1];
        String userDistrict = userAddressParts[2];
        String userCity = userAddressParts[3];

        for (Cate cate : Cates) {
            String storeAddress = cate.getAddress();
            String[] storeAddressParts = storeAddress.split(", ");
            String storeStreet = storeAddressParts[0];
            String storeWard = storeAddressParts[1];
            String storeDistrict = storeAddressParts[2];
            String storeCity = storeAddressParts[3];

            boolean isMatching = false;

            if (userCity.equals(storeCity)
                    && userDistrict.equals(storeDistrict))
                //|| userWard.equals(storeWard)
            //|| userStreet.equals(storeStreet)) 
            {
                isMatching = true;
            }

            if (isMatching) {
                storeMap.put(cate.getStoreName(), cate);
            }
        }

        List<Cate> nearestStores = new ArrayList<>(storeMap.values());

        Collections.sort(nearestStores, new Comparator<Cate>() {
            @Override
            public int compare(Cate cate1, Cate cate2) {
                return cate1.getStoreName().compareTo(cate2.getStoreName());
            }
        });

        return nearestStores;
    }

    public List<Store> listStoreU;

    public List<Store> getListStoreU() {
        return listStoreU;
    }

    private Store createStoreUFromResultSet(ResultSet rs) throws SQLException {
        Store store = new Store();
        store.setStoreName(rs.getString("Fullname"));
        store.setAddress(rs.getString("Address"));
        store.setPriceGiatThuong(Integer.parseInt(rs.getString("giatthuong")));
        store.setPriceGiatNhanh(Integer.parseInt(rs.getString("giatnhanh")));
        store.setPriceGiatSieuToc(Integer.parseInt(rs.getString("giatsieutoc")));
        store.setImage(rs.getString("ImageDetail"));

        return store;

    }

    public void getAllStoreU(int userID) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listStoreU = new ArrayList<>();
        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT TOP (1000) Fullname, Address, p.PriceDetail AS giatthuong, p1.PriceDetail AS giatnhanh, p2.PriceDetail AS giatsieutoc, "
                        + "i.ImageDetail "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID AND p.ServiceID = 1 "
                        + "INNER JOIN Price p1 ON p1.StoreID = u.UserID AND p1.ServiceID = 2 "
                        + "INNER JOIN Price p2 ON p2.StoreID = u.UserID AND p2.ServiceID = 3 "
                        + "LEFT JOIN Service s ON s.ServiceID = p.ServiceID "
                        + "LEFT JOIN Image i ON i.StoreID = u.UserID "
                        + "WHERE RoleID = 3 AND u.UserID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, userID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    Store store = createStoreUFromResultSet(rs);
                    listStoreU.add(store);
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

    public static boolean UpdateService(int userId, String newFullname, String newAddress, int service1Price, int service2Price, int service3Price) {
        Connection conn = null;
        PreparedStatement pos = null;

        try {
            conn = ConnectDB.getConnection();
            if (conn != null) {
                // Câu truy vấn cập nhật Fullname và Address trong bảng [User]
                String updateUserQuery = "UPDATE [User] SET Fullname = ?, Address = ? WHERE UserID = ?";
                pos = conn.prepareStatement(updateUserQuery);
                pos.setString(1, newFullname);
                pos.setString(2, newAddress);
                pos.setInt(3, userId);
                int rowsUpdated = pos.executeUpdate();

                if (rowsUpdated <= 0) {
                    return false;
                }

                // Câu truy vấn cập nhật giá trị dịch vụ trong bảng [Price]
                String updatePriceQuery = "UPDATE [Price] SET PriceDetail = CASE "
                        + "WHEN ServiceID = 1 THEN ? "
                        + "WHEN ServiceID = 2 THEN ? "
                        + "WHEN ServiceID = 3 THEN ? "
                        + "ELSE PriceDetail "
                        + "END WHERE StoreID = ?";
                pos = conn.prepareStatement(updatePriceQuery);
                pos.setInt(1, service1Price);
                pos.setInt(2, service2Price);
                pos.setInt(3, service3Price);
                pos.setInt(4, userId);
                rowsUpdated = pos.executeUpdate();

                return rowsUpdated > 0;
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Xử lý lỗi (thay thế bằng việc xử lý lỗi thực tế)
            e.printStackTrace();
        } finally {
            try {
                if (pos != null) {
                    pos.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                // Xử lý lỗi (thay thế bằng việc xử lý lỗi thực tế)
                e.printStackTrace();
            }
        }
        return false;
    }
}
