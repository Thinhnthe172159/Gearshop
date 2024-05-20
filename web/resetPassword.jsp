<%-- 
    Document   : resetPassword
    Created on : Mar 17, 2024, 5:43:32 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Created By CodingNepal -->
<html lang="en" dir="ltr">
    <head>
        <meta charset="utf-8">
        <title></title>
        <style>
            @import url('https://fonts.googleapis.com/css?family=Poppins:400,500,600,700&display=swap');
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Poppins', sans-serif;
            }
            html,body{
                display: grid;
                height: 100%;
                width: 100%;
                place-items: center;
                background: #f2f2f2;
                /* background: linear-gradient(-135deg, #c850c0, #4158d0); */
            }
            ::selection{
                background: #4158d0;
                color: #fff;
            }
            .wrapper{
                width: 380px;
                background: #fff;
                border-radius: 15px;
                box-shadow: 0px 15px 20px rgba(0,0,0,0.1);
            }
            .wrapper .title{
                font-size: 35px;
                font-weight: 600;
                text-align: center;
                line-height: 100px;
                color: #fff;
                user-select: none;
                border-radius: 15px 15px 0 0;
                background: linear-gradient(-135deg, #c850c0, #4158d0);
            }
            .wrapper form{
                padding: 10px 30px 50px 30px;
            }
            .wrapper form .field{
                height: 50px;
                width: 100%;
                margin-top: 20px;
                position: relative;
            }
            .wrapper form .field input{
                height: 100%;
                width: 100%;
                outline: none;
                font-size: 17px;
                padding-left: 20px;
                border: 1px solid lightgrey;
                border-radius: 25px;
                transition: all 0.3s ease;
            }
            .wrapper form .field input:focus,
            form .field input:valid{
                border-color: #4158d0;
            }
            .wrapper form .field label{
                position: absolute;
                top: 50%;
                left: 20px;
                color: #999999;
                font-weight: 400;
                font-size: 17px;
                pointer-events: none;
                transform: translateY(-50%);
                transition: all 0.3s ease;
            }
            form .field input:focus ~ label,
            form .field input:valid ~ label{
                top: 0%;
                font-size: 16px;
                color: #4158d0;
                background: #fff;
                transform: translateY(-50%);
            }
            form .content{
                display: flex;
                width: 100%;
                height: 50px;
                font-size: 16px;
                align-items: center;
                justify-content: space-around;
            }
            form .content .checkbox{
                display: flex;
                align-items: center;
                justify-content: center;
            }
            form .content input{
                width: 15px;
                height: 15px;
                background: red;
            }
            form .content label{
                color: #262626;
                user-select: none;
                padding-left: 5px;
            }
            form .content .pass-link{
                color: "";
            }
            form .field input[type="submit"]{
                color: #fff;
                border: none;
                padding-left: 0;
                margin-top: -10px;
                font-size: 20px;
                font-weight: 500;
                cursor: pointer;
                background: linear-gradient(-135deg, #c850c0, #4158d0);
                transition: all 0.3s ease;
            }
            form .field input[type="submit"]:active{
                transform: scale(0.95);
            }
            form .signup-link{
                color: #262626;
                margin-top: 20px;
                text-align: center;
            }
            form .pass-link a,
            form .signup-link a{
                color: #4158d0;
                text-decoration: none;
            }
            form .pass-link a:hover,
            form .signup-link a:hover{
                text-decoration: underline;
            }
        </style>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    </head>
    <body>
        <div class="wrapper">
            <div class="title">
                Reset Password
            </div>
            <c:if test="${requestScope.user ==null}">
                <form action="forgot" method="get">
                    <div class="field">
                        <input placeholder="Enter email" type="text" name="email" required>                   
                    </div><c:if test="${requestScope.mess!=null}">
                        <div class="">

                            <p style="color: red;">${requestScope.mess}</p>

                        </div></c:if>
                        <div class="field">
                            <input type="submit" value="Search account">
                        </div>
                        <div class="signup-link">
                            <a href="login.jsp">back to login</a>
                        </div>
                    </form>
            </c:if>
            <c:if test="${requestScope.user !=null}">

                <form id="reset" action="forgot" method="post">
                    <input type="text" hidden="" value="${requestScope.user.id}" name="id">
                    <c:if test="${requestScope.mess!=null}">
                        <div class="">

                            <p style="color: green;">${requestScope.mess}</p>

                        </div></c:if>
                        <div class="field">
                            <input type="password" required name="password" placeholder="Enter Password"> 
                        </div>
                        <div>
                            <div class="g-recaptcha" data-sitekey="6LebwpspAAAAAKaZwl1lQsD9Yuj4iIXt1dNvY5UN"></div>
                            <div style="color: red;" id="error"></div>
                        </div>

                        <div class="field">
                            <input type="button" onclick="checkCaptcha()" value="Apply">
                        </div>

                        <div class="signup-link">
                            <a href="login.jsp">back to login</a>
                        </div>

                    </form>
            </c:if>
        </div> 

        <script type="text/javascript">
            function checkCaptcha() {
                var form = document.getElementById("reset");
                var error = document.getElementById("error");
                const response = grecaptcha.getResponse();
                if (response) {
                    form.submit();
                } else {
                    error.textContent = "Please verify that you are not a robot.";
                }
            }

        </script>

    </body>
</html>