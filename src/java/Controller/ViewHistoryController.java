/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Order;
import Model.User;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nguye
 */
@WebServlet(name = "ViewHistoryController", urlPatterns = {"/ViewHistoryController"})
public class ViewHistoryController extends HttpServlet {

    List<Order> listOrder = new ArrayList<>();

    private final String SHOWSEARCHCONTROLLER = "vieworder.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            String url = SHOWSEARCHCONTROLLER;
            String searchValue = request.getParameter("txtSearchOrder");
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getUserId();
            int roleId = user.getRoleId();
            UserService ord = new UserService();

            try {
                if (!searchValue.isEmpty()) {
                    // Nếu có giá trị tìm kiếm, thực hiện tìm kiếm theo OrderID
                    ord.searchOrderByCustomerStoreStaff(searchValue, userId, roleId);
                } else {
                    // Nếu không có giá trị tìm kiếm, thực hiện truy vấn để lấy tất cả đơn đặt hàng
                    ord.getAllOrders(userId, roleId);
                }

                List<Order> result = ord.getListOrder();
                request.setAttribute("SEARCHRESULT", result); 

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
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
