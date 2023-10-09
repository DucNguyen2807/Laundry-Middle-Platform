<%-- 
    Document   : homepage
    Created on : Oct 5, 2023, 9:59:40 PM
    Author     : khait
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home page admin</h1>
            <form action="MainController" method="post">
        <input type="hidden" name="txtSearchValue" value="" />
        <input type="submit" value="Search" name="btAction" />
    </form>

    </body>
</html>
