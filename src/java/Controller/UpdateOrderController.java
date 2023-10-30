package Controller;

import Service.OrderService;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String button = request.getParameter("btAction");
            String orderID = request.getParameter("orderID");
            String addressCus = request.getParameter("addressCus");
            
            OrderService ord = new OrderService();

            if (button.equals("Cancel")) {
                ord.updateOrder(Integer.parseInt(orderID), 3);
                response.sendRedirect("MainController?btAction=1");
            } else if (button.equals("Approve")) {

                LocalDate approveDate = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String formattedDate = approveDate.format(formatter);
                java.sql.Date sqlDate = java.sql.Date.valueOf(formattedDate);
                
                //Cập nhập trạng thái cho đơn hàng
                ord.updateOrder(Integer.parseInt(orderID), 6);
                //Cập nhập ngày nhận đơn hàng 
                ord.updateDateApprove(Integer.parseInt(orderID), sqlDate);
                response.sendRedirect("MainController?btAction=1");
            } else if (button.equals("Done")) {
                ord.updateOrder(Integer.parseInt(orderID), 2);
                response.sendRedirect("MainController?btAction=4");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateOrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UpdateOrderController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(UpdateOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
