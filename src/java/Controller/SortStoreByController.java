/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Cate;
import Model.User;
import Service.StoreService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nguye
 */
@WebServlet(name = "SortStoreByController", urlPatterns = {"/SortStoreByController"})
public class SortStoreByController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String orderBy = request.getParameter("orderBy");
            request.setAttribute("orderBy", orderBy);
            int itemsPerPage = 6;
            int currentPage = 1;
            String pageParam = request.getParameter("page");

            if (pageParam != null) {
                try {
                    currentPage = Integer.parseInt(pageParam);
                } catch (NumberFormatException e) {
                   
                }
            }

            StoreService store = new StoreService();
            List<Cate> result = null;
            if (orderBy != null) {
                switch (orderBy) {
                    case "Nearest":
                        store.getAllStore();
                        User user = (User) request.getSession().getAttribute("user");
                        String addressCus = user.getaddress();
                        List<Cate> nearestStores = store.getNearestStores(user, store.getListStoreCate());
                        result = nearestStores;
                        break;  
                    case "favoriteCount":
                        store.sortByFavoriteCount();
                        result = store.getListStoreCate();
                        break;
                    case "rating":
                        store.sortByRating();
                        result = store.getListStoreCate();
                        break;
                    default:
                        store.getAllStore();
                        result = store.getListStoreCate();
                        break;
                }
            }

            int totalStores = result.size();
            int totalPages;
            totalPages = (int) Math.ceil((double) totalStores / itemsPerPage);

            int startIndex = (currentPage - 1) * itemsPerPage;
            int endIndex = Math.min(startIndex + itemsPerPage, totalStores);

            List<Cate> pagedStores = result.subList(startIndex, endIndex);

            request.setAttribute("pagedStores", pagedStores);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            RequestDispatcher dispatcher = request.getRequestDispatcher("sortstoreby.jsp");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SortStoreByController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SortStoreByController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SortStoreByController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SortStoreByController.class.getName()).log(Level.SEVERE, null, ex);
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
