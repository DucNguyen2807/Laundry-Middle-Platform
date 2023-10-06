
<%-- 
    Document   : updateuser
    Created on : Oct 5, 2023, 2:11:07 AM
    Author     : ducnx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.bundle.min.js"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <!DOCTYPE html>
    <html>
        <head>
            <title>Cập nhật thông tin tài khoản</title>
            <style>

                @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');

                *{
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                }

                body{
                    font-family: 'Poppins', sans-serif;
                    background-color: aliceblue;
                }

                .wrapper{
                    padding: 30px 50px;
                    border: 1px solid #ddd;
                    border-radius: 15px;
                    margin: 10px auto;
                    max-width: 600px;
                }
                h4{
                    letter-spacing: -1px;
                    font-weight: 400;
                }
                .img{
                    width: 70px;
                    height: 70px;
                    border-radius: 6px;
                    object-fit: cover;
                }
                #img-section p,#deactivate p{
                    font-size: 12px;
                    color: #777;
                    margin-bottom: 10px;
                    text-align: justify;
                }
                #img-section b,#img-section button,#deactivate b{
                    font-size: 14px;
                }

                label{
                    margin-bottom: 0;
                    font-size: 14px;
                    font-weight: 500;
                    color: #777;
                    padding-left: 3px;
                }

                .form-control{
                    border-radius: 10px;
                }

                input[placeholder]{
                    font-weight: 500;
                }
                .form-control:focus{
                    box-shadow: none;
                    border: 1.5px solid #0779e4;
                }
                select{
                    display: block;
                    width: 100%;
                    border: 1px solid #ddd;
                    border-radius: 10px;
                    height: 40px;
                    padding: 5px 10px;
                    /* -webkit-appearance: none; */
                }

                select:focus{
                    outline: none;
                }
                .button{
                    background-color: #fff;
                    color: #0779e4;
                }
                .button:hover{
                    background-color: #0779e4;
                    color: #fff;
                }
                .btn-primary{
                    background-color: #0779e4;
                }
                .danger{
                    background-color: #fff;
                    color: #e20404;
                    border: 1px solid #ddd;
                }
                .danger:hover{
                    background-color: #e20404;
                    color: #fff;
                }
                @media(max-width:576px){
                    .wrapper{
                        padding: 25px 20px;
                    }
                    #deactivate{
                        line-height: 18px;
                    }
                }
            </style>
        </head>
        <body>
            <h1 style="text-align: center">Update account</h1>
            <form>
                <div class="wrapper bg-white mt-sm-5">
                    <h4 class="pb-4 border-bottom">Account settings</h4>
                    <div class="d-flex align-items-start py-3 border-bottom">
                        <img src="views/image/123.png"
                             class="img" alt="Tienngu">
                        <div class="pl-sm-4 pl-2" id="img-section">
                            <b>Profile Photo</b>
                            <p>Accepted file type .png. Less than 1MB</p>
                            <button class="btn button border"><b>Upload</b></button>
                        </div>
                    </div>
                    <div class="py-2">
                        <div class="py-2">
                                <label for="fullname">Full Name</label>
                                <input type="text" class="bg-light form-control" placeholder="Nguyen Xuan Duc">
                        </div>
                        <div class="row py-2">
                            <div class="col-md-6">
                                <label for="email">Email Address</label>
                                <input type="text" class="bg-light form-control" placeholder="steve_@email.com">
                            </div>
                            <div class="col-md-6 pt-md-0 pt-3">
                                <label for="phone">Phone Number</label>
                                <input type="tel" class="bg-light form-control" placeholder="+1 213-548-6015">
                            </div>
                        </div>

                        <div class="py-2">
                            <label for="Address">Address</label>
                            <input type="address" class="bg-light form-control" placeholder="Số 123, đường Trần Hưng Đạo, phường Bến Thành, quận 1, TP. Hồ Chí Minh">
                        </div>

                        <div class="py-2">
                            <label for="Password">Password</label>
                            <input type="password" class="bg-light form-control" name="password">
                        </div>
                        <div class="py-2">
                            <label for="NewPassword">New Password</label>
                            <input type="password" class="bg-light form-control" name="newpassword">
                        </div>

                        <div class="py-3 pb-4 border-bottom">
                            <button class="btn btn-primary mr-3">Save Changes</button>
                            <button class="btn border button">Cancel</button>
                        </div>
                    </div>
                </div>
            </form>
        </body>
    </html>

