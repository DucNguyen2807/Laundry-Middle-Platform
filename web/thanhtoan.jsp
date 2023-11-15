<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Thanh toan</title>
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
                            </ul>

                        </div>
                    </div><!-- /.container-fluid -->
                </nav>
            </div>

            <div class="container mt-5">

                <div class="row">
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success">
                            <strong>Thông báo:</strong> <c:out value="${successMessage}" />
                        </div>
                    </c:if>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                            <strong>Lỗi:</strong> <c:out value="${errorMessage}" />
                        </div>
                    </c:if>
                    <div class="col-md-6">
                        <div class="customer-details">
                            <c:choose>
                                <c:when test="${not empty storeSale}">
                                    <c:forEach items="${storeSale}" var="store">
                                        <div class="text-center">
                                            <img src="${store.imageDetail}" alt="Cửa hàng" alt="Store Image" style="width: 83%; height: auto;" />
                                        </div>
                                    </c:forEach>
                                </c:when>
                            </c:choose>

                            <c:forEach items="${storeSale}" var="store">
                                <div class="card-body">
                                    <p>Tên cửa hàng: ${store.storeName.toUpperCase()}</p>
                                    <p>Địa chỉ: ${store.address}</p>
                                    <div class="rating">
                                        Rating:
                                        <c:forEach begin="1" end="${store.averageRating}">
                                            <span class="star yellow">★</span>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>   

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

                    <div>
                        <div class="col-md-6">

                            <div class="customer-details mx-auto">
                                <h2>Thông tin khách hàng</h2>
                                <form action="ConfirmOrderController" method="post" accept-charset="UTF-8">
                                    <label for="fullname">Tên khách hàng:</label>
                                    <input type="text" class="bg-light form-control" name="fullname" value="${user.fullname}" readonly>
                                    <c:if test="${not empty errorMessage}">
                                        <div class="alert alert-danger">
                                            <strong>Lỗi:</strong> <c:out value="${errorMessage}" />
                                        </div>
                                    </c:if>
                                    <label for="phone">Số điện thoại:</label>
                                    <input type="tel" id="phone" name="phone" value="0${user.phone}" required><br>
                                    <c:if test="${not empty errorMessage3}">
                                        <div class="alert alert-danger">
                                            <strong>Lỗi:</strong> <c:out value="${errorMessage3}" />
                                        </div>
                                    </c:if>
                                    <label for="customerAddress">Địa chỉ:</label>
                                    <input type="text" id="customerAddress" name="customerAddress" value="${user.address}" required><br>

                                    <label for="kilos">Số kg (từ 1 đến 20):</label>
                                    <input type="number" id="kilos" name="kilos" required min="1" max="20" oninput="checkMaxValue(this)">

                                    <label for="selectedService">Dịch vụ và giá cả:</label>
                                    <select id="selectedService" name="selectedService">
                                        <c:forEach items="${storeSale}" var="store">
                                            <c:forEach items="${storePrice}" var="price">
                                                <c:if test="${price.storeID eq store.storeID}">
                                                    <option value="${price.serviceID}" data-price="${price.price}">
                                                        ${price.serviceDetail} : ${price.price} /1kg
                                                    </option>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </select>

                                    <c:forEach items="${storeSale}" var="store">
                                        <input type="hidden" name="storeID" value="${store.storeID}" />
                                        <input type="hidden" name="storeAddress" value="${store.address}" />
                                    </c:forEach>


                                    <label for="note">Ghi chú:</label>
                                    <textarea id="note" name="note" rows="4"></textarea><br>
                                    <div style="display: flex; align-items: center;">
                                        <div>
                                            <label for="time">Chọn thời gian:</label>
                                            <input type="time" id="time" name="time" required>
                                        </div>
                                        <div style="margin-left: 50px;">

                                            <label for="date">Chọn ngày:</label>
                                            <input type="date" id="date" name="date" required>
                                        </div>
                                    </div>
                                    <br>

                                    <input type="hidden" name="totalPrice" id="totalPrice" value="0">
                                    <p>Tổng tiền cần thanh toán: <span id="totalAmount">0 VNĐ</span></p>                       
                                    <br>
                                    <button type="submit" name="btAction" value="ConfirmOrder">Xác nhận đặt hàng</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                function calculateTotalAmount() {
                    var selectedService = document.getElementById("selectedService");
                    var selectedOption = selectedService.options[selectedService.selectedIndex];
                    var price = parseFloat(selectedOption.getAttribute("data-price"));
                    var kilos = parseInt(document.getElementById("kilos").value);

                    if (isNaN(kilos) || kilos <= 0) {
                        document.getElementById("totalAmount").textContent = "0 VNĐ";
                        document.getElementById("totalPrice").value = "0";
                        return;
                    }

                    var totalAmount = price * kilos;

                    // Loại bỏ "VNĐ" trước khi hiển thị
                    var formattedTotalAmount = totalAmount.toFixed(3).replace(".", ",");

                    document.getElementById("totalAmount").textContent = formattedTotalAmount + " VNĐ";
                    document.getElementById("totalPrice").value = formattedTotalAmount;
                }


                function checkMaxValue(input) {
                    if (input.value > 20 || input.value === "0") {
                        input.value = 20;
                    }
                }

                document.getElementById("selectedService").addEventListener("change", calculateTotalAmount);
                document.getElementById("kilos").addEventListener("input", calculateTotalAmount);
                calculateTotalAmount();

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
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


            <script src="js/Jquery.js"></script>
            <script src="js/bootstrap.min.js"></script>

    </body>

</html>