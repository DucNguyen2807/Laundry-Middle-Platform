<%-- 
    Document   : updateuser
    Created on : Oct 5, 2023, 2:11:07 AM
    Author     : ducnx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="Model.User" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="css/updateprofile.css"/>
        <title>Update Page</title>
        <title>Cập nhật thông tin tài khoản</title>
    </head>
    <body>

        <h1 style="text-align: center; padding-top: 50px;">Update Account</h1>
        <form action="UpdateProfileController" method="POST" >
            <div class="wrapper bg-white mt-sm-5">
                <h4 class="pb-4 border-bottom">Account settings</h4>
                <div class="d-flex align-items-start py-3 border-bottom">
                    <img src="image/Profile.jpg"
                         class="img" alt="Tienngu">
                    <div class="pl-sm-4 pl-2" id="img-section">
                        <input style="border: none" type="text" name="username" value="${user.username}" readonly=""/>
                    </div>
                </div>
                <div class="py-2">
                    <div class="py-2">
                        <label for="fullname">Full Name</label>
                        <input type="text" class="bg-light form-control" name="fullname" value="${user.fullname}" >
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger">
                                <strong>Lỗi:</strong> <c:out value="${errorMessage}" />
                            </div>
                        </c:if>
                    </div>
                    <div class="py-2">

                    </div>
                    <div class="row py-2">
                        <div class="col-md-6">
                            <label for="email">Email Address</label>
                            <input type="text" class="bg-light form-control" name="email" value="${user.email}" pattern="^[a-zA-Z0-9._%+-]+@gmail\.com$" required>
                        </div>
                        <div class="col-md-6 pt-md-0 pt-3">
                            <label for="phone">Phone Number</label>
                            <input type="tel" class="bg-light form-control" name="phone" value="0${user.phone}">
                            <c:if test="${not empty errorMessage3}">
                                <div class="alert alert-danger">
                                    <strong>Lỗi:</strong> <c:out value="${errorMessage3}" />
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="py-2">
                        <label for="Address">Address</label>
                        <input type="address" class="bg-light form-control" name="address" value="${user.address}">
                    </div>

                    <div class="py-2">
                        <label for="curPassword">Password</label>
                        <input type="password" class="bg-light form-control" name="curpassword">
                        <c:if test="${not empty errorMessage1}">
                            <div class="alert alert-danger">
                                <strong>Lỗi:</strong> <c:out value="${errorMessage1}" />
                            </div>
                        </c:if>
                    </div>
                    <div class="py-2">
                        <label for="Password">New Password</label>
                        <input type="password" class="bg-light form-control" name="password">
                        <c:if test="${not empty errorMessage2}">
                            <div class="alert alert-danger">
                                <strong>Lỗi:</strong> <c:out value="${errorMessage2}" />
                            </div>
                        </c:if>
                    </div>

                    <div class="py-3 pb-4 border-bottom">
                        <form action="MainController" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn lưu thay đổi?');">
                            <button class="btn btn-primary mr-3" value="Update" name="btAction">Save Changes</button>
                        </form>
                    </div>
                    <div>
                        <form action="MainController" method="post">
                            <button class="btn btn-danger" value="CancelUppdate" name="btAction">Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>