<%-- 
    Document   : landingpage
    Created on : Oct 5, 2023, 2:45:47 AM
    Author     : khait
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="views/css/login.css"/>
    </head>
    <body>
        <div class="login-page">
            <div class="container">
                <div class="thumnail">
                    <img src="views/image/thumnail.png"  alt="thumnail"></img>
                </div>
                <div class="form">
                    <div class="form-login">
                        <h1>WELCOME</h1>
                        <h2>Sign in to</h2>
                        <h5>Laudry middle platform</h5>
                        
                        <form class="form-login-content" action="MainController" method="POST">
                            <label for="username" >User name</label><br>
                            <input class="text" type="text" name="txtUsername" value="" required><br>
                            <br>
                            <label for="password">Password</label><br>
                            <input class="text" type="password" name="txtPassword" value="" required><br>
                            <br>
                            
                            <input type="checkbox" name="" value="1">Remember me
                            <a href="url" style="text-decoration: none; margin-left: 40px; color:black">Forgot password?</a></li><br>
                            <br>
                            <label style="color: red">${ERROR}</label><br>
                            <br>
                            <input class="btn-login" type="submit" value="Login"  name="btAction"/><br>
                            
                            <a href="url" class="btn-login-gg"> Đăng nhập với Google </a><br><br>
                            
                            <a href="register.jsp" style="text-decoration: none; margin-left: 30px; color: darkgrey">
                                Don't have an account? <b style="color: black">Register</b>
                            </a>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
