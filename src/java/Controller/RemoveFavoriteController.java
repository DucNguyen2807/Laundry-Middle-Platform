/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "RemoveFavoriteController", urlPatterns = {"/RemoveFavoriteController"})
public class RemoveFavoriteController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        User user = (User) request.getSession().getAttribute("user");

        if (user != null) {
            int userID = user.getUserId();
            String storeID = request.getParameter("storeID");

            if (storeID != null) {
                UserService usersv = new UserService();
                try {
                    boolean success = usersv.removeToFavorites(userID, storeID);
                    if (success) {
                        response.getWriter().println("Cửa hàng với ID " + storeID + " đã được thêm vào danh sách yêu thích.");
                    } else {
                        response.getWriter().println("Có lỗi xảy ra khi thêm cửa hàng vào danh sách yêu thích.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().println("Có lỗi xảy ra khi thêm cửa hàng vào danh sách yêu thích.");
                }
            } else {
                response.getWriter().println("Lỗi: Không có ID cửa hàng được cung cấp.");
            }
        } else {
            response.getWriter().println("Lỗi: Người dùng chưa đăng nhập hoặc phiên làm việc đã hết hạn.");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
