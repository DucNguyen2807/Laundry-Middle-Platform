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
        <script>
            var urlParams = new URLSearchParams(window.location.search);
            var successMessage = urlParams.get("successMessage");

            if (successMessage) {
                alert(successMessage);
            }
        </script>
    </head>

    <body>
        <form action="UpdatePriceServiceStoreController" method="POST" accept-charset="UTF-8">


            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-8">
                        <div class="store-details">
                            <c:choose>
                                <c:when test="${not empty storeSale}">
                                    <c:forEach items="${storeSale}" var="store">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div><img src="${store.imageDetail}" name="imageStore" alt="Cửa hàng" alt="Store Image" style="width: 100%; height: auto;" /></div>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                                <div class="col-md-6">
                                    <p>Thông tin cửa hàng</p>
                                    <c:forEach items="${storeSale}" var="store">
                                        <div class="card-body">
                                            Tên cửa hàng:<input type="text" name="nameStore"  value="${store.storeName.toUpperCase()}"><br>

<!--                                            Địa chỉ: <input type="text" name="addressStore" value="${store.address}" >-->
                                            <p>Dịch vụ:</p>
                                            <c:forEach items="${storePrice}" var="price">
                                                <c:if test="${price.storeID eq store.storeID}">
                                                    <p>${price.serviceDetail}:</p>
                                                    <input name ="priceU" value="${price.price}" đ >
                                                    <input type="hidden" name="serviceID" value="${price.serviceID}" />
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                        <input type="hidden" name="storeID" value="${store.storeID}" />
                                        <button type="submit">Update</button>
                                    </c:forEach> 
                                </div>
                            </div>
                        </div>
                    </div>


                    </form>
                    <div class="col-md-4">
                        <div class="store-details">
                            <h3>Add Service</h3>
                            <form action="AddServiceController" method="POST" accept-charset="UTF-8">
                                <!-- Add service form fields -->
                                <div class="form-group">
                                    <label for="serviceDetail">Service Detail:</label>
                                    <input type="text" class="form-control" name="serviceDetail" id="serviceDetail">
                                </div>
                                <div class="form-group">
                                    <label for="servicePrice">Service Price:</label>
                                    <input type="text" class="form-control" name="servicePrice" id="servicePrice">
                                </div>
                                <input type="hidden" name="storeID" value="${store.storeID}" />
                                <button type="submit">Add Service</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    </body>

</html>
