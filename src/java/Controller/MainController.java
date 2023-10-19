/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khait
 */
public class MainController extends HttpServlet {

    private static final String LOGINPAGE = "login.jsp";
    //private static final String HOMEPAGE = "homepage.html";
    private static final String LOGINCONTROLLER = "LoginController";
    private static final String REGISTERCONTROLLER = "RegisterController";
    private static final String VIEWHISORYCONTROLLER = "ViewHistoryController";
    private static final String UPDATEPROFILECONTROLLER = "UpdateProfileController";
    private static final String VIEWSTAFFCONTROLLER = "ViewStaffController";
    private static final String VIEWSTORECONTROLLER = "ViewStoreController";
    private static final String VIEWCUSTOMERCONTROLLER = "ViewCustomerController";
    private static final String VIEWNEWORDER_STORE = "ViewNewOrder_Store";
    private static final String CONFIRMORDERCONTROLLER = "ConfirmOrderController";
    private static final String DELETEACCCONTROLLER = "DeleteAccController";
    private static final String CATECONTROLLER = "CateController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = "";
            String button = request.getParameter("btAction");
            if (button == null) {
                url = LOGINPAGE;
            } else if (button.equals("Login")) {
                url = LOGINCONTROLLER;
            } else if (button.equals("Register")) {
                url = REGISTERCONTROLLER;
            } else if (button.equals("ViewOrder")) {
                url = VIEWHISORYCONTROLLER;
            } else if (button.equals("Update")) {
                url = UPDATEPROFILECONTROLLER;
            } else if (button.equals("ViewStaff")) {
                url = VIEWSTAFFCONTROLLER;
            } else if (button.equals("ViewStore")) {
                url = VIEWSTORECONTROLLER;
            } else if (button.equals("ViewCustomer")) {
                url = VIEWCUSTOMERCONTROLLER;
            } else if (button.equals("DeleteStaff")) {
                url = DELETEACCCONTROLLER;
            } else if (button.equals("Category")) {
                url = CATECONTROLLER;
            } else if (button.equals("1")) {
                url = VIEWNEWORDER_STORE;
            } else if (button.equals("ConfirmOrder")) {
                url = CONFIRMORDERCONTROLLER;
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
