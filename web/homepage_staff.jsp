<%-- 
    Document   : homepage_store
    Created on : Oct 8, 2023, 2:29:13 PM
    Author     : ducnx
--%>

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
                                <li class="active"><a href="homepage_staff.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                                <li><a href="updateacc.jsp" style="color: #ffffff">Profile</a></li>
                                <li><a href="list_task.jsp" style="color: #ffffff">Task List</a></li>               
                            </ul>
                            <ul class="nav navbar-nav navbar-right">
                                <li><a href="login.jsp" style="color: #ffffff">Logout</a></li>
                            </ul>
                        </div>
                    </div><!-- /.container-fluid -->
                </nav>
            </div>


            <div class="row anh1">
                <div class="col-md-12">
                    <img src="image/banner.jpg" />
                </div>
            </div>

        </div>
        <!--K?t thúc container-fluid-->

        <div class="container mybody">
            <div class="row task1">	

                <div class="row combo">

                    <div class="col-md-12">

                        <h2>Quy trình 4 bước</h2>
                        <p>During the promotion period, let's wash your clothes!</p>
                    </div>

                </div>

                <div class="col-md-3">
                    <div class="rounded-image">
                        <img src="image/b1.png" />
                        <div class="relative">
                            <h3>Đặt dịch vụ</h3>
                            <p>Khách hàng sẽ đặt dịch vụ trên các cửa hàng</p>
                        </div>

                    </div>
                </div>

                <div class="col-md-3">
                    <div class="rounded-image">
                        <img src="image/b2.png" />
                        <div class="relative">
                            <h3>Nhận đồ từ khách hàng</h3>
                            <p>Nhân viên sẽ đi giao tới cho cửa hàng</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="rounded-image">
                        <img src="image/b3.png" />
                        <div class="relative">
                            <h3>Giặt và làm sạch</h3>
                            <p>Cửa hàng sẽ giặt và làm theo yêu cầu</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="rounded-image">
                        <img src="image/b4.png" />
                        <div class="relative">
                            <h3>Trả đồ cho khách hàng</h3>
                            <p>Nhân viên sẽ giao trả hàng lại cho khách hàng</p>

                        </div>
                    </div>
                </div>
            </div>
            <br>    
            <div class="row orderonline">

                <div class="col-md-12">
                    <h2>The best Laundry Middle Platform</h2>
                    <p style="color: darkcyan">No more dragging your laundry to the laundromat! With Cleaner, you can have your laundry picked up and delivered to your door.
                        We offer a variety of convenient pickup and delivery times to fit your schedule.</p>
                </div>

            </div>

            <div class="row order">

                <div class="col-md-6">

                    <div>

                        <img id="image1" src="image/logo.png" />

                    </div>

                </div>

                <div>
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d582.5042382897573!2d106.80972000195557!3d10.841448543525589!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752731176b07b1%3A0xb752b24b379bae5e!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBGUFQgVFAuIEhDTQ!5e0!3m2!1svi!2s!4v1698773108161!5m2!1svi!2s" width="570" height="431.08" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>




            </div>



        </div>

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
