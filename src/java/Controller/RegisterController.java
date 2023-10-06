/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Service.InsertError;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author khait
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String CREATENEWACCOUNT = "register.jsp";
    private final String LOGINPAGE = "homepage.html";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String url = CREATENEWACCOUNT;
            InsertError err = new InsertError();
            boolean bErrors = false;
            try {
                String username = request.getParameter("username");
                String fullName = request.getParameter("fname");
                String password = request.getParameter("password");
                String confirmPassword = request.getParameter("cfpassword");
                String phone = request.getParameter("phone");
                String roleid = request.getParameter("role_id");

                // Kiểm tra mật khẩu xác nhận
                if (!confirmPassword.trim().equals(password)) {
                    err.setConfirmNotMatch("Mật khẩu không khớp!!!");
                    bErrors = true;
                }

                // Kiểm tra sự tồn tại của username
                if (!bErrors) {
                    try {
                        if (UserService.checkExistUsername(username)) {
                            err.setUsernameDuplicateErr("Username đã tồn tại!!!");
                            bErrors = true;
                        }
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                        // Xử lý lỗi kết nối đến cơ sở dữ liệu nếu cần
                    }
                }
                if (bErrors) {
                    request.setAttribute("INSERTERROR", err);
                } else {
                    boolean result = UserService.insert(username, password, phone, fullName, roleid);
                    if (result) {
                        url = LOGINPAGE;
                    } else {
                        // Xử lý lỗi nếu có lỗi xảy ra trong quá trình insert
                        err.setSetInsertError("Có lỗi xảy ra trong quá trình đăng ký!!!");
                        request.setAttribute("INSERTERROR", err);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Xử lý lỗi nếu có lỗi xảy ra trong quá trình thực hiện các thao tác với cơ sở dữ liệu
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
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
