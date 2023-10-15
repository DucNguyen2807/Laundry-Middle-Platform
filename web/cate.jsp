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
                            <ul class="nav navbar-nav" >

                                <li class="active"> <a href="homepage1.html" > <span class="glyphicon glyphicon-home"></span>
                                        Home</a> </li>

                                <li><a href="#" style="color: #ffffff">Home</a></li>
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

                        </div><!-- /.navbar-collapse -->
                    </div><!-- /.container-fluid -->
                </nav>

            </div>
        </div>
        <div class="container mt-5">
            <div class="row" >
                <c:forEach items="${SEARCHRESULT}" var="cat" varStatus="loop">
                    <%-- Hiển thị cửa hàng nếu nó nằm trong phạm vi hiển thị của trang --%>
                    <c:if test="${loop.index % 6 == 0}">
                        <div class="clearfix"></div> <%-- Bắt đầu một hàng mới --%>
                    </c:if>

                    <div   class="product col-12 col-md-6 col-lg-4">
                        <div style="margin:25px 20px " class="card-body text-center vertical-center">
                            <div class="card-body">
                                <img class="card-img-top" src="<c:out value='${cat.image}'/>" alt="Store Image" style="width: 100%; height: auto;">

                                <h4 class="card-title show_txt">Store Name: <c:out value='${cat.storeName}'/></h4>
                                <p class="card-text show_txt">Address: <c:out value='${cat.address}'/></p>
                                <div class="rating">
                                    <c:forEach begin="1" end="${cat.rating}">
                                        <i class="fas fa-star"></i>
                                    </c:forEach>
                                </div>
                                <p class="card-text show_txt">Price: <c:out value='${cat.price}'/> vnđ</p>
                                <a href="#" class="btn btn-success btn-block">Add to cart</a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <c:choose>
            <c:when test="${empty SEARCHRESULT}">
                <div class="alert alert-warning mt-3" role="alert">No results found!</div>
            </c:when>
        </c:choose>
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