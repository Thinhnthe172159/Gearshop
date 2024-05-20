<%--
    Document   : login
    Created on : Feb 20, 2024, 4:46:14 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- ===== Iconscout CSS ===== -->
        <link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

        <!-- ===== CSS ===== -->
        <link rel="stylesheet" href="assests/css/style2.css">
        <style>
            /* ===== Google Font Import - Poformsins ===== */
            @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap');

            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }

            body{
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                background-color: #4070f4;
            }

            .container{
                position: relative;
                max-width: 430px;
                width: 100%;
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                margin: 0 20px;
            }

            .container .forms{
                display: flex;
                align-items: center;
                height: 440px;
                width: 200%;
                transition: height 0.2s ease;
            }


            .container .form{
                width: 50%;
                padding: 30px;
                background-color: #fff;
                transition: margin-left 0.18s ease;
            }

            .container.active .login{
                margin-left: -50%;
                opacity: 0;
                transition: margin-left 0.18s ease, opacity 0.15s ease;
            }

            .container .signup{
                opacity: 0;
                transition: opacity 0.09s ease;
            }
            .container.active .signup{
                opacity: 1;
                transition: opacity 0.2s ease;
            }

            .container.active .forms{
                height: 900px;
            }
            .container .form .title{
                position: relative;
                font-size: 27px;
                font-weight: 600;
            }

            .form .title::before{
                content: '';
                position: absolute;
                left: 0;
                bottom: 0;
                height: 3px;
                width: 30px;
                background-color: #4070f4;
                border-radius: 25px;
            }

            .form .input-field{
                position: relative;
                height: 50px;
                width: 100%;
                margin-top: 30px;
            }

            .input-field input{
                position: absolute;
                height: 100%;
                width: 100%;
                padding: 0 35px;
                border: none;
                outline: none;
                font-size: 16px;
                border-bottom: 2px solid #ccc;
                border-top: 2px solid transparent;
                transition: all 0.2s ease;
            }

            .input-field input:is(:focus, :valid){
                border-bottom-color: #4070f4;
            }

            .input-field i{
                position: absolute;
                top: 50%;
                transform: translateY(-50%);
                color: #999;
                font-size: 23px;
                transition: all 0.2s ease;
            }

            .input-field input:is(:focus, :valid) ~ i{
                color: #4070f4;
            }

            .input-field i.icon{
                left: 0;
            }
            .input-field i.showHidePw{
                right: 0;
                cursor: pointer;
                padding: 10px;
            }

            .form .checkbox-text{
                display: flex;
                align-items: center;
                justify-content: space-between;
                margin-top: 20px;
            }

            .checkbox-text .checkbox-content{
                display: flex;
                align-items: center;
            }

            .checkbox-content input{
                margin-right: 10px;
                accent-color: #4070f4;
            }

            .form .text{
                color: #333;
                font-size: 14px;
            }

            .form a.text{
                color: #4070f4;
                text-decoration: none;
            }
            .form a:hover{
                text-decoration: underline;
            }

            .form .button{
                margin-top: 35px;
            }

            .form .button input{
                border: none;
                color: #fff;
                font-size: 17px;
                font-weight: 500;
                letter-spacing: 1px;
                border-radius: 6px;
                background-color: #4070f4;
                cursor: pointer;
                transition: all 0.3s ease;
            }

            .button input:hover{
                background-color: #265df2;
            }

            .form .login-signup{
                margin-top: 30px;
                text-align: center;
            }
        </style>
        <title>Login & Registration Form</title> 
    </head>
    <body>
        <div class=""><a href="home"><svg xmlns="http://www.w3.org/2000/svg" x="0px" y="0px" width="100" height="100" viewBox="0 0 48 48">
                <path fill="#9fa8da" d="M45,45V25.9c0-1.8-0.8-3.6-2.3-4.7L29.1,10.5c-0.4-0.3-0.9-0.3-1.2,0L14.3,21.2c-1.4,1.1-2.3,2.9-2.3,4.7V45	c0,0.6,0.4,1,1,1h31C44.6,46,45,45.6,45,45z"></path><path fill="none" stroke="#18193f" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="3" d="M13.4,13.8l-3.6,2.9c-1.4,1.1-2.3,2.9-2.3,4.7v18.1c0,1.1,0.9,2,2,2h20"></path><path fill="none" stroke="#18193f" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="3" d="M35,41.5h3.5c1.1,0,2-0.9,2-2V21.4c0-1.8-0.8-3.6-2.3-4.7L24.6,6c-0.4-0.3-0.9-0.3-1.2,0l-4.6,3.6"></path><path fill="none" stroke="#18193f" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" stroke-width="3" d="M18.5,35.7v-8.2c0-0.6,0.4-1,1-1h8.9c0.6,0,1,0.4,1,1v14"></path>
                </svg></a></div>
        <div class="container" id="authContainer">       
            <c:if test="${requestScope.wrong!=null}">
                <div class="alert alert-warning h-100">
                    <strong>Warning!</strong> ${requestScope.wrong}
                </div>
            </c:if>
            <c:if test="${requestScope.incorrect!=null}">
                <div class="alert alert-warning h-100">
                    <strong>Warning!</strong> ${requestScope.incorrect}
                </div>
            </c:if>
            <c:if test="${requestScope.done!=null}">
                <div class="alert alert-success h-100">
                    <strong>done!</strong> ${requestScope.done}
                </div>
            </c:if>
            <c:if test="${requestScope.mess2!=null}">
                <div class="alert alert-warning h-100">
                    <strong>warning!</strong> ${requestScope.mess2}
                </div>
            </c:if>
            <div class="forms">
                <div class="form login">
                    <span class="title">Login</span>
                    <form action="login" method="post">
                        <div class="input-field">
                            <input type="text" placeholder="Enter user name" required name="user" value="${requestScope.username}">
                            <i class="uil uil-user icon"></i>
                        </div>
                        <div class="input-field">
                            <input type="password" class="password" placeholder="Enter your password" required name="pass" value="${requestScope.pass}">
                            <i class="uil uil-lock icon"></i>
                            <i class="uil uil-eye-slash showHidePw"></i>
                        </div>

                        <div class="checkbox-text">
                            <div class="checkbox-content">
                                <input  type="checkbox" id="logCheck" name="remember" value="on">
                                <label for="logCheck" class="text">Remember me</label>
                            </div>

                            <a href="resetPassword.jsp" class="text">Forgot password?</a>
                        </div>

                        <div class="input-field button">
                            <input type="submit" value="Login">
                        </div>
                    </form>

                    <div class="login-signup">
                        <span class="text">Not a member?
                            <a href="#" class="text signup-link">Signup Now</a>
                        </span>
                    </div>
                </div>

                <!-- Registration Form -->
                <div class="form signup">
                    <span class="title">Registration</span>

                    <form action="signup" method="post">
                        <div class="input-field">
                            <input type="text" placeholder="Enter your name" required name="cr_user" value="${requestScope.user_data.name}">
                            <i class="uil uil-user"></i>
                        </div>
                        <div class="input-field">
                            <input type="text" placeholder="Enter your email" name="cr_email" required value="${requestScope.user_data.email}">
                            <i class="uil uil-envelope icon"></i>
                        </div>
                        <div class="input-field">
                            <input type="text" placeholder="Enter your phone" name="cr_phone" required value="${requestScope.user_data.phone}">
                            <i class="uil uil-user icon"></i>
                        </div>
                        <div class="input-field">                    
                            <input type="text" placeholder="Enter user name" name="cr_user_name" required value="${requestScope.user_data.username}">
                            <i class="uil uil-user icon"></i>
                        </div>
                        <div class="input-field">
                            <input type="password" class="password" name="cr_password" placeholder="Create a password" required value="${requestScope.user_data.password}">
                            <i class="uil uil-lock icon"></i>
                        </div>
                        <div class="input-field">
                            <input type="password" class="password"name="cr_c_password" placeholder="Confirm a password" required value="${requestScope.c_password}">
                            <i class="uil uil-lock icon"></i>
                            <i class="uil uil-eye-slash showHidePw"></i>
                        </div>

                        <div class="checkbox-text">
                            <div class="checkbox-content">
                                <input type="checkbox" id="termCon">
                                <label for="termCon" class="text">I accepted all terms and conditions</label>
                            </div>
                        </div>

                        <div class="input-field button">
                            <input type="submit" value="Signup">
                        </div>
                    </form>

                    <div class="login-signup">
                        <span class="text">Already a member?
                            <a href="#" class="text login-link">Login Now</a>
                        </span>
                    </div>
                </div>
            </div>
        </div>
        <script>
            const container = document.querySelector(".container"),
                    pwShowHide = document.querySelectorAll(".showHidePw"),
                    pwFields = document.querySelectorAll(".password"),
                    signUp = document.querySelector(".signup-link"),
                    login = document.querySelector(".login-link");
//   js code to show/hide password and change icon
            pwShowHide.forEach(eyeIcon => {
                eyeIcon.addEventListener("click", () => {
                    pwFields.forEach(pwField => {
                        if (pwField.type === "password") {
                            pwField.type = "text";
                            pwShowHide.forEach(icon => {
                                icon.classList.replace("uil-eye-slash", "uil-eye");
                            })
                        } else {
                            pwField.type = "password";
                            pwShowHide.forEach(icon => {
                                icon.classList.replace("uil-eye", "uil-eye-slash");
                            })
                        }
                    })
                })
            })
// js code to appear signup and login form
            signUp.addEventListener("click", ( ) => {
                container.classList.add("active");
            });
            login.addEventListener("click", ( ) => {
                container.classList.remove("active");
            });
        </script> 
    </body>
</html>