<%-- 
    Document   : index
    Created on : Nov 12, 2023, 12:43:03 AM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <style>
        body {
            background-image: url('https://i.pinimg.com/564x/4d/f9/c0/4df9c00c2698ab7d77bb61790127fa67.jpg');
            background-size: 80%; 
        }
    </style>

    <body>
        <h1>Access Denied</h1>
        <p><%= request.getAttribute("notification")%></p>
        <a href="login.jsp">Vui lòng đăng nhập ?</a>
    </body>
</html>
