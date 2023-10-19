<%-- Import các thư viện JSTL cần thiết --%>
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
                                <li class="active"> <a href="homepage_admin.jsp" > <span class="glyphicon glyphicon-home"></span>
                                        Home</a> </li>
                                <li><a href="updateacc.jsp" style="color: #ffffff">Profile</a></li>
                                <li><a href="admin.jsp" style="color: #ffffff">Manage</a></li>
                                <li> <a href="contact.html" style="color: #ffffff"><span class="glyphicon glyphicon-envelope" ></span> Contact</a>
                                </li>
                            </ul>
                            <form class="navbar-form navbar-right" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                                <button type="submit" class="btn btn-default">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
            </div>
        </div>

        <label for="orderBy">Sắp xếp theo:</label>
        <select id="orderBy" name="orderBy">
            <option value="favoriteCount">Yêu thích</option>
            <option value="rating">Rating</option>
            <option value="review">Review</option>
        </select>
        <button type="submit">Sắp xếp</button>


        <div class="container mt-5">
            <div class="row">
                <c:forEach items="${pagedStores}" var="cat">
                    <div class="product col-12 col-md-6 col-lg-4">
                        <div style="margin: 25px 20px" class="card-body text-center vertical-center">
                            <div class="card-body">
                                <img class="card-img-top" src="<c:out value='${cat.image}'/>" alt="Store Image" style="width: 100%; height: auto;">
                                <h4 class="card-title show_txt">Tên cửa hàng: <c:out value='${cat.storeName}'/></h4>
                                <p class="card-text show_txt">Địa chỉ: <c:out value='${cat.address}'/></p>

                                <div class="rating">
                                    <c:choose>
                                        <c:when test="${cat.rating == 5}">
                                            <c:forEach begin="1" end="5">
                                                <span class="star yellow">★</span>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach begin="1" end="4">
                                                <span class="star yellow">★</span>
                                            </c:forEach>
                                            <span class="star black">★</span>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <p class="card-text show_txt">Giặt thường: <c:out value='${cat.priceGiatThuong}'/> vnđ</p>
                            <a href="#" class="btn btn-success btn-block" onclick="saveCatInfo(
                                            '<c:out value='${cat.image}' />',
                                            '<c:out value='${cat.priceGiatThuong}' />',
                                            '<c:out value='${cat.priceGiatNhanh}' />',
                                            '<c:out value='${cat.priceGiatSieuToc}' />',
                                            '<c:out value='${cat.storeName}' />',
                                            '<c:out value='${cat.address}' />',
                                            '<c:out value='${cat.storeID}' />',
                                            '<c:out value='${cat.rating}' />'
                                            )">Booking</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <script>
            function saveCatInfo(image, priceGiatThuong, priceGiatNhanh, priceGiatSieuToc, storeName, address, storeID, rating) {
                var session = sessionStorage;
                session.setItem("catImage", image);
                session.setItem("catPriceGiatThuong", priceGiatThuong);
                session.setItem("catPriceGiatNhanh", priceGiatNhanh);
                session.setItem("catPriceGiatSieuToc", priceGiatSieuToc);
                session.setItem("catStoreName", storeName);
                session.setItem("catAddress", address);
                session.setItem("catStoreID", storeID);
                session.setItem("catRating", rating);   
                    window.location.href = "thanhtoan.jsp";
            }
        </script>







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




        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <div class="container-fluid" >
            <div class="row footer">
                <div>
                    <p>Email : Tiennvse171676</p>
                    <p>Address: 100 Vuon Lai, Tan Phu District, HCMC</p>
                    <h5>&copy; Copyright 2023. Laundry Middle Platform</h5>
                </div>
            </div>  
        </div>
        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
