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
        <!--        <script>
                    var urlParams = new URLSearchParams(window.location.search);
                    var successMessage = urlParams.get("successMessage");
        
                    if (successMessage) {
                        alert(successMessage);
                    }
                </script>-->
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
        </div>
        <!--K?t thúc container-fluid-->
        <form action="MainController" method="POST" accept-charset="UTF-8">


            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-4">
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
                                                <button type="submit" id="submitButton" class="btn btn-primary" onclick="return alert('Thay đổi ảnh thành công!')">Change photo</button>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <p>Không có ảnh cửa hàng nào.</p>
                                    <label for="imageURL">Đường dẫn URL ảnh:</label>
                                    <input type="text" id="imageURL" name="imageURL" required>
                                    <input type="hidden" name="btAction" value="ChangePhoto">
                                    <button type="submit" id="submitButton" class="btn btn-primary" onclick="return alert('Thay đổi ảnh thành công!')">Change photo</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>  
                    

                    
                    <div class="col-md-4">
                        <p>Thông tin cửa hàng</p>
                        <c:forEach items="${storeSale}" var="store">
                            <div class="card-body">
                                Tên cửa hàng:<input type="text" name="nameStore"  value="${store.storeName.toUpperCase()}"><br>

                                Địa chỉ: <input type="text" name="addressStore" value="${store.address}" >
                                <p>Dịch vụ:</p>
                                <c:forEach items="${storePrice}" var="price">
                                    <c:if test="${price.storeID eq store.storeID}">
                                        <p>${price.serviceDetail}:</p>
                                        <input name ="priceU" value="${price.price}" id="priceU" oninput="validateInput(this)" đ >
                                        <input type="hidden" name="serviceID" value="${price.serviceID}" />
                                        <input type="hidden" name="btAction" value="DeleteServiceStore" />
                                        <button type="submit" class="btn btn-danger" onclick="return alert('Bạn có muốn xóa dịch vụ này không?')">Delete</button>
                                        <script>
                                            function validateInput(inputField) {
                                                inputField.value = inputField.value.replace(/[^0-9]/g, '');
                                                if (inputField.value === "") {
                                                    alert("Giá trị nhập vào phải là số từ 0 đến 9.");
                                                    document.getElementById("submitButton").disabled = true;
                                                } else {
                                                    document.getElementById("submitButton").disabled = false;
                                                }
                                            }
                                        </script>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <!--                                      <button type="submit" value="UpdatePriceStore" name="btAction">Update</button>-->
                            <input type="hidden" name="btAction" value="UpdatePriceStore" />
                            <button type="submit" id="submitButton" class="btn btn-primary" onclick="return alert('Cập nhật thành công!')">Update</button>
                        </c:forEach> 
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
                                <c:forEach items="${storeSale}" var="store">
                                    <input type="hidden" name="storeID" value="${store.storeID}" />
                                </c:forEach>
                                <button type="submit">Add Service</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>