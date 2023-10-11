<%--
    Document   : viewstaff
    Created on : Oct 11, 2023, 12:18:48 AM
    Author     : khait
--%>

<%@page import="Model.Staff"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Add Bootstrap CSS link here -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>

        <form action="MainController" method="post">
            <button value="ViewStaff" name="btAction">Staff</button>
            <input type="hidden" name="txtSearchStaff" value="" />
        </form>

        <div class="container mt-4">
            <h1 class="display-4">Danh sách nhân viên</h1>

            <!-- Search form -->
            <form action="MainController" method="post" class="mb-3">
                <div class="input-group">
                    <input type="text" name="txtSearchStaff" class="form-control" placeholder="Tìm nhân viên...">
                    <button type="submit" value="ViewStaff" name="btAction" class="btn btn-primary">Tìm kiếm</button>
                </div>
            </form>

            <%
                String searchvalue = request.getParameter("txtSearchStaff");
                if (searchvalue != null) {
                    List<Staff> result = (List<Staff>) request.getAttribute("SEARCHRESULT");
                    if (result != null && !result.isEmpty()) {
            %>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">No.</th>
                        <th scope="col">Staff ID</th>
                        <th scope="col">Username</th>
                        <th scope="col">Password</th>
                        <th scope="col">Address</th>
                        <th scope="col">Full Name</th>
                        <th scope="col">Phone</th>
                        <th scope="col">Email</th>
                        <th scope="col">Status</th>
                        <th scope="col"></th>
                       

                      
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (Staff staff : result) {
                    %>
                    <tr>
                        <td><%= ++count%></td>
                        <td><%= staff.getstaffID()%></td>
                        <td><%= staff.getUsername()%></td>
                        <td><%= staff.getPassword()%></td>
                        <td><%= staff.getAddress()%></td>
                        <td><%= staff.getFullname()%></td>
                        <td><%= staff.getPhone()%></td>
                        <td><%= staff.getEmail()%></td>
                        <td><%= staff.getStatusDetail()%></td>
                        <td>
                            <form action="MainController" method="post">
                                <input type="hidden" name="staffID" value="<%= staff.getstaffID()%>">
                                <button type="submit" value="DeleteStaff" name="btAction" class="btn btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
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
        <!-- Add Bootstrap JS and jQuery scripts here -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </body>
</html>
