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
        <!-- Đường dẫn đến Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- Đường dẫn đến tệp CSS tùy chỉnh -->
        <link href="css/custom.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container mt-5">
            <h1>Search Page</h1>
            <form action="MainController" method="post">
                <div class="input-group mb-3 mt-3">
                    <input type="text" class="form-control" name="txtSearchValue" placeholder="Search Value">
                    <div class="input-group-append">
                        <button class="btn btn-primary btn-search" value="Search" type="submit" name="btAction">Search</button>
                    </div>
                </div>
            </form>


            <%
                String searchvalue = request.getParameter("txtSearchValue");
                if (searchvalue != null) {
                    List<Order> result = (List<Order>) request.getAttribute("SEARCHRESULT");
                    if (result != null && !result.isEmpty()) {
            %>
            <table class="table table-striped mt-3">
                <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">OrderID</th>
                        <th scope="col">Service</th>
                        <th scope="col">Weight</th>
                        <th scope="col">TotalPrice</th>
                        <th scope="col">Note</th>
                        <th scope="col">DateApprove</th>
                        <th scope="col">DateCompleted</th>
                        <th scope="col">TimeComplete</th>
                        <th scope="col">Customer</th>
                        <th scope="col">Store</th>
                        <th scope="col">Staff</th>
                        <th scope="col">Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (Order ord : result) {
                    %>
                <form action="MainController" method="post">
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= ord.getOrderID()%><input type="hidden" name="txtOrderID" value="<%= ord.getOrderID()%>"></td>
                        <td><%= ord.getServiceDetail()%></td>
                        <td><%= ord.getWeight()%></td>
                        <td><%= ord.getTotalPrice()%></td>
                        <td><%= ord.getNOTE()%></td>
                        <td><%= ord.getDateApproved()%></td>
                        <td><%= ord.getDateComplete()%></td>
                        <td><%= ord.getTimeComplete()%></td>
                        <td><%= ord.getCustomerName()%></td>
                        <td><%= ord.getStoreName()%></td>
                        <td><%= ord.getStaffName()%></td>
                        <td><%= ord.getStOrderDetail()%></td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
            </table>
            <%
            } else {
            %>
            <div class="alert alert-warning mt-3" role="alert">No results found!</div>
            <%
                    }
                }
            %>
        </div>
    </body>
</html>
