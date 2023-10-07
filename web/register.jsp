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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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

                            <div>
                                <label for="username" >Username</label><br>
                                <input class="text" type="text" name="username" 
                                       value="<%= (request.getParameter("username") == null) ? "" : request.getParameter("username")%>" required>
                                <br>
                                <c:if test="${not empty requestScope.INSERTERROR}">
                                    <c:if test="${not empty requestScope.INSERTERROR.usernameDuplicateErr}">
                                        <font  size="2px" color="red">
                                        ${requestScope.INSERTERROR.usernameDuplicateErr}<br/>
                                        </font>
                                    </c:if>
                                </c:if>
                            </div>

                            <div>
                                <label for="password">Password</label><br>
                                <input class="text" type="password" name="password" 
                                       value="<%= (request.getParameter("password") == null) ? "" : request.getParameter("password")%>" required>
                                <br>
                                <c:if test="${not empty requestScope.INSERTERROR}">
                                    <c:if test="${not empty requestScope.INSERTERROR.passwordLengthErr}">
                                        <font  size="2px" color="red">
                                        ${requestScope.INSERTERROR.passwordLengthErr}<br/>
                                        </font>
                                    </c:if>
                                </c:if>
                            </div>

                            <div>
                                <label for="confirm">Confirm password</label><br>
                                <input class="text" type="password" name="cfpassword" \
                                       value="<%= (request.getParameter("cfpassword") == null) ? "" : request.getParameter("cfpassword")%>" required>
                                <br>
                                <c:if test="${not empty requestScope.INSERTERROR}">
                                    <c:if test="${not empty requestScope.INSERTERROR.confirmNotMatch}">
                                        <font size="2px" color="red">
                                        ${requestScope.INSERTERROR.confirmNotMatch}<br/>
                                        </font>
                                    </c:if>
                                </c:if>
                            </div>

                            <div>
                                <label for="fullname">Fullname</label><br>
                                <input class="text" type="text" name="fname" 
                                       value="<%= (request.getParameter("fname") == null) ? "" : request.getParameter("fname")%>" required>
                                <br>
                                <c:if test="${not empty requestScope.INSERTERROR}">
                                    <c:if test="${not empty requestScope.INSERTERROR.fullNameLengthErr}">
                                        <font  size="2px" color="red">
                                        ${requestScope.INSERTERROR.fullNameLengthErr}<br/>
                                        </font>
                                    </c:if>
                                </c:if>
                            </div>

                            <div>
                                <label for="phone">Phone number</label><br>
                                <input class="text" type="text" name="phone" 
                                       value="<%= (request.getParameter("phone") == null) ? "" : request.getParameter("phone")%>" required>
                                <br>
                                <c:if test="${not empty requestScope.INSERTERROR}">
                                    <c:if test="${not empty requestScope.INSERTERROR.phoneDuplicateErr}">
                                        <font  size="2px" color="red">
                                        ${requestScope.INSERTERROR.phoneDuplicateErr}
                                        </font>
                                    </c:if>
                                </c:if>
                                <c:if test="${not empty requestScope.INSERTERROR.phoneLengthErr}">
                                    <font  size="2px" color="red">
                                    ${requestScope.INSERTERROR.phoneLengthErr}<br/>
                                    </font>
                                </c:if>
                            </div>       

                            <div>
                                <label for="role">
                                    <input type="radio" name="roleid" value="1"> Customer
                                    <input type="radio" name="roleid" value="2"> Store
                                </label>
                            </div>   
                            <br>

                            <input class="btn-register" type="submit" value="Register" name="btAction"/><br>

                            <a href="url" class="btn-register-gg">Đăng kí với Google</a><br><br>

                            <a href="login.jsp" style="text-decoration: none; margin-left: 25px; margin-right: 20px; color: darkgrey">
                                Already have an account? <b style="color: black">Login</b>
                            </a>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>

