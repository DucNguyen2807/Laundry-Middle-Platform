<%-- 
    Document   : storesetting.jsp
    Created on : Oct 26, 2023, 8:31:56 PM
    Author     : acer
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/thanhtoan.css">
        <link href="css/styleindex.css" rel="stylesheet" >
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link href="css/cate.css" rel="stylesheet" >
    </head>

    <body>
        <form action="UpdatePriceServiceStoreController" method="POST" accept-charset="UTF-8">
            <c:forEach items="${storeU}" var="storeUp">


                <div class="container mt-5">
                    <div class="row">
                        <div class="col-md-5">
                            <div class="store-details">
                                <img class="card-img-top" src="<c:out value='${storeUp.image}'/>" alt="Store Image" style="width: 100%; height: auto;">
                            </div>
                        </div>
                        <div class="col-md-7">
                            <!-- Khung bên phải -->
                            <div class="customer-details">
                                <div style="margin-bottom: 20px">Tên cửa hàng:<input type="text" name="nameStore"  value="${storeUp.storeName}" ><br></div>
                                <div style="margin-bottom: 20px">Địa chỉ:<input type="text" name="addressStore" value="${storeUp.address}" ><br></div>
                                <div style="margin-bottom: 20px">Giặt thường:<input type="number"" name="giatthuong" value="${storeUp.priceGiatThuong}"><br></div>
                                <div style="margin-bottom: 20px">Giặt Nhanh:<input type="number"" name="giatnhanh" value="${storeUp.priceGiatNhanh}"><br></div>
                                <div style="margin-bottom: 20px">Giặt Siêu Tốc:<input type="number"" name="giatsieutoc" value="${storeUp.priceGiatSieuToc}"><br></div>
                                <input type="submit" name="btAction" value="Update Service" >
                            </div>
                        </div>
                    </div>
                </div>

            </c:forEach>
        </form>
    </body>
</html>
