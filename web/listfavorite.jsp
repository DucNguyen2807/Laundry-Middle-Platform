<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/5.0.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/styleindex.css" rel="stylesheet" >
        <link href="css/cate.css" rel="stylesheet" >
    </head>
    <body>
        <div class="container-fluid">
            <div class="row" style="background-color: #22638f"> 
                <nav class="navbar navbar-inverse bg-primary" role="navigation">
                    <div class="container-fluid">
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
                            <ul class="nav navbar-nav" >
                                <li class="active"> <a href="homepage_customer.jsp" > <span class="glyphicon glyphicon-home"></span>
                                        Home</a> </li>
                                <li><a href="updateacc.jsp" style="color: #ffffff">Profile</a></li>
                                </li>
                            </ul>

                        </div>
                    </div>
                </nav>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <div class="container mt-5">
            <div class="row">
                <c:forEach items="${pagedStores}" var="cat">

                    <div class="product col-12 col-md-6 col-lg-4">
                        <div style="margin: 25px 20px" class="card-body text-center vertical-center">
                            <div class="card-body">
                                <i class="fas fa-heart heart-icon" onclick="removeFavorites('<c:out value="${cat.storeID}"/>')"></i>
                                <img class="card-img-top" src="<c:out value='${cat.image}'/>" alt="Store Image" style="width: 350px; height: 350px;">
                                <h4 class="card-title show_txt">Tên cửa hàng: <c:out value='${cat.storeName}'/></h4>
                                <p class="card-text show_txt">Địa chỉ: <c:out value='${cat.address}'/></p>

                                <div class="rating">
                                    <c:forEach begin="1" end="${cat.rating}">
                                        <span class="star yellow">★</span>
                                    </c:forEach>
                                </div>
                                <p class="card-text show_txt">Giá chỉ từ: <c:out value="${cat.averagePrice}" /> đ/1kg</p>
                            </div>
                            <a href='<c:url value="/BeforeThanhToanController?storeID=${cat.storeID}" />'class="btn btn-success btn-block">Get more information</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <c:choose>
            <c:when test="${empty pagedStores}">
                <div class="alert alert-warning mt-3" role="alert">No results found!</div>
            </c:when>
        </c:choose>
        <div class="pagination-container">
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="<c:url value="/CateController"/>?page=1" class="pagination-link">First</a>
                    <a href="<c:url value="/CateController"/>?page=${currentPage - 1}" class="pagination-link">Previous</a>
                </c:if>
                <c:if test="${currentPage < totalPages}">
                    <a href="<c:url value="/CateController"/>?page=${currentPage + 1}" class="pagination-link">Next</a>
                    <a href="<c:url value="/CateController"/>?page=${totalPages}" class="pagination-link">Last</a>
                </c:if>
            </div>
        </div>
        <script>
            function removeFavorites(storeID) {

                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/Laundry_mp/RemoveFavoriteController",
                    data: {storeID: storeID},
                    success: function (response) {
                        alert("Đã xóa cửa hàng khỏi danh sách yêu thích!");
                        location.reload();
                    },
                    error: function (error) {
                        alert("Có lỗi xảy ra khi bỏ yêu thích cửa hàng khỏi danh sách yêu thích.");
                    }
                });
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <div class="container-fluid" >
            <div class="row footer">
                <div>
                    <p>Email : laundrymiddleplatform@gmail.com</p>
                    <p>Address: Lô E2a-7, Đường D1, Đ. D1, Long Thạnh Mỹ, Thành Phố Thủ Đức, Thành phố Hồ Chí Minh</p>
                    <h5>&copy; Copyright 2023. Laundry Middle Platform</h5>
                </div>
            </div>  
        </div>
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>
</html>
