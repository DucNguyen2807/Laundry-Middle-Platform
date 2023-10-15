/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Store;
import Model.Cate;
import Model.Order;
import Model.User;
import Service.StaffService;
import Service.StoreService;
import Service.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
@WebServlet(name = "CateController", urlPatterns = {"/CateController"})
public class CateController extends HttpServlet {

    private final String CATE = "cate.jsp";

        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, ClassNotFoundException, SQLException {
    response.setContentType("text/html;charset=UTF-8");
    String url = CATE;
    try {
        StoreService store = new StoreService();
        store.getAllStore();
        List<Cate> result = store.getListStoreCate();

        // Sắp xếp danh sách theo trường cụ thể, ví dụ: theo địa chỉ
        Collections.sort(result, new Comparator<Cate>() {
            @Override
            public int compare(Cate cat1, Cate cat2) {
                return cat1.getAddress().compareTo(cat2.getAddress());
            }
        });

        request.setAttribute("SEARCHRESULT", result);
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CateController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CateController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CateController.class.getName()).log(Level.SEVERE, null, ex);
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
