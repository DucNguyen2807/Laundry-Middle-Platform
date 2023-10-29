<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Chi tiết cửa hàng</title>
        <link rel="stylesheet" href="css/thanhtoan.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
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
                        <p>Thông tin cửa hàng</p>

                        <c:forEach items="${storeSale}" var="store">
                            <div class="card-body">
                                <p>Tên cửa hàng: ${store.storeName.toUpperCase()}</p>
                                <p>Địa chỉ: ${store.address}</p>
                                <p>Dịch vụ:</p>
                                <c:forEach items="${storePrice}" var="price">
                                    <c:if test="${price.storeID eq store.storeID}">
                                         <p>${price.serviceDetail} : ${price.price} VNĐ</p>
                                    </c:if>
                                </c:forEach>

                                <div class="rating">
                                    Rating:
                                    <c:forEach begin="1" end="${store.averageRating}">
                                        <span class="star yellow">★</span>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>       
                    </div>
                </div>

                <script>

                    function saveCatInfo(storeName, address, giatthuong, giatnhanh, giatsieutoc, storeID) {
                        var session = sessionStorage;
                        session.setItem("storeStoreName", storeName);
                        session.setItem("storeAddress", address);
                        session.setItem("storeGiatthuong", giatthuong);
                        session.setItem("storeGiatnhanh", giatnhanh);
                        session.setItem("storeGiatsieutoc", giatsieutoc);
                        session.setItem("storeStoreID", storeID);
                        window.location.href = "thanhtoan.jsp";
                    }

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

    </body>
</html>
