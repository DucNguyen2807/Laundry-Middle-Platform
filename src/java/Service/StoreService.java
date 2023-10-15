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
        cate.setPrice(Integer.parseInt(rs.getString("PriceDetail")));
        cate.setService(rs.getString("ServiceDetail"));
        cate.setRating(Integer.parseInt(rs.getString("Rating")));
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
                String sql = "SELECT u.UserID, u.Fullname, u.Address, p.PriceDetail, s.ServiceDetail, r.Rating, r.ReviewText, i.ImageDetail "
                        + "FROM [User] u "
                        + "INNER JOIN Price p ON p.StoreID = u.UserID "
                        + "INNER JOIN Service s ON p.ServiceID = s.ServiceID "
                        + "INNER JOIN Review r ON r.StoreID = u.UserID "
                        + "INNER JOIN Image i ON i.StoreID = u.UserID "
                        + "INNER JOIN Favorite f ON f.StoreID = u.UserID "
                        + "WHERE s.ServiceID = 1";

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
}
