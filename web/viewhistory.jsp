<%-- 
    Document   : viewhistory
    Created on : Oct 5, 2023, 2:46:31 PM
    Author     : nguye
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
        <h1>Search Page</h1>
        <form action="MainController" method="post">
            Search Value: 
            <input type="text" name="txtSearchValue" value="" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <%
            String searchvalue = request.getParameter("txtSearchValue");
            if (searchvalue != null) {
                List<Order> result = (List<Order>) request.getAttribute("SEARCHRESULT");
                if (result != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>OrderID</th>
                    <th>ServiceID</th>
                    <th>Weight</th>
                    <th>TotalPrice</th>
                    <th>Note</th>
                    <th>DateApprove</th>
                    <th>DateCompleted</th>
                    <th>TimeComplete</th>
                    <th>CustomerID</th>
                    <th>StoreID</th>
                    <th>StaffID</th>
                    <th>StOrderDetail</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
            </thead>

            <%
                int count = 0;
                for (Order ord : result) {
            %>
            <form action="MainController" method="post">
                <tr>
                    <td>
                        <%= ++count%>
                    </td>
                    <td>
                        <%= ord.getOrderID()%>
                        <input type="hidden" name="txtOrderID" value="<%= ord.getOrderID()%>" />
                    </td>
                    <td>
                        <%= ord.getServiceDetail()%>
                    </td>
                    <td>
                        <%= ord.getWeight()%>
                    </td>
                    <td>
                        <%= ord.getTotalPrice()%>
                    </td>
                    <td>
                        <%= ord.getNOTE()%>
                    </td>
                    <td>
                        <%= ord.getDateApproved()%>
                    </td>
                    <td>
                        <%= ord.getDateComplete()%>
                    </td>
                    <td>
                        <%= ord.getTimeComplete()%>
                    </td>
                    <td>
                        <%= ord.getCustomerName()%>
                    </td>
                    <td>
                        <%= ord.getStoreName()%>
                    </td>
                    <td>
                        <%= ord.getStaffName()%>
                    </td>
                    <td>
                        <%= ord.getStOrderDetail()%>
                    </td>
                    <td>
                        <!-- Nút cập nhật -->
                        <!--                    <input type="submit" name="btnUpdate" value="Cập nhật" />-->
                    </td>
                    <td>
                        <!-- Nút xóa -->
                        <!--                    <input type="submit" name="btnDelete" value="Xóa" />-->
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </table>
        <%
        } else {
        %>
        hahaha
        <%
                }
            }
        %>

    
</html>
