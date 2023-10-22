/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import Service.StoreService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ConfirmOrderController", urlPatterns = {"/ConfirmOrderController"})
public class ConfirmOrderController extends HttpServlet {

    private final String THANHTOAN = "thanhtoan.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            // Lấy giá trị từ trang JSP
            String url = THANHTOAN;
            HttpSession session1 = request.getSession();
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            String customerAddress = request.getParameter("customerAddress");
            int kilos = Integer.parseInt(request.getParameter("kilos"));
            int serviceId = Integer.parseInt(request.getParameter("services"));
            String note = request.getParameter("note");
            String TimeDesired = request.getParameter("time");
            String DateDesired = request.getParameter("date");

            String storeId = request.getParameter("storeID");
            String storeAddress = request.getParameter("catAddress");

            String totalPrice = request.getParameter("totalPrice");
            User user = (User) session1.getAttribute("user");
            int userId = user.getUserId();

            StoreService store = new StoreService();
            boolean success = store.BookingOrder(phone, fullname, storeId, serviceId, kilos,
                    totalPrice, customerAddress, storeAddress, note, userId, DateDesired, TimeDesired);
            if (success) {
                request.setAttribute("successMessage", "Đặt hàng thành công!");
            } else {
                request.setAttribute("errorMessage", "Đã xảy ra lỗi khi đặt hàng. Vui lòng thử lại sau!");
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);

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
        } catch (ParseException ex) {
            Logger.getLogger(ConfirmOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ParseException ex) {
            Logger.getLogger(ConfirmOrderController.class.getName()).log(Level.SEVERE, null, ex);
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
