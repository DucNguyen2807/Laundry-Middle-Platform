<%-- 
    Document   : admin
    Created on : Oct 11, 2023, 12:21:59 AM
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
        <h1>Hello World day la trang ad min</h1>
        <div>
            <form action="MainController" method="post">
                <button value="ViewStaff" name="btAction">Staff</button>
                <input type="hidden" name="txtSearchStaff" value="" />
            </form>
        </div>
    
    </body>
</html>
