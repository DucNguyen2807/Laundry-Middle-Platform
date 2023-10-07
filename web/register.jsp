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
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="css/register.css"/>
    </head>
    <body>
        <div class="register-page">
            <div class="container">
                <div class="thumnail">
                    <img src="image/thumnail.png"  alt="thumnail"></img>
                </div>
                <div class="form">
                    <div class="form-register">
                        <h1>WELCOME</h1>
                        <h2>Sign up to</h2>
                        <h5>Laudry middle platform</h5>

                        <form class="form-register-content" action="MainController" method="POST">

                            <td>
                                <label for="username" >User name</label><br>
                                <input class="text" type="text" name="username" value="" required>
                                <br>
                            <c:if test="${not empty requestScope.INSERTERROR}">
                                <c:if test="${not empty requestScope.INSERTERROR.usernameDuplicateErr}">
                                    <font color="red">
                                    ${requestScope.INSERTERROR.usernameDuplicateErr}<br/>
                                    </font>
                                </c:if>
                            </c:if>
                            </td>

                            <td>
                                <label for="password">Password</label><br>
                                <input class="text" type="password" name="password" value="" required>
                                <br>
                            <c:if test="${not empty requestScope.INSERTERROR}">
                                <c:if test="${not empty requestScope.INSERTERROR.passwordLengthErr}">
                                    <font color="red">
                                    ${requestScope.INSERTERROR.passwordLengthErr}<br/>
                                    </font>
                                </c:if>
                            </c:if>
                            </td>

                            <td>
                                <label for="confirm">Confirm password</label><br>
                                <input class="text" type="password" name="cfpassword" value="" required>
                                <br>
                            <c:if test="${not empty requestScope.INSERTERROR}">
                                <c:if test="${not empty requestScope.INSERTERROR.confirmNotMatch}">
                                    <font color="red">
                                    ${requestScope.INSERTERROR.confirmNotMatch}<br/>
                                    </font>
                                </c:if>
                            </c:if>
                            </td>
                            
                            <td>
                                <label for="fullname">Fullname</label><br>
                            <input class="text" type="text" name="fname" value="" required>
                            <br>
                            <c:if test="${not empty requestScope.INSERTERROR}">
                                <c:if test="${not empty requestScope.INSERTERROR.fullNameLengthErr}">
                                    <font color="red">
                                    ${requestScope.INSERTERROR.fullNameLengthErr}<br/>
                                    </font>
                                </c:if>
                            </c:if>
                            </td>
                            
                            <td>
                                <label for="phone">Phone number</label><br>
                            <input class="text" type="text" name="phone" value="" required>
                            <br>
                            <c:if test="${not empty requestScope.INSERTERROR}">
                                <c:if test="${not empty requestScope.INSERTERROR.phoneDuplicateErr}">
                                    <font color="red">
                                    ${requestScope.INSERTERROR.phoneDuplicateErr}<br/>
                                    </font>
                                </c:if>
                            </c:if>
                            </td>
                            
                            <td>
                                <label for="role">
                                <input type="radio" name="roleid" value="1"> Customer
                                <input type="radio" name="roleid" value="2"> Store
                            </label>
                            </td>   
                            <br>
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

