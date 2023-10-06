<%-- 
    Document   : register
    Created on : Oct 6, 2023, 1:43:38 PM
    Author     : khait
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="views/css/register.css"/>
    </head>
    <body>
        <div class="register-page">
            <div class="container">
                <div class="thumnail">
                    <img src="views/image/thumnail.png"  alt="thumnail"></img>
                </div>
                <div class="form">
                    <div class="form-register">
                        <h1>WELCOME</h1>
                        <h2>Sign up to</h2>
                        <h5>Laudry middle platform</h5>

                        <form class="form-register-content" action="MainController" method="POST">
                            <label for="username" >User name</label><br>
                            <input class="text" type="text" name="txtUsername" value="" required><br>
                            <br>
                            <label for="password">Password</label><br>
                            <input class="text" type="password" name="txtPassword" value="" required><br>
                            <br>
                            <label for="confirm">Confirm password</label><br>
                            <input class="text" type="password" name="txtCofirm" value="" required><br>
                            <br>
                            <label for="fullname">Fullname</label><br>
                            <input class="text" type="text" name="txtFullname" value="" required><br>
                            <br>
                            <label for="phone">Phone number</label><br>
                            <input class="text" type="text" name="txtphone" value="" required><br>
                            <br>
                            <label for="role">
                                <input type="radio" name="roleid" value="1"> Customer
                                <input type="radio" name="roleid" value="2"> Staff
                                <input type="radio" name="roleid" value="3"> Store<br>
                            </label>

                            <label style="color: red">${ERROR}</label><br>
                            <br>
                            <input class="btn-register" type="submit" value="Register"  name="btAction"/><br>

                            <a href="url" class="btn-register-gg">Đăng kí với Google</a><br><br>

                            <a href="login.jsp" style="text-decoration: none; margin-left: 40px; color: darkgrey">
                                Already have an account? <b style="color: black">Login</b>
                            </a>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>

