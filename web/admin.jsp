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

        <form action="MainController" method="post">
            <input type="hidden" name="txtSearchValue" value="" />
            <input type="submit" value="Search" name="btAction" />
        </form>
        <form action="MainController" method="post" class="mb-3">
            <div class="input-group">
                <input type="text" name="txtSearchStaff" class="form-control" placeholder="Tìm nhân viên...">
                <button type="submit" value="StaffSearch" name="btAction" class="btn btn-primary">Tìm kiếm</button>
            </div>
        </form>
    </body>
</html>
