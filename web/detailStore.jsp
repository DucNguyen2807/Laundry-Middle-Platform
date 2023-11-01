<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Chi tiết cửa hàng</title>
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
                                <li class="active"><a href="homepage_customer.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                                <li><a href="updateacc.jsp" style="color: #ffffff">Profile</a></li>
                                <li><a href="contact.html" style="color: #ffffff"><span class="glyphicon glyphicon-envelope"></span> Contact</a></li>

                            </ul>
                            
                        </div>
                    </div><!-- /.container-fluid -->
                </nav>
            </div>
            
            
            
            <div class="container mt-5">
            <div class="row">
                <div class="col-md-4">
                    <div class="customer-details">
                        <c:choose>
                            <c:when test="${not empty storeSale}">
                                <c:forEach items="${storeSale}" var="store">
                                    <div><img src="${store.imageDetail}" alt="Cửa hàng" alt="Store Image" style="width: 100%; height: auto;" /></div>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                    </div>
                    <div class="customer-details">
                        <div class="reviews-section">
                            <p>Tổng số lượt đánh giá: ${allReviews.size()}</p>
                            <div id="reviewsContainer">
                                <c:forEach items="${allReviews}" var="review">

                                    <div class="review">
                                        <p>Customer Name: ${review.customerName.toUpperCase()}</p>
                                        <p>Review Text: ${review.reviewText}</p>
                                        <div class="rating">
                                            Rating:
                                            <c:forEach begin="1" end="${review.rating}">
                                                <span class="star yellow">★</span>
                                            </c:forEach>
                                        </div>
                                    </div>

                                </c:forEach>
                            </div>
                            <button id="prevReview">Previous</button>
                            <button id="nextReview">Next</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="store-details">
                        <form action="BeforeThanhToanController" method="post">
                            <p>Thông tin cửa hàng</p>

                            <c:forEach items="${storeSale}" var="store">
                                <div class="card-body">
                                    <p>Tên cửa hàng: ${store.storeName.toUpperCase()}</p>
                                    <p>Địa chỉ: ${store.address}</p>
                                    <p>Dịch vụ:</p>
                                    <c:forEach items="${storePrice}" var="price">
                                        <c:if test="${price.storeID eq store.storeID}">
                                            <p>${price.serviceDetail} : ${price.price} đ</p>
                                            <input type="hidden" name="serviceID" value="${price.serviceID}" />
                                        </c:if>
                                    </c:forEach>



                                    <div class="rating">
                                        Rating:
                                        <c:forEach begin="1" end="${store.averageRating}">
                                            <span class="star yellow">★</span>
                                        </c:forEach>
                                    </div>
                                </div>
                                    <input type="hidden" name="storeID" value="${store.storeID}" />
                                <button type="submit">Thanh toán</button>
                            </c:forEach>   

                        </form>
                    </div>
                </div>

                <script>
                    var reviewIndex = 0;
                    var reviews = document.querySelectorAll('.review');
                    function showReview(index) {
                        for (var i = 0; i < reviews.length; i++) {
                            reviews[i].style.display = 'none';
                        }
                        reviews[index].style.display = 'block';
                    }
                    document.getElementById('prevReview').addEventListener('click', function () {
                        if (reviewIndex > 0) {
                            reviewIndex--;
                            showReview(reviewIndex);
                        }
                    });

                    document.getElementById('nextReview').addEventListener('click', function () {
                        if (reviewIndex < reviews.length - 1) {
                            reviewIndex++;
                            showReview(reviewIndex);
                        }
                    });

                    showReview(reviewIndex);
                </script>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


        <script src="js/Jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>

    </body>

</html>