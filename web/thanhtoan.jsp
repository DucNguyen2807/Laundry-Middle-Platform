<%-- 
    Document   : thanhtoan
    Created on : Oct 17, 2023, 8:29:57 PM
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Thanh toán</title>
        <link rel="stylesheet" href="css/thanhtoan.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


    <body>
        <div class="than">
            <div class="tieu-de" include-html="/components/header.html">
            </div>
            <div class="noi-dung">
                <div class="tt-thanh-toan">
                    <div class="tt-ca-nhan">

                        <div>
                            <div class="tieu-de">
                                <div>
                                    <span>1</span>
                                    <p>Số liên hệ</p>
                                </div>
                                <button class="button-3">Cập nhật</button>
                            </div>
                            <div>
                                <div>Điện thoại:</div>
                                <input id="sdt" class="sdt-input rounded-lg" value="">
                            </div>

                            <div>
                                <div class="tieu-de">
                                    <div>
                                        <span>2</span>
                                        <p>Địa chỉ gửi</p>
                                    </div>
                                    <!-- <button class="button-3"> Thêm</button> -->
                                </div>
                                <div>
                                    <div class="dia-chi"> 
                                        <div>
                                            <p class="loai-dia-chi">Gửi từ:</p>
                                            <p class="dia-chi-ct">Linh Xuân, Thủ Đức</p>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div>
                                <div class="tieu-de">
                                    <div>
                                        <span>3</span>
                                        <p>Địa chỉ nhận</p>
                                    </div>
                                    <!-- <button class="button-3"> Thêm</button> -->
                                </div>
                                <div>
                                    <div class="dia-chi">
                                        <div id="headlessui-radiogroup-option-13" role="radio" aria-checked="true" tabindex="0">
                                            <div>
                                                <p class="loai-dia-chi">Nơi nhận:</p>
                                                <p id="diachi" class="dia-chi-ct">Dĩ An, Bình Dương</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div>
                            <div class="tieu-de">
                                <div>
                                    <span>4</span>
                                    <p>Thời gian nhận hàng mong muốn:</p>
                                </div>
                            </div>
                            <div>
                                <div class="ds-thoi-gian-giao-hang">
                                    <div>
                                        <span>Buổi sáng</span>
                                        <span>8.00 giờ - 11.00 giờ</span>
                                    </div>
                                    <div>
                                        <span>Buổi trưa</span>
                                        <span>11.00 giờ - 14.00 giờ</span>
                                    </div>
                                    <div>
                                        <span>Buổi chiều</span>
                                        <span>14.00 giờ - 17.00 giờ</span>
                                    </div>
                                    <div>
                                        <span>Buổi tối</span>
                                        <span>17.00 giờ - 20.00 giờ</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tt-don-hang">
                        <div>
                            <div class="ds-mat-hang">
                                <!-- ds hàng -->
                            </div>
                            <div class="thanh-tien">
                                <div>
                                    <p>Tổng tiền hàng</p>
                                    <span id="tongTienHang">10.000đ</span>
                                </div>
                                <div>
                                    <p>Phí vận chuyển</p>
                                    <span id="phiVanChuyen">0</span>
                                </div>

                                <div class="tong-don-hang">
                                    <p>Total</p>
                                    <span id="tongTien">đ10.000</span>
                                </div>
                            </div>
                            <!--                            <div class="ds-hinh-thuc-thanh-toan">
                                                            <div>
                                                                <label>Hình thức thanh toán</label>
                                                                <div class="hinh-thuc-thanh-toan">
                            
                                                                    <div class="active">
                                                                        <span>Thanh toán khi giao hàng</span>
                                                                    </div>
                            
                                                                </div>
                                                            </div>
                                                        </div>-->
                            <button onclick="makeOrder()" class="nut-dat-hang">Đặt hàng</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
