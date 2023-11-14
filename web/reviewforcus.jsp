<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Review</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/styleindex.css" rel="stylesheet" >
        <link href="css/cate.css" rel="stylesheet" >
        <link href="css/thanhtoan.css" rel="stylesheet" >
        <link href="css/rating.css" rel="stylesheet" >


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
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="customer-details">
                            <c:choose>
                                <c:when test="${not empty storeSale}">
                                    <c:forEach items="${storeSale}" var="store">
                                        <div class="text-center">
                                            <img src="${store.imageDetail}" alt="Cửa hàng" alt="Store Image" style="width: 75%; height: auto;" />
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
                        </div>

                        

                            
                        <div class="customer-details">
                            <form action="InsertReviewController" method="post">
                            <h4>Đánh giá và Bình luận</h4>
                            
                            <div class="form-group">
                                <label for="rating2">Đánh giá sao:</label>
                                <div class="rating2">
                                    <input type="checkbox" id="star52" name="rating2" value="5" />
                                    <label for="star52" title="5 sao">★</label>

                                    <input type="checkbox" id="star42" name="rating2" value="4" />
                                    <label for="star42" title="4 sao">★</label>

                                    <input type="checkbox" id="star32" name="rating2" value="3" />
                                    <label for="star32" title="3 sao">★</label>

                                    <input type="checkbox" id="star22" name="rating2" value="2" />
                                    <label for="star22" title="2 sao">★</label>

                                    <input type="checkbox" id="star12" name="rating2" value="1" />
                                    <label for="star12" title="1 sao">★</label>
                                </div>
                            </div>
                            <c:forEach items="${storeSale}" var="store">
                            <input type="hidden" name="storeID" value="${store.storeID}" />
                        </c:forEach>
                            <div class="form-group">
                                <label for="comment">Bình luận:</label>
                                <textarea class="form-control" id="comment" name="comment" rows="3"></textarea>
                            </div>

                            <button type="submit" class="btn btn-primary">Gửi đánh giá</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-3"></div>
                </div>
            </div>
            <script>
                document.querySelectorAll('.rating2 input').forEach(function (checkbox) {
                    checkbox.addEventListener('change', function () {

                        var labelsToHighlight = Array.from(this.parentElement.children).slice(-this.value);

                        document.querySelectorAll('.rating2 label').forEach(function (label) {
                            label.classList.remove('selected');
                        });
                        labelsToHighlight.forEach(function (label) {
                            label.classList.add('selected');
                        });
                    });
                });
            </script>


            <script src="js/Jquery.js"></script>
            <script src="js/bootstrap.min.js"></script>

    </body>

</html>