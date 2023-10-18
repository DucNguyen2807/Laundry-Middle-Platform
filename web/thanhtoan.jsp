<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page language="java" import="Model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <head>
        <meta charset="UTF-8">
        <title>Thanh toán</title>
        <link rel="stylesheet" href="css/thanhtoan.css"> 
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6">
                    <!-- Khung bên trái -->
                    <div class="store-details">
                        <img src="<%= request.getParameter("image")%>" alt="Store Image" style="width: 100%; height: auto;">
                        <p>Store Name: <span><%= request.getParameter("storeName")%></span></p>

                        <p>Address: <span><%= request.getParameter("address")%></span></p>
                        <div class="rating">
                            <p>Rating: <span><%= request.getParameter("rating")%></span></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <!-- Khung bên phải -->
                    <div class="customer-details">
                        <h2>Thông tin khách hàng</h2>
                        <form action="xulythanhtoan.jsp" method="post">
                            <label for="fullname">Tên khách hàng:</label>
                            <input type="text" class="bg-light form-control" name="fullname" value="${user.fullname}" >
                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger">
                                    <strong>Lỗi:</strong> <c:out value="${errorMessage}" />
                                </div>
                            </c:if>
                            <label for="phone">Số điện thoại:</label>
                            <input type="tel" id="phone" name="phone" value="${user.phone}" required><br>
                            <c:if test="${not empty errorMessage3}">
                                <div class="alert alert-danger">
                                    <strong>Lỗi:</strong> <c:out value="${errorMessage3}" />
                                </div>
                            </c:if>     
                            <label for="customerAddress">Địa chỉ:</label>
                            <input type="text" id="customerAddress" name="customerAddress" value="${user.address}" required><br>
                            <label for="kilos">Số kg:</label>
                            <input type="number" id="kilos" name="kilos" required><br>
                            <label for="services">Dịch vụ:</label>
                            <select id="services" name="services" required>
                                <option value="giatthuong">Giặt thường: <%= request.getParameter("priceGiatThuong")%> vnđ</option>
                                <option value="giatnhanh">Giặt nhanh: <%= request.getParameter("priceGiatNhanh")%> vnđ</option>
                                <option value="giatsieutoc">Giặt siêu tốc: <%= request.getParameter("priceGiatSieuToc")%> vnđ</option>
                            </select><br>
                            <label for="note">Ghi chú:</label>
                            <textarea id="note" name="note" rows="4"></textarea><br>
                            <p>Tổng thanh toán: <span id="totalAmount">0</span> vnđ</p>
                            <button type="submit">Xác nhận đặt hàng</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
