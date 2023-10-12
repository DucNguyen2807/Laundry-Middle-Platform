/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.User;
import Service.UserService;
import static Service.UserService.getUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author khait
 */
public class LoginController extends HttpServlet {

    private final String LOGINPAGE = "login.jsp";
    private final String HOMEPAGEADMIN = "homepage_admin.jsp";
    private final String HOMEPAGESTORE = "homepage_store.jsp";
    private final String HOMEPAGECUSTOMER = "homepage_customer.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            UserService userService = new UserService();
            boolean result = userService.CheckLogin(username, password);
            String url = LOGINPAGE;
            if (result == false) {
                request.setAttribute("ERROR", "Incorrect Username or Password!");
            } else if (result) {
                Cookie user = new Cookie("user", username);
                Cookie pass = new Cookie("pass", password);

                if (request.getAttribute("check") != null) {
                    user.setMaxAge((60 * 43200));
                    pass.setMaxAge((60 * 43200));

                    response.addCookie(user);
                    response.addCookie(pass);
                }

                int roleid = userService.CheckRole(username, password);
                switch (roleid) {
                    case 1:
                        url = HOMEPAGECUSTOMER;
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
            }
            User user = UserService.getUser(username);
            request.getSession().setAttribute("user", user);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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
