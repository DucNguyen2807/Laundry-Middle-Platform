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
        cate.setRating(Integer.parseInt(rs.getString("AverageRating")));
        cate.setAveragePrice(rs.getString("AveragePrice"));
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
                String sql = "SELECT u.UserID, u.Fullname, u.Address, i.ImageDetail, "
                        + "AVG(r.Rating) AS AverageRating, "
                        + "FORMAT(ROUND(AVG(p.PriceDetail), 0), 'N0') AS AveragePrice "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "INNER JOIN [Laundry-Middle-Platform].[dbo].[Price] p ON u.UserID = p.StoreID "
                        + "INNER JOIN [Laundry-Middle-Platform].[dbo].[Service] s ON s.ServiceID = p.ServiceID "
                        + "INNER JOIN [Laundry-Middle-Platform].[dbo].[Review] r ON r.StoreID = u.UserID "
                        + "INNER JOIN [Laundry-Middle-Platform].[dbo].[Image] i ON i.StoreID = u.UserID "
                        + "GROUP BY u.UserID, u.Fullname, u.Address, i.ImageDetail "
                        + "ORDER BY AverageRating DESC;";

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

    public List<Review> allPrice;

    public List<Review> getListPrice() {
        return this.allPrice;
    }

    public void getAllPrice(int userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        allPrice = new ArrayList<>();
        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "SELECT p.StoreID, s.ServiceID ,s.ServiceDetail, FORMAT(PriceDetail, 'N0') AS Price "
                        + "FROM [Price] p "
                        + "INNER JOIN [Laundry-Middle-Platform].[dbo].Service s ON s.ServiceID = p.ServiceID "
                        + "WHERE p.StoreID = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String storeID = rs.getString("StoreID");
                    String serviceID = rs.getString("ServiceID");
                    String serviceDetail = rs.getString("ServiceDetail");
                    String price = rs.getString("Price");
                    Review review = new Review(storeID, price, serviceDetail, serviceID);
                    allPrice.add(review);
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
                String sql = "SELECT u.UserID, u.Fullname, i.ImageDetail, u.Address, AVG(r.Rating) AS AverageRating "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID "
                        + "INNER JOIN Review r ON r.StoreID = u.UserID "
                        + "LEFT JOIN Image i ON i.StoreID = u.UserID "
                        + "WHERE u.UserID = ? "
                        + "GROUP BY u.UserID, u.Fullname, u.Address, i.ImageDetail";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String storeID = rs.getString("UserID");
                    String storeName = rs.getString("Fullname");
                    String imageDetail = rs.getString("ImageDetail");
                    String address = rs.getString("Address");
                    double averageRating = rs.getDouble("AverageRating");
                    Review review = new Review(storeID, storeName, imageDetail, address, averageRating);
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
    public List<Review> listStorewithNoImage;

    public List<Review> getListStorewithNoImage() {
        return this.listStorewithNoImage;
    }
    
    public void getStorewithNoImage(int userId) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listStorewithNoImage = new ArrayList<>();
        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "SELECT u.UserID, u.Fullname, u.Address, AVG(r.Rating) AS AverageRating "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID "
                        + "INNER JOIN Review r ON r.StoreID = u.UserID "
                        + "WHERE u.UserID = ? "
                        + "GROUP BY u.UserID, u.Fullname, u.Address";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String storeID = rs.getString("UserID");
                    String storeName = rs.getString("Fullname");
                    String address = rs.getString("Address");
                    double averageRating = rs.getDouble("AverageRating");
                    Review review = new Review(storeID, storeName, address, averageRating);
                    listStorewithNoImage.add(review);

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
    public List<Review> listImage;

    public List<Review> getListImage() {
        return this.listImage;
    }

    public List<Review> getImage(int userId) {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listImage = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "SELECT i.ImageDetail "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "LEFT JOIN Image i ON i.StoreID = u.UserID "
                        + "WHERE u.UserID = ? ";
                stm = con.prepareStatement(sql);
                stm.setInt(1, userId);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String imageDetail = rs.getString("ImageDetail");
                    Review review = new Review(imageDetail);
                    listImage.add(review);
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Xử lý lỗi hoặc ghi log tại đây
            return null; // Trả về null khi có lỗi
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Xử lý lỗi hoặc ghi log tại đây
            }
        }

        return listStoreSale;
    }

    public void sortByFavoriteCount() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        listCate = new ArrayList<>();
        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT u.UserID, u.Fullname, i.ImageDetail, u.Address, AVG(r.Rating) AS AverageRating, "
                        + "FORMAT(ROUND(AVG(p.PriceDetail), 0), 'N0') AS AveragePrice "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID "
                        + "INNER JOIN Review r ON r.StoreID = u.UserID "
                        + "LEFT JOIN Image i ON i.StoreID = u.UserID "
                        + "INNER JOIN Favorite f ON f.StoreID = u.UserID "
                        + "GROUP BY u.UserID, u.Fullname, u.Address, i.ImageDetail "
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
                String sql = "SELECT u.UserID, u.Fullname, i.ImageDetail, u.Address, AVG(r.Rating) AS AverageRating, "
                        + "FORMAT(ROUND(AVG(p.PriceDetail), 0), 'N0') AS AveragePrice "
                        + "FROM [Laundry-Middle-Platform].[dbo].[User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID "
                        + "INNER JOIN Review r ON r.StoreID = u.UserID "
                        + "LEFT JOIN Image i ON i.StoreID = u.UserID "
                        + "INNER JOIN Favorite f ON f.StoreID = u.UserID "
                        + "GROUP BY u.UserID, u.Fullname, u.Address, i.ImageDetail "
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

    public boolean BookingOrder(String phone, String fullname, String storeId, String serviceID,
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
                posd.setString(3, serviceID);
                totalPrice = totalPrice.replaceAll("\\,", "");
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
                    && userDistrict.equals(storeDistrict)) //|| userWard.equals(storeWard)
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

    public static boolean updateService(int userId, String newFullname, String newAddress, String[] serviceIDs, String[] prices) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDB.getConnection();
            if (conn != null) {
                // Create the SQL query to update the service prices
                String sql = "UPDATE [Price] SET PriceDetail = ? WHERE StoreID = ? AND ServiceID = ?";

                pstmt = conn.prepareStatement(sql);

                for (int i = 0; i < serviceIDs.length; i++) {
                    int serviceId = Integer.parseInt(serviceIDs[i]);
                    String price = (prices[i]);

                    price = price.replaceAll(",", "");
                    pstmt.setInt(1, Integer.parseInt(price));
                    pstmt.setInt(2, userId);
                    pstmt.setInt(3, serviceId);

                    int rowsUpdated = pstmt.executeUpdate();
                    if (rowsUpdated <= 0) {
                        return false;
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
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

    public static boolean insertServiceAndPrice(String serviceDetails, String priceUp, int storeID) {
        Connection conn = null;
        PreparedStatement insertServiceStatement = null;
        PreparedStatement insertPriceStatement = null;

        try {
            conn = ConnectDB.getConnection();

            if (conn != null) {
                String insertServiceQuery = "INSERT INTO Service (ServiceDetail, StoreID) VALUES (?, ?)";
                insertServiceStatement = conn.prepareStatement(insertServiceQuery, Statement.RETURN_GENERATED_KEYS);

                insertServiceStatement.setString(1, serviceDetails);
                insertServiceStatement.setInt(2, storeID);

                int rowsInserted = insertServiceStatement.executeUpdate();

                if (rowsInserted <= 0) {
                    return false;
                }

                ResultSet generatedKeys = insertServiceStatement.getGeneratedKeys();
                int serviceID = -1;
                if (generatedKeys.next()) {
                    serviceID = generatedKeys.getInt(1);
                }

                String insertPriceQuery = "INSERT INTO Price (StoreID, ServiceID, PriceDetail) VALUES (?, ?, ?)";
                insertPriceStatement = conn.prepareStatement(insertPriceQuery);

                insertPriceStatement.setInt(1, storeID);
                insertPriceStatement.setInt(2, serviceID);
                insertPriceStatement.setDouble(3, Double.parseDouble(priceUp));

                rowsInserted = insertPriceStatement.executeUpdate();

                if (rowsInserted <= 0) {
                    return false;
                }
            }
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (insertPriceStatement != null) {
                    insertPriceStatement.close();
                }
                if (insertServiceStatement != null) {
                    insertServiceStatement.close();
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

    public static boolean deleteService(int serviceId) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDB.getConnection();
            if (conn != null) {

                String sql = "DELETE [Price] WHERE ServiceID = ?";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, serviceId);

                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
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

    public boolean checkImage(int storeID) throws ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = ConnectDB.getConnection();
            if (conn != null) {
                String sql = "SELECT ImageID FROM [Image] WHERE StoreID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, storeID);
                ResultSet resultSet = pstmt.executeQuery();
                return resultSet.next(); // Trả về true nếu cửa hàng đã có ảnh, ngược lại trả về false
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
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

    public boolean updateImage(int storeID, String imageDetail) throws ClassNotFoundException {
        if (checkImage(storeID)) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = ConnectDB.getConnection();
                if (conn != null) {
                    String sql = "UPDATE [Image] SET ImageDetail = ? WHERE StoreID = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, imageDetail);
                    pstmt.setInt(2, storeID);
                    int rowsUpdated = pstmt.executeUpdate();
                    return rowsUpdated > 0; // Trả về true nếu cập nhật thành công, ngược lại trả về false
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false; // Trả về false nếu cửa hàng chưa có ảnh
    }

    public boolean insertImage(int storeID, String imageDetail) throws ClassNotFoundException {
        if (!checkImage(storeID)) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = ConnectDB.getConnection();
                if (conn != null) {
                    String sql = "INSERT INTO [Image] (ImageDetail, StoreID) VALUES (?, ?)";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, imageDetail);
                    pstmt.setInt(2, storeID);
                    int rowsInserted = pstmt.executeUpdate();
                    return rowsInserted > 0; // Trả về true nếu thêm mới thành công, ngược lại trả về false
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false; // Trả về false nếu cửa hàng đã có ảnh
    }
    
    public int getTotalStores() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT COUNT(*) AS TotalStores FROM [Laundry-Middle-Platform].[dbo].[User] u WHERE u.RoleID = 3;";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    return rs.getInt("TotalStores");
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
