<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page language="java" import="Model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
    <head>
        <meta charset="UTF-8">
        <title>Thanh toán</title>
        <link rel="stylesheet" href="css/thanhtoan.css"> 
        <!--<link rel="stylesheet" href="css/cate.css">--> 

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
                        <input type="hidden" name="storeID" value="<%= request.getParameter("storeID")%>">
                        <input type="hidden" name="storeAddress" value="<%= request.getParameter("storeAddress")%>">

                        <div class="rating">

<!--<p>Rating: <span><%= request.getParameter("rating")%></span></p>-->
                            <p >Rating:
                                <%
                                    int rating = Integer.parseInt(request.getParameter("rating"));
                                    for (int i = 0; i < rating; i++) {
                                %>
                                <span class="star">★</span>
                                <%
                                    }
                                %>
                            </p>
                        </div>

                    </div>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                            <strong>Lỗi:</strong> <c:out value="${errorMessage}" />
                        </div>
                    </c:if>
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success">
                            <strong>Thông báo:</strong> <c:out value="${successMessage}" />
                        </div>
                    </c:if>


                </div>
                <div class="col-md-6">
                    <!-- Khung bên phải -->
                    <div class="customer-details">
                        <h2>Thông tin khách hàng</h2>
                        <form action="MainController" method="post">
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
                            </select>

                            <label for="note">Ghi chú:</label>
                            <textarea id="note" name="note" rows="4"></textarea><br>

                            <label for="session">Buổi:</label>
                            <select id="session" name="session" required>
                                <option value="morning">Buổi sáng</option>
                                <option value="noon">Buổi trưa</option>
                                <option value="afternoon">Buổi chiều</option>
                                <option value="evening">Buổi tối</option>
                            </select><br>


                            <p>Tổng thanh toán: <span name="totalPrice" id="totalAmount"></span>
                                <br>

                                <button type="submit" name="btAction" value="ConfirmOrder">Xác nhận đặt hàng</button>

                                <script type="text/javascript">
                                    function calculateTotalAmount() {
                                        // Lấy giá trị dịch vụ và số kg từ các trường input
                                        var selectedService = document.getElementById("services").value;

                                        var kilos = parseInt(document.getElementById("kilos").value);
                                        var selectedSession = document.getElementById("session").value;

                                        // Kiểm tra nếu số kg là NaN hoặc không hợp lệ
                                        if (isNaN(kilos) || kilos <= 0) {
                                            document.getElementById("totalAmount").textContent = "0 vnđ";
                                            return;
                                        }

                                        // Khai báo biến để lưu giá tiền
                                        var price = 0;

                                        // Dựa vào loại dịch vụ, gán giá trị cho price
                                        if (selectedService === "giatthuong") {
                                            price = <%= request.getParameter("priceGiatThuong")%>;
                                        } else if (selectedService === "giatnhanh") {
                                            price = <%= request.getParameter("priceGiatNhanh")%>;
                                        } else if (selectedService === "giatsieutoc") {
                                            price = <%= request.getParameter("priceGiatSieuToc")%>;
                                        }

                                        // Tính tổng tiền
                                        var totalAmount = price * kilos;

                                        // Hiển thị tổng tiền lên trang
                                        document.getElementById("totalAmount").textContent = totalAmount + " vnđ";
                                    }

                                    // Gọi hàm tính toán khi các trường input thay đổi
                                    document.getElementById("services").addEventListener("change", calculateTotalAmount);
                                    document.getElementById("kilos").addEventListener("input", calculateTotalAmount);

                                    // Tính toán tổng tiền khi trang tải lần đầu
                                    calculateTotalAmount();
                                </script>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
