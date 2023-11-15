<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
        <link href="css/cate.css" rel="stylesheet" >
        <link href="css/thanhtoan.css" rel="stylesheet" >


        <style>
        </style>
        <script>
            function validateInput(inputField) {
                inputField.value = inputField.value.replace(/[^0-9]/g, ''); 

                var maxPrice = 40000;
                var price = parseInt(inputField.value);

                if (price > maxPrice) {
                    alert("Giá trị không thể lớn hơn 40,000đ.");
                    inputField.value = maxPrice;
                }

                var submitButton = document.querySelector('button[type="submit"]');
                if (inputField.value === "") {
                    alert("Giá trị nhập vào phải là số từ 0 đến 9.");
                    submitButton.disabled = true;
                } else {
                    submitButton.disabled = false;
                }
            }
        </script>
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
                                <li><a href="updateacc.jsp" style="color: #ffffff">Profile</a></li>
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="login.jsp" style="color: #ffffff">Logout</a></li>
                            </ul>
                        </div>
                    </div><!-- /.container-fluid -->
                </nav>
            </div>

            <!--K?t thúc container-fluid-->



            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-6">
                        <form action="MainController" method="POST" accept-charset="UTF-8">
                            <div class="store-details">
                                <c:choose>
                                    <c:when test="${not empty storeImage}">
                                        <c:forEach items="${storeImage}" var="storeImage">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div><img src="${storeImage.imageDetail}" name="imageStore" alt="Cửa hàng" alt="Store Image" style="width: 100%; height: auto;" /></div>
                                                    <label for="imageURL">Đường dẫn URL ảnh:</label>
                                                    
                                                    <input type="text" id="imageURL" name="imageURL" required>
                                                    <input type="hidden" name="btAction" value="ChangePhoto">
                                                    <br>
                                                    <button type="submit" class="btn btn-primary" onclick="return alert('Thay đổi ảnh thành công!')">Thay đổi ảnh</button>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <p>Không có ảnh cửa hàng nào.</p>
                                        <label for="imageURL">Đường dẫn URL ảnh:</label>
                                        
                                        <input type="text" id="imageURL" name="imageURL" required>
                                        <input type="hidden" name="btAction" value="ChangePhoto">
                                        <br>
                                        <button type="submit" class="btn btn-primary" onclick="return alert('Thay đổi ảnh thành công!')">Thay đổi ảnh</button>
                                    </c:otherwise>
                                </c:choose>
                            </div>  
                        </form>

                        <div class="store-details">
                            <h3>Thêm dịch vụ</h3>
                            <form action="AddServiceController" method="POST" accept-charset="UTF-8" onsubmit="return validateForm()">
                                <!-- Add service form fields -->
                                <div class="form-group">
                                    <label for="serviceDetail">Tên dịch vụ:</label>
                                    <input type="text" class="form-control" name="serviceDetail" id="serviceDetail" required>
                                </div>
                                <div class="form-group">
                                    <label for="servicePrice">Giá tiền dịch vụ:</label>
                                    <input type="text" class="form-control" name="servicePrice" id="servicePrice" required>
                                </div>
                                <c:forEach items="${storeSale}" var="store">
                                    <input type="hidden" name="storeID" value="${store.storeID}" />
                                </c:forEach>
                                <button type="submit">Thêm dịch vụ</button>
                            </form>
                        </div>

                    </div>
                    <div class="col-md-6">
                        <div class="store-details">
                            <form action="MainController" method="POST" accept-charset="UTF-8">
                                <c:forEach items="${storeSale}" var="store">
                                    <p>Dịch vụ:</p>
                                    <c:forEach items="${storePrice}" var="price">
                                        <c:if test="${price.storeID eq store.storeID}">
                                            <form action="MainController" method="POST" accept-charset="UTF-8">
                                                <p>${price.serviceDetail}:</p>
                                                <input name="priceU" value="${price.price}" id="priceU${price.serviceID}" oninput="validateInput(this)">
                                                <input type="hidden" name="serviceID" value="${price.serviceID}" />
                                                <button type="submit" name="btAction" value="DeleteServiceStore" class="btn btn-danger"
                                                        onclick="return confirm('Bạn có muốn xóa dịch vụ này không?')">Delete</button>
                                            
                                        </c:if>
                                    </c:forEach>

                                    <br>
                                    
                                    <div class="action-buttons">
                                        <button type="submit" class="btn btn-primary" name="btAction" value="UpdatePriceStore"
                                                onclick="return alert('Cập nhật thành công!')">Update</button>
                                    </div>
                                </c:forEach>
                            </form>
                        </div>

                    </div>
                </div>  
            </div>

            <div class="container-fluid" style="margin-left: -15px; margin-right: -15px;margin-top: -15px;">

                <div class="row footer">

                    <div>

                        <p>Email : laundrymiddleplatform@gmail.com</p>
                        <p>Address: Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh</p>
                        <h5>&copy; Copyright 2023. Laundry Middle Platform</h5>
                    </div>

                </div>

            </div>
            <script>
                function validateForm() {
                    var servicePriceInput = document.getElementById("servicePrice");
                    var servicePrice = parseFloat(servicePriceInput.value);

                    if (isNaN(servicePrice) || servicePrice < 0 || servicePrice > 40000) {
                        alert("Giá của dịch vụ phải là số không âm và không quá 40,000 VNĐ.");
                        return false;
                    }

                    if (/[^0-9.]/.test(servicePriceInput.value)) {
                        alert("Giá của dịch vụ chỉ được nhập số");
                        servicePriceInput.value = '';
                        return false;
                    }

                    return true; 
                }
            </script>

            <script src="js/Jquery.js"></script>
            <script src="js/bootstrap.min.js"></script>
    </body>

</html>