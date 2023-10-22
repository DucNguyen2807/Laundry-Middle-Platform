/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DBConnect.ConnectDB;
import Model.Staff;
import Model.User;
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
public class StaffService implements Serializable {

    private List<Staff> listStaff;

    public List<Staff> getListStaff() {
        return listStaff;
    }

    private Staff createStaffFromResultSet(ResultSet rs) throws SQLException {
        String staffID = String.valueOf(rs.getInt("UserID"));
        String username = rs.getString("Username");
        String password = rs.getString("Password");
        String address = rs.getString("Address");
        String fullname = rs.getString("Fullname");
        String phone = rs.getString("Phone");
        String email = rs.getString("Email");
        String statusDetail = rs.getString("StStaffDetail");

        Staff staff = new Staff(Integer.parseInt(staffID), username, password, address, fullname, phone, email, statusDetail);
        return staff;
    }

    public void getStaff() throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listStaff = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT u.UserID, u.Username, u.Password, u.Address, u.Fullname,\n"
                        + " u.Phone, u.Email, st.StStaffDetail\n"
                        + " FROM [Laundry-Middle-Platform].[dbo].[User] u\n"
                        + " LEFT JOIN StatusStaff st ON st.StStaffID = u.Status\n"
                        + " WHERE u.RoleID = 2";

                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()) {
                    Staff staff = createStaffFromResultSet(rs);
                    listStaff.add(staff);
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

    public void searchStaffByName(String name) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        listStaff = new ArrayList<>();

        try {
            con = ConnectDB.getConnection();

            if (con != null) {
                String sql = "SELECT u.UserID, u.Username, u.Password, u.Address, u.Fullname,\n"
                        + " u.Phone, u.Email, st.StStaffDetail\n"
                        + " FROM [Laundry-Middle-Platform].[dbo].[User] u\n"
                        + " LEFT JOIN StatusStaff st ON st.StStaffID = u.Status\n"
                        + " WHERE u.RoleID = 2 AND u.Fullname LIKE ?";

                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + name + "%");
                rs = ps.executeQuery();

                while (rs.next()) {
                    Staff staff = createStaffFromResultSet(rs);
                    listStaff.add(staff);
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

    public boolean deleteStaff(String id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = ConnectDB.getConnection();
            if (con != null) {
                //String sql = "DELETE FROM [Laundry-Middle-Platform].[dbo].[User] WHERE UserID = ?";
                        String sql = "UPDATE [Laundry-Middle-Platform].[dbo].[User] SET Status = 3 WHERE UserID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, id);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
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

}
