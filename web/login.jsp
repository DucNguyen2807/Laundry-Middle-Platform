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
        <link rel="stylesheet" href="css/login.css"/>
        <title>Login</title>
    </head>
    <body class="image-background">

        <div class="container">
            <div class="wrapper">
                <form class="form-login-content" action="MainController" method="POST">
                    <h1>WELCOME</h1>
                    <div class="sign-in">
                        <a class="larger-text">Sign in to</a>
                        <a>Laundry Middle Plaform</a>
                    </div>

                    <br/>
                    <div>
                        <input type="username" id="username" name="txtUsername" placeholder="Username"                               
                               value="<%= (request.getParameter("txtUsername") == null) ? "" : request.getParameter("txtUsername")%>"required><br>
                    </div>
                    <div class="pass-icon">
                        <input type="password" name="txtPassword" id="password" placeholder="Password"
                               value="<%= (request.getParameter("txtPassword") == null) ? "" : request.getParameter("txtPassword")%>"required><br>
                    </div>

                    <div>
                        <label>
                            <input type="checkbox" name="check" value="1"> <a>Remember me</a>
                            <a href="forgotPassword.jsp" class="forgot-password-link">Forgot password?</a>
                        </label>
                    </div>

                    <%
                        // Lấy giá trị của thẻ radio đã được chọn
                        if (request.getParameter("check") != null) {
                            int check = 1;
                            request.setAttribute("check", check);
                        }
                    %>
                    <br>
                    <label class="error-label">${ERROR}</label>

                    <input type="submit" id="login-btn" value="Login" name="btAction">
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
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile&redirect_uri=http://localhost:8080/Laundry_mp/LoginGoogleHandler&response_type=code
                           &client_id=1029323599721-eau63nt8f9pcta6neg5hehi5vfv6kkue.apps.googleusercontent.com">Login with Google</a>

                    </button>
                    <!--                    <div class="register">
                                            <p>If you don't have an account</p>
                                            <button  class="register-btn">Register</button>-->
                    <a href="register2.jsp" class="centered-link">
                        Don't have an account? <b>Register</b>
                    </a>
            </div>
        </form>  



        <div class="main-img">

        </div>
    </div>
</div>
</body>
</html>


