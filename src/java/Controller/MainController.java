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
    private static final String LOGINCONTROLLER = "LoginController";
    private static final String REGISTERCONTROLLER = "RegisterController";

    private static final String VIEWHISORYCONTROLLER = "ViewHistoryController";
    private static final String VIEWSTAFFCONTROLLER = "ViewStaffController";
    private static final String VIEWSTORECONTROLLER = "ViewStoreController";
    private static final String VIEWCUSTOMERCONTROLLER = "ViewCustomerController";
    private static final String VIEWORDERWAITINGCONTROLLER = "ViewOrderWaitingController";
    private static final String VIEWORDERDETAILCONTROLLER = "ViewOrderDetailController";
    private static final String VIEWREPORTCONTROLLER = "ViewReportController";
    private static final String VIEWORDER_STORE = "ViewNewOrder_Store";
    private static final String VIEWFAVORITECONTROLLER = "ViewFavoriteController";
    private static final String VIEWORDER_CUSTOMER = "ViewOrderCustomerController";

    private static final String UPDATEPROFILECONTROLLER = "UpdateProfileController";
    private static final String UPDATESTAFFORDERCONTROLLER = "UpdateStaffOrderController";
    private static final String UPDATEORDERCONTROLLER = "UpdateOrderController";
    private static final String UPDATESERVICECONTROLLER = "UpdateServiceStoreController";
    private static final String UPDATETASKFINISH = "UpdateTaskFinishController";
    private static final String UPDATETASKCOMPLETED = "UpdateTaskCompletedController";
    private static final String UPDATEPRICESTORECONTROLLER = "UpdatePriceServiceStoreController";

    private static final String CONFIRMORDERCONTROLLER = "ConfirmOrderController";
    private static final String CANCELORDERCUSCONTROLLER = "CancelOrderCusController";
    private static final String CANCELUPDATECONTROLLER = "CancelUpdateController";
    private static final String CATECONTROLLER = "CateController";

    private static final String DELETEACCCONTROLLER = "DeleteAccController";
    private static final String DELETESERVICESTORECONTROLLER = "DeleteServiceStoreController";

    private static final String LISTTASKCONTROLLER = "ListTaskController";
    private static final String REVIEWSTORECONTROLLER = "ReviewStoreController";
    private static final String INSERTIMAGECONTROLLER = "InsertImageController";

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
            } else if (button.equals("ViewOrderWaiting")) {
                url = VIEWORDERWAITINGCONTROLLER;
            } else if (button.equals("ViewOrderDetail")) {
                url = VIEWORDERDETAILCONTROLLER;
            } else if (button.equals("ViewReport")) {
                url = VIEWREPORTCONTROLLER;
            } else if (button.equals("ConfirmStaff")) {
                url = UPDATESTAFFORDERCONTROLLER;
            } else if (button.equals("DeleteStaff")) {
                url = DELETEACCCONTROLLER;
            } else if (button.equals("Category")) {
                url = CATECONTROLLER;
            } else if (button.equals("ConfirmOrder")) {
                url = CONFIRMORDERCONTROLLER;
            } else if (button.equals("1") || button.equals("4") || button.equals("5") || button.equals("2")) {
                url = VIEWORDER_STORE;
            } else if (button.equals("Cancel") || button.equals("Approve") || button.equals("Done")) {
                url = UPDATEORDERCONTROLLER;
            } else if (button.equals("Favorite")) {
                url = VIEWFAVORITECONTROLLER;
            } else if (button.equals("Settings")) {
                url = UPDATESERVICECONTROLLER;
            } else if (button.equals("CancelUppdate")) {
                url = CANCELUPDATECONTROLLER;
            } else if (button.equals("7") || button.equals("8")) {
                url = LISTTASKCONTROLLER;
            } else if (button.equals("Finish")) {
                url = UPDATETASKFINISH;
            } else if (button.equals("Completed")) {
                url = UPDATETASKCOMPLETED;
            } else if (button.equals("NewOrderCus") || button.equals("ProcessingCustomer") || button.equals("CompletedCustomer") || button.equals("WaitingCustomer")) {
                url = VIEWORDER_CUSTOMER;
            } else if (button.equals("CancelCus")) {
                url = CANCELORDERCUSCONTROLLER;
            } else if (button.equals("UpdatePriceStore")) {
                url = UPDATEPRICESTORECONTROLLER;
            } else if (button.equals("DeleteServiceStore")) {
                url = DELETESERVICESTORECONTROLLER;
            } else if (button.equals("Review")) {
                url = REVIEWSTORECONTROLLER;
            } else if (button.equals("ChangePhoto")) {
                url = INSERTIMAGECONTROLLER;
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
