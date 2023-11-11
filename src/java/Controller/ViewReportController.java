/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Service.CustomerService;
import Service.OrderService;
import Service.StaffService;
import Service.StoreService;
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
public class ViewReportController extends HttpServlet {

    private final String VIEWREPORT = "viewreport.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            request.setCharacterEncoding("UTF-8");

            CustomerService cusService = new CustomerService();
            StoreService stoService = new StoreService();
            StaffService staService = new StaffService();
            OrderService ordService = new OrderService();

            try {
                int totalCus = cusService.getTotalCustomers();
                int totalSto = stoService.getTotalStores();
                int totalSta = staService.getTotalStaffs();
                int totalOrd = ordService.getTotalOrders();
                int totalOrdPro = ordService.getTotalOrderProcessings();
                int totalOrdCom = ordService.getTotalOrderCompleteds();

                request.setAttribute("TOTALCUSTOMER", totalCus);
                request.setAttribute("TOTALSTORE", totalSto);
                request.setAttribute("TOTALSTAFF", totalSta);
                request.setAttribute("TOTALORDER", totalOrd);
                request.setAttribute("TOTALORDERPRO", totalOrdPro);
                request.setAttribute("TOTALORDERCOM", totalOrdCom);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                RequestDispatcher rd = request.getRequestDispatcher(VIEWREPORT);
                rd.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
