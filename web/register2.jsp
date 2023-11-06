<%-- 
    Document   : testlog
    Created on : Oct 12, 2023, 12:07:51 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/register2.css"/>
        <title>JSP Page</title>
    </head>
    <body class="image-background">
        <div class="container">
            <div class="wrapper">
                <form class="form-login-content" action="MainController" method="POST">
                    <h1>WELCOME</h1>
                    <div class="sign-in">
                        <a class="larger-text">Sign up to</a>
                        <a>Laundry Middle Plaform</a>
                    </div>
                    <br/>
                    <div>
                        <input type="username" id="username" name="username" placeholder="Username"                               
                               value="<%= (request.getParameter("username") == null) ? "" : request.getParameter("username")%>" required> 
                        <c:if test="${not empty requestScope.INSERTERROR}">
                            <c:if test="${not empty requestScope.INSERTERROR.usernameDuplicateErr}">
                                <font  size="2px" color="red">
                                ${requestScope.INSERTERROR.usernameDuplicateErr}
                                </font>
                            </c:if>
                        </c:if>
                    </div>

                    <div>
                        <input type="password" name="password" id="password" placeholder="Password"
                               value="<%= (request.getParameter("password") == null) ? "" : request.getParameter("password")%>" required>    
                        <c:if test="${not empty requestScope.INSERTERROR}">
                            <c:if test="${not empty requestScope.INSERTERROR.passwordLengthErr}">
                                <font  size="2px" color="red">
                                ${requestScope.INSERTERROR.passwordLengthErr}<br/>
                                </font>
                            </c:if>
                        </c:if>
                    </div>

                    <div>
                        <input type="password" id="password" name="cfpassword" placeholder="Confirm password"                               
                               value="<%= (request.getParameter("cfpassword") == null) ? "" : request.getParameter("cfpassword")%>" required>
                        <c:if test="${not empty requestScope.INSERTERROR}">
                            <c:if test="${not empty requestScope.INSERTERROR.confirmNotMatch}">
                                <font size="2px" color="red">
                                ${requestScope.INSERTERROR.confirmNotMatch}<br/>
                                </font>
                            </c:if>
                        </c:if>
                    </div>

                    <div>
                        <input type="username" id="username" name="fname" placeholder="Fullname"                               
                               value="<%= (request.getParameter("fname") == null) ? "" : request.getParameter("fname")%>" required>
                        <c:if test="${not empty requestScope.INSERTERROR}">
                            <c:if test="${not empty requestScope.INSERTERROR.fullNameLengthErr}">
                                <font  size="2px" color="red">
                                ${requestScope.INSERTERROR.fullNameLengthErr}<br/>
                                </font>
                            </c:if>
                        </c:if>
                    </div>

                    <div>
                        <input type="username" id="username" name="phone" placeholder="Phone"                               
                               value="<%= (request.getParameter("phone") == null) ? "" : request.getParameter("phone")%>" required> 
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
                    <br>
                    <div>
                        <label for="role">Select a role:</label>
                        <div class="radio-options">
                            <input type="radio" id="customer" name="role_id" value="1" required>
                            <label for="customer">Customer</label>
                            <input type="radio" id="store" name="role_id" value="3" required>
                            <label for="store">Store</label>
                        </div>
                    </div>



                    <input type="submit" id="login-btn" value="Register" name="btAction">
                    <div class="or">
                        <hr>
                        <span>OR</span>
                        <hr>
                    </div>
                    <button class="google-btn">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-google" viewBox="0 0 16 16">
                        <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384
                              5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 
                              2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 
                              3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 
                              1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z"/>
                        </svg>
                        Login with Google
                    </button>
                    <a href="login.jsp" class="centered-link">
                        Already have an account? <b>Login</b>
                    </a>

            </div>
        </form> 



        <div class="main-img">

        </div>
    </div>
</div>
</body>
</html>
