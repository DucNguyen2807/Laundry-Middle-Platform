<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<%@ page language="java" import="Model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

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
                <div class="col-md-7 mx-auto">
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success">
                            <strong>Thông báo:</strong> <c:out value="${successMessage}" />
                        </div>
                    </c:if>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger">
                            <strong>Lỗi:</strong> <c:out value="${errorMessage}" />
                        </div>
                    </c:if>
                    <div class="customer-details mx-auto">
                        <h2>Thông tin khách hàng</h2>
                        <form action="ConfirmOrderController" method="post" accept-charset="UTF-8">
                            <label for="fullname">Tên khách hàng:</label>
                            <input type="text" class="bg-light form-control" name="fullname" value="${user.fullname}" >
                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger">
                                    <strong>Lỗi:</strong> <c:out value="${errorMessage}" />
                                </div>
                            </c:if>
                            <label for="phone">Số điện thoại:</label>
                            <input type="tel" id="phone" name="phone" value="0${user.phone}" required><br>
                            <c:if test="${not empty errorMessage3}">
                                <div class="alert alert-danger">
                                    <strong>Lỗi:</strong> <c:out value="${errorMessage3}" />
                                </div>
                            </c:if>
                            <label for="customerAddress">Địa chỉ:</label>
                            <input type="text" id="customerAddress" name="customerAddress" value="${user.address}" required><br>

                            <label for="kilos">Số kg (từ 1 đến 20):</label>
                            <input type="number" id="kilos" name="kilos" required min="1" max="20" oninput="checkMaxValue(this)">

                            <label for="selectedService">Dịch vụ và giá cả:</label>
                            <select id="selectedService" name="selectedService">
                                <c:forEach items="${storeSale}" var="store">
                                    <c:forEach items="${storePrice}" var="price">
                                        <c:if test="${price.storeID eq store.storeID}">
                                            <option value="${price.serviceID}" data-price="${price.price}">
                                                ${price.serviceDetail} : ${price.price} đ
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </select>

                            <c:forEach items="${storeSale}" var="store">
                                <input type="hidden" name="storeID" value="${store.storeID}" />
                                <input type="hidden" name="storeAddress" value="${store.address}" />
                            </c:forEach>


                            <label for="note">Ghi chú:</label>
                            <textarea id="note" name="note" rows="4"></textarea><br>
                            <div style="display: flex; align-items: center;">
                                <div>
                                    <label for="time">Chọn thời gian:</label>
                                    <input type="time" id="time" name="time" required>
                                </div>
                                <div style="margin-left: 50px;">

                                    <label for="date">Chọn ngày:</label>
                                    <input type="date" id="date" name="date" required>
                                </div>
                            </div>
                            <br>

                            <input type="hidden" name="totalPrice" id="totalPrice" value="0">
                            <p>Tổng tiền cần thanh toán: <span id="totalAmount">0 VNĐ</span></p>                       
                            <br>
                            <button type="submit" name="btAction" value="ConfirmOrder">Xác nhận đặt hàng</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            function calculateTotalAmount() {
                var selectedService = document.getElementById("selectedService");
                var selectedOption = selectedService.options[selectedService.selectedIndex];
                var price = parseFloat(selectedOption.getAttribute("data-price"));
                var kilos = parseInt(document.getElementById("kilos").value);

                if (isNaN(kilos) || kilos <= 0) {
                    document.getElementById("totalAmount").textContent = "0 VNĐ";
                    document.getElementById("totalPrice").value = "0";
                    return;
                }

                var totalAmount = price * kilos;

                // Loại bỏ "VNĐ" trước khi hiển thị
                var formattedTotalAmount = totalAmount.toFixed(3).replace(".", ",");

                document.getElementById("totalAmount").textContent = formattedTotalAmount + " VNĐ";
                document.getElementById("totalPrice").value = formattedTotalAmount; 
            }


            function checkMaxValue(input) {
                if (input.value > 20) {
                    input.value = 20;
                }
            }

            document.getElementById("selectedService").addEventListener("change", calculateTotalAmount);
            document.getElementById("kilos").addEventListener("input", calculateTotalAmount);
            calculateTotalAmount();
        </script>
    </body>
</html>
