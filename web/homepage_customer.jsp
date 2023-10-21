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
                                <li class="active"><a href="homepage_customer.jsp"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                                <li><a href="updateacc.jsp" style="color: #ffffff">Profile</a></li>
                                <li><a href="manage_admin.jsp" style="color: #ffffff">Manage</a></li>
                                <li><a href="contact.html" style="color: #ffffff"><span class="glyphicon glyphicon-envelope"></span> Contact</a></li>
                                <li>
                                    <form action="MainController" method="post">
                                        <input type="submit" value="Category" name="btAction" />
                                        <input type="submit" value="Favorite" name="btAction" />
                                    </form>  
                                </li>
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

            <div class="row">

                <div class="col-md-12">

                    <h2>Services</h2>

                </div>

            </div>

            <div class="row sanpham">

                <div class="col-md-3 ">

                    <div id="sanpham1">

                        <img src="image/b1.jpg" />
                        <p id="first_line1">COMBO laundry 3 get 1 free</p>
                        <p>Discount locations:<span style="font-weight: bold;margin-left: 10px;">Tan Phu District
                                Branch</span> </p>
                        <p>Price: <del> 200.000 vnd </del> <span style="color: #F72B2F; margin-left:10px; ">150.000
                                vn?</span></p>


                    </div>

                </div>

                <div class="col-md-3">

                    <div id="sanpham2">

                        <img src="image/b2.png" />
                        <p id="first_line2">COMBO laundry 3 get 1 free</p>
                        <p>Discount locations:<span style="font-weight: bold;margin-left: 10px;">Tan Phu District
                                Branch</span> </p>
                        <p>Price: <del>500.000 vnd </del> <span style="color: #F72B2F; margin-left:10px; ">450.000
                                vn?</span></p>


                    </div>

                </div>

                <div class="col-md-3">

                    <div id="sanpham3">

                        <img src="image/b3.jpg"/>
                        <p id="first_line3">COMBO laundry 3 get 1 free</p>
                        <p>Discount locations:<span style="font-weight: bold;margin-left: 10px;">Tan Phu District
                                Branch</span> </p>
                        <p>Price: <del>150.000 vnd </del> <span style="color: #F72B2F; margin-left:10px; ">100.000
                                vn?</span></p>


                    </div>

                </div>
                <div class="col-md-3">

                    <div id="sanpham3">

                        <img src="image/b4.jpg" />
                        <p id="first_line3">COMBO laundry 3 get 1 free</p>
                        <p>Discount locations:<span style="font-weight: bold;margin-left: 10px;">Tan Phu District
                                Branch</span> </p>
                        <p>Price: <del>150.000 vnd </del> <span style="color: #F72B2F; margin-left:10px; ">100.000
                                vn?</span></p>


                    </div>

                </div>



            </div>

            <div class="row task1">	

                <div class="row combo">

                    <div class="col-md-12">

                        <h2>Quy trinh 4 buoc</h2>
                        <p>During the promotion period, let's wash your shoes!</p>
                    </div>

                </div>

                <div class="col-md-3">
                    <div>
                        <img src="image/service.jpg" />
                        <div class="relative">
                            <h3>Cheap Shoe Washing</h3>
                            <p>Perfect quality</p>

                        </div>

                    </div>


                </div>

                <div class="col-md-3">
                    <div>
                        <img src="image/service.jpg" />
                        <div class="relative">
                            <h3>Cheap Repaint</h3>
                            <p>The best quality</p>

                        </div>

                    </div>

                </div>

                <div class="col-md-3">
                    <div >
                        <img src="image/service.jpg" />
                        <div class="relative">
                            <h3>Cheap shoe sole stickers</h3>
                            <p>Absolute shoe sole protection</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div >

                        <img src="image/service.jpg" />
                        <div class="relative">
                            <h3>Glue Shoes</h3>
                            <p>Reliable quality</p>
                        </div>
                    </div>
                </div>
            </div>




            <div class="row orderonline">

                <div class="col-md-12">

                    <h2>SPECIAL ORDER ONLINE</h2>
                    <p>Surprisingly cheap and freeship</p>
                </div>

            </div>

            <div class="row order">

                <div class="col-md-6">

                    <div>

                        <img id="image1" src="image/logo.png" />

                    </div>

                </div>

                <div class="col-md-3">

                    <div id="class2">

                        <img id="image2" src="image/sale20.png"  />
                        <p id="first_one1">Order online 20% off</p>
                        <p>Discount locations:<span style="font-weight: bold;margin-left: 10px;">Tan Phu District
                                Branch</span> </p>
                        <p><del>100% </del> <span style="color: #F72B2F; margin-left:10px; ">80%</span></p>


                    </div>

                </div>

                <div class="col-md-3">

                    <div id="class3">

                        <img id="image3" src="image/camau.jpg" />
                        <p id="first_one2">Free shipping under 3km</p>
                        <p>Discount locations:<span style="font-weight: bold;margin-left: 10px;">Tan Phu District
                                Branch</span> </p>
                        <p><del>60.000 vn? </del> <span style="color: #F72B2F; margin-left:10px; ">0 vn?</span></p>


                    </div>

                </div>


            </div>



        </div>

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