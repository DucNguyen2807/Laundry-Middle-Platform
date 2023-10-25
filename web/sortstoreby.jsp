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
                                <li> <a href="contact.html" style="color: #ffffff"><span class="glyphicon glyphicon-envelope" ></span> Contact</a>
                                </li>
                            </ul>

                        </div>
                    </div>
                </nav>
            </div>
        </div>


        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <form action="SortStoreByController" method="post" class="custom-select-container">
                    <label for="btAction" class="navbar-brand">Sắp xếp theo:</label>
                    <div class="custom-select">
                        <select style="font-size: 20px" name="btAction" id="btAction">
                            <option value="Nearest">Các cửa hàng gần nhất</option>
                            <option value="favoriteCount">Nhiều lượt yêu thích nhất</option>
                            <option value="rating">Nhiều lượt rating nhất</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Sắp xếp</button>
                    <input type="hidden" name="orderBy" />
                </form>
            </div>
        </nav>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                // Lắng nghe sự kiện khi lựa chọn trong dropdown thay đổi
                $("select[name='btAction']").change(function () {
                    var selectedValue = $(this).val();
                    // Cập nhật giá trị cho trường input ẩn
                    $("input[name='orderBy']").val(selectedValue);
                });

                // Kiểm tra giá trị của trường "orderBy" và chọn lại option tương ứng
                var selectedOrderBy = $("input[name='orderBy']").val();
                $("select[name='btAction']").val(selectedOrderBy);
            });
        </script>

        <div class="container mt-5">
            <div class="row">
                <c:forEach items="${pagedStores}" var="cat">
                    <div class="product col-12 col-md-6 col-lg-4">
                        <div style="margin: 25px 20px" class="card-body text-center vertical-center">
                            <div class="card-body">
                                <i class="fas fa-heart heart-icon" onclick="addToFavorites('<c:out value="${cat.storeID}"/>')"></i>
                                <img class="card-img-top" src="<c:out value='${cat.image}'/>" alt="Store Image" style="width: 100%; height: auto;">
                                <h4 class="card-title show_txt">Tên cửa hàng: <c:out value='${cat.storeName}'/></h4>
                                <p class="card-text show_txt">Địa chỉ: <c:out value='${cat.address}'/></p>

<div class="rating">
    <c:forEach begin="1" end="${cat.rating}">
        <span class="star yellow">★</span>
    </c:forEach>
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

            function addToFavorites(storeID) {
                // Gửi yêu cầu POST đến máy chủ để thêm cửa hàng vào danh sách yêu thích
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/Laundry_mp/AddFavoriteController", // Thay đổi đường dẫn tới endpoint của bạn
                    data: {storeID: storeID},
                    success: function (response) {
                        // Xử lý phản hồi từ máy chủ (nếu cần)
                        alert("Đã thêm cửa hàng vào danh sách yêu thích!");
                    },
                    error: function (error) {
                        // Xử lý lỗi (nếu có)
                        alert("Có lỗi xảy ra khi thêm cửa hàng vào danh sách yêu thích.");
                    }
                });
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
                    <a href="<c:url value='/SortStoreByController'/>?page=1&orderBy=${orderBy}" class="pagination-link">First</a>
                    <a href="<c:url value='/SortStoreByController'/>?page=${currentPage - 1}&orderBy=${orderBy}" class="pagination-link">Previous</a>
                </c:if>
                <c:if test="${currentPage < totalPages}">
                    <a href="<c:url value='/SortStoreByController'/>?page=${currentPage + 1}&orderBy=${orderBy}" class="pagination-link">Next</a>
                    <a href="<c:url value='/SortStoreByController'/>?page=${totalPages}&orderBy=${orderBy}" class="pagination-link">Last</a>
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