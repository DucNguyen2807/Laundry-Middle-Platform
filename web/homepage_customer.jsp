<%-- 
    Document   : homepage_customer
    Created on : Oct 8, 2023, 3:12:53 PM
    Author     : khait
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="css/homepagecus.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <header class="header">
            <nav class="menu">
                <a href="#">Profile</a>
                <a href="#">Order</a>
                <a href="#">Store</a>
            </nav>
            <section>
                <img class="logo"src="image/logo.png" alt="logo"/>
            </section>
            <nav class="logout">
                <a href="#">Log out</a>
            </nav>
        </header>
        
        <main>
                <div class="banner">
                    <img src="image/banner.jpg" alt="banner">
                </div>
            
            <h2>Sản phẩm mới</h2>
            <section class="store">
                <ul>
                    <li>
                        <a href="#">
                            <img src="image/product1.jpg" alt="product1">
                            <h3>Sản phẩm 1</h3>
                            <p>Mô tả sản phẩm 1</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img src="image/product2.jpg" alt="product2">
                            <h3>Sản phẩm 2</h3>
                            <p>Mô tả sản phẩm 2</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img src="image/product3.jpg" alt="product3">
                            <h3>Sản phẩm 3</h3>
                            <p>Mô tả sản phẩm 3</p>
                        </a>
                    </li>
                </ul>
            </section>
            
            <section class="promotions">
                <h2>Khuyến mãi</h2>
                <ul>
                    <li>
                        <a href="#">
                            <img src="image/promotion1.jpg" alt="promotion1">
                            <h3>Khuyến mãi 1</h3>
                            <p>Thông tin khuyến mãi 1</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img src="image/promotion2.jpg" alt="promotion2">
                            <h3>Khuyến mãi 2</h3>
                            <p>Thông tin khuyến mãi 2</p>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <img src="image/promotion3.jpg" alt="promotion3">
                            <h3>Khuyến mãi 3</h3>
                            <p>Thông tin khuyến mãi 3</p>
                        </a>
                    </li>
                </ul>
            </section>
        </main>
        <footer>
            Copyright &copy; 2023
        </footer>
    </body>
</html>
