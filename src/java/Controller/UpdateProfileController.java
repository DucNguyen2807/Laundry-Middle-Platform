/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author acer
 */
@WebServlet(name = "UpdateProfileController", urlPatterns = {"/UpdateProfileController"})
public class UpdateProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String curPassword = request.getParameter("curpassword");
            String newPassword = request.getParameter("password");
            HttpSession session = request.getSession();
            User user = (User) request.getSession().getAttribute("user");
            String username = user.getUsername();

            if (fullname.length() < 6 || fullname.length() > 30) {
                String errorMessage = "Tên đầy đủ phải có từ 6 đến 30 ký tự.";
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("updateacc.jsp").forward(request, response);
            } else {
                String passwordFromDatabase = UserService.getPasswordByUsername(username);
                if (!curPassword.equals(passwordFromDatabase)) {
                    String errorMessage1 = "Mật khẩu hiện tại không đúng. Vui lòng thử lại.";
                    request.setAttribute("errorMessage1", errorMessage1);
                    request.getRequestDispatcher("updateacc.jsp").forward(request, response);
                } else if (newPassword.length() < 6) {
                    String errorMessage2 = "Mật khẩu mới phải có ít nhất 6 ký tự.";
                    request.setAttribute("errorMessage2", errorMessage2);
                    request.getRequestDispatcher("updateacc.jsp").forward(request, response);
                } else if (!phone.matches("^0[0-9]{9,12}")) {
                    String errorMessage3 = "Số điện thoại không hợp lệ";
                    request.setAttribute("errorMessage3", errorMessage3);
                    request.getRequestDispatcher("updateacc.jsp").forward(request, response);
                } else {
                    UserService.UpdateUser(fullname, email, phone, address, newPassword, username);
                    User updatedUser = UserService.getUser(username);
                    session.setAttribute("user", updatedUser);
                    response.sendRedirect("updateacc.jsp");
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
