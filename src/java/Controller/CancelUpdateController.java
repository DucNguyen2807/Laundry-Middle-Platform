/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "CancelUpdateController", urlPatterns = {"/CancelUpdateController"})
public class CancelUpdateController extends HttpServlet {

    private final String LOGINPAGE = "updateacc.jsp";
    private final String HOMEPAGEADMIN = "homepage_admin.jsp";
    private final String HOMEPAGESTORE = "homepage_store.jsp";
    private final String HOMEPAGECUSTOMER = "homepage_customer.jsp";
    private final String HOMEPAGESTAFF = "homepage_staff.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String url = LOGINPAGE;
            HttpSession session = request.getSession();
            User user = (User) request.getSession().getAttribute("user");
            int userId = user.getUserId();
            int roleId = user.getRoleId();

            switch (roleId) {
                case 1:
                    url = HOMEPAGECUSTOMER;
                    break;
                case 2:
                    url = HOMEPAGESTAFF;
                    break;
                case 3:
                    url = HOMEPAGESTORE;
                    break;
                case 4:
                    url = HOMEPAGEADMIN;
                    break;
                default:
                    break;
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
