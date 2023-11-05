<%-- 
    Document   : homepage_store
    Created on : Oct 8, 2023, 2:29:13 PM
    Author     : khait
--%>

<%@page import="Model.Order"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Laundry Middle Platform</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/styleindex.css" rel="stylesheet" >
        <style>
        </style>
    </head>

    <body>

        <div class="container-fluid">

            <div class="row" style="background-color: #22638f">

                <nav class="navbar navbar-inverse bg-primary" role="navigation">
                    <div class="container-fluid">
                        <!-- Brand and toggle get grouped for better mobile display -->
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse"
                                    data-target="#bs-example-navbar-collapse-1">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>

                        </div>

                        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                            <ul class="nav navbar-nav">
                                <li class="active"><a href="homepage_store.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>

                                <li class="nav-form">
                                    <form action="MainController" method="post">
                                        <input type="hidden" name="btAction" value="1" />
                                        <button type="submit" class="btn btn-primary">New Order</button>
                                    </form>
                                </li>

                                <li class="nav-form">
                                    <form action="MainController" method="post">
                                        <input type="hidden" name="btAction" value="4" />
                                        <button type="submit" class="btn btn-primary">Processing</button>
                                    </form>                        
                                </li>

                                <li class="nav-form">
                                    <form action="MainController" method="post">
                                        <input type="hidden" name="btAction" value="2" />
                                        <button type="submit" class="btn btn-primary">Waiting</button>
                                    </form>
                                </li>

                                <li class="nav-form">
                                    <form action="MainController" method="post">
                                        <input type="hidden" name="btAction" value="5" />
                                        <button type="submit" class="btn btn-primary">Completed</button>
                                    </form>
                                </li>

                            </ul>

                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="login.jsp" style="color: #ffffff">Logout</a></li>
                            </ul>
                        </div>
                    </div><!-- /.container-fluid -->
                </nav>
            </div>


            <div class="container mt-4" style="position: absolute;">
                <h1 class="display-4">Danh sách đơn hàng</h1>

                <%
                    String searchvalue = request.getParameter("txtSearchOrder");
                    if (searchvalue != null) {
                        List<Order> result = (List<Order>) request.getAttribute("SEARCHRESULT");
                        if (result != null && !result.isEmpty()) {
                %>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">No.</th>
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
                    <form action="MainController">
                        <tr>
                            <td><%= ++count%></td>
                            <td><%= ord.getServiceDetail()%></td>
                            <td><%= ord.getWeight()%></td>
                            <td><%= ord.getTotalPrice()%></td>
                            <td><%= ord.getNote()%></td>
                            <td><%= ord.getDateApproved()%></td>
                            <td><%= ord.getDateComplete()%></td>
                            <td><%= ord.getTimeComplete()%></td>
                            <td><%= ord.getCustomerName()%></td>
                            <td><%= ord.getStoreName().toUpperCase()%></td>
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
                <div class="alert alert-warning mt-3" role="alert">Không tìm thấy kết quả!</div>
                <%
                        }
                    }
                %>
            </div>

            <script src="js/Jquery.js"></script>
            <script src="js/bootstrap.min.js"></script>

    </body>

</html>
