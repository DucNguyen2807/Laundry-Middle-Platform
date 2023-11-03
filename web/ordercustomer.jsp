<%-- 
    Document   : ordercustomer
    Created on : Nov 2, 2023, 2:16:15 PM
    Author     : nguye
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
        <link rel="stylesheet" href="css/manage_admin.css">
        <link rel="stylesheet" href="css/viewneworder_store.css">

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
                                <li class="active"><a href="homepage_customer.jsp"><span class="glyphicon glyphicon-home"></span>Home</a></li>
                                <li>
                                    <a style="background-color:#red; color: #ffffff">
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="btAction" value="NewOrderCus" />
                                            <button type="submit" class="btn btn-primary">New Order</button>
                                        </form>
                                    </a>
                                </li>

                                <li>
                                    <a style="background-color:#red; color: #ffffff">
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="btAction" value="ProcessingCustomer" />
                                            <button type="submit" class="btn btn-primary">Processing</button>
                                        </form>
                                    </a>
                                </li>
                                
                                <li>
                                    <a style="background-color:#red; color: #ffffff">
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="btAction" value="WaitingCustomer" />
                                            <button type="submit" class="btn btn-primary">Waiting for a return</button>
                                        </form>
                                    </a>
                                </li>
                                
                                <li>
                                    <a style="background-color:#red; color: #ffffff">
                                        <form action="MainController" method="post">
                                            <input type="hidden" name="btAction" value="CompletedCustomer" />
                                            <button type="submit" class="btn btn-primary">Completed</button>
                                        </form>
                                    </a>
                                </li>

                            </ul>

                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="login.jsp" style="color: #ffffff">Logout</a></li>
                            </ul>
                        </div>
                    </div><!-- /.container-fluid -->
                </nav>
            </div>


            <div class="container mt-4" style="position: absolute; width: 95%">
                <h1 class="display-4">Danh sách đơn hàng</h1>

                <%
                    String button = request.getParameter("btAction");
                    if (button != null) {
                        List<Order> result = (List<Order>) request.getAttribute("SEARCHRESULT");
                        if (result != null && !result.isEmpty()) {
                %>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">No.</th>
                            <th scope="col">Customer</th>
                            <th scope="col">AddressCus</th>
                            <th scope="col">Store</th>
                            <th scope="col">AddressSto</th>
                            <th scope="col">Service</th>
                            <th scope="col">Weight</th>
                            <th scope="col">TotalPrice</th>
                            <th scope="col">Note</th>
                            <th scope="col">DateDesired</th>
                            <th scope="col">TimeDesired</th>
                            <th scope="col">DateApproved</th>
                            <th scope="col">DateCompleted</th>
                            <th scope="col">TimeCompleted</th>  
                            <th scope="col">Staff</th>
                            <th scope="col">Status</th>
                                <%
                                    if (button.equals("NewOrderCus")) {
                                %>
                            <th scope="col"></th> 
                                <%
                                } else if (button.equals("CompletedCustomer")) {
                                %>
                            <th scope="col"></th> 
                                <%
                                    }
                                %>
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
                            <td><%= ord.getCustomerName()%></td>
                            <td><%= ord.getAddressCus()%></td>
                            <td><%= ord.getStoreName()%></td>
                            <td><%= ord.getAddressSto()%></td>
                            <td><%= ord.getServiceDetail()%></td>
                            <td><%= ord.getWeight()%></td>
                            <td><%= ord.getTotalPrice()%></td>
                            <td><%= ord.getNote()%></td>
                            <td><%= ord.getDateDesired()%></td>
                            <td><%= ord.getTimeDesired()%></td>
                            <td><%= ord.getDateApproved()%></td>
                            <td><%= ord.getDateComplete()%></td>
                            <td><%= ord.getTimeComplete()%></td>
                            <td><%= ord.getStaffName()%></td>
                            <td><%= ord.getStOrderDetail()%></td>
                            <%
                                if (button.equals("NewOrderCus")) {
                            %>
                            <td>
                                <input type="hidden" name="orderID" value="<%= ord.getOrderID()%>">
                                <button type="submit" name="btAction" value="CancelCus" class="btn btn-danger" name="btAction" onclick="return confirm('Are you sure you want to cancel this order?')">Cancel</button>
                            </td>
                            <%
                            } else if (button.equals("CompletedCustomer")) {
                            %>
                            <td>
                                <input type="hidden" name="orderID" value="<%= ord.getOrderID()%>">
                                <button type="submit" name="btAction" value="Review" class="btn btn-success" name="btAction">Review</button>
                            </td>
                            <li><a href="reviewforcus.jsp" style="color: #ffffff">Profile</a></li>
                            <%
                                }
                            %>

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
                <div class="alert alert-warning mt-3" role="alert">Đơn hàng trống!</div>
                <%
                        }
                    }
                %>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    </body>

</html>
