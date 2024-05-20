<%-- 
    Document   : home
    Created on : Feb 20, 2024, 9:17:48 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Font Awesome -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
            />
        <!-- Google Fonts -->
        <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
            />
        <!-- MDB -->
        <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            nav {
                width: 100%;
                height: 80px;
                background-color: #000000; /* Change this to the desired black color */
                display: block;
                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.5);
            }
        </style>


    </head>
    <body>
        <!-- Navbar --------------------------------------------------------------------------->
        <nav class="navbar navbar-expand-lg navbar-light bg-body-tertiary sticky-top">
            <!-- Container wrapper -->
            <div class="container-fluid">
                <!-- Toggle button -->


                <!-- Collapsible wrapper -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- Navbar brand -->
                    <a class="navbar-brand mt-2 mt-lg-0" href="home">
                        <img
                            src="img_icon/Neon Blue and Black Gamer Badge Logo.png"
                            height="60"
                            alt="MDB Logo"
                            loading="lazy"
                            />
                    </a>
                    <!-- Left links -->            
                    <form action="productList">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-1">
                            <li class="my-2 my-sm-1 nav-item">
                                <a class="nav-link" href="">Thông tin về shop</a>
                            </li>
                            <li class="my-2 my-sm-1 nav-item">
                                <a class="nav-link" href="contactUs.jsp">Khách hàng liên hệ</a>
                            </li>
                            <li class="my-2 my-sm-1 nav-item">
                                <a class="nav-link" href="#">Tin tức công nghệ</a>
                            </li>                        
                            <li><input name="name" class="form-control my-2 my-sm-2 nav-item" type="search" placeholder="Search" aria-label="Search"></li>
                            <li><button class="btn btn-outline-success my-2 my-sm-2 nav-item" type="submit">Search</button></li>                          
                        </ul>
                    </form>
                    <!-- Left links -->
                </div>

                <!-- Right elements -->

                <div class="d-flex align-items-center">
                    <c:if test="${sessionScope.user == null}">
                        <a class="text-reset me-3" href="login">Login</a>
                    </c:if>
                    <c:if test="${sessionScope.user != null}">
                        <!-- Icon -->
                        <a class="text-reset me-3" href="showCart">
                            <i class="fas fa-shopping-cart"></i>
                            <span class="badge rounded-pill badge-notification bg-danger">${sessionScope.OrderLineList.size()}</span>
                        </a>

                        <!-- Notifications -->
                        <div class="dropdown">
                            <a
                                data-mdb-dropdown-init
                                class="text-reset me-3 dropdown-toggle hidden-arrow"
                                href="#"
                                id="navbarDropdownMenuLink"
                                role="button"
                                aria-expanded="false"
                                >
                                <i class="fas fa-bell"></i>
                                <span class="badge rounded-pill badge-notification bg-danger">1</span>
                            </a>
                            <ul
                                class="dropdown-menu dropdown-menu-end"
                                aria-labelledby="navbarDropdownMenuLink"
                                >
                                <li>
                                    <a class="dropdown-item" href="ordered">Đơn hàng đã đặt</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="recieved">Đơn hàng đã được giao</a>
                                </li>   
                            </ul>
                        </div>
                        <!-- Avatar -->
                        <div class="dropdown">
                            <a
                                data-mdb-dropdown-init
                                class="dropdown-toggle d-flex align-items-center hidden-arrow"
                                href="#"
                                id="navbarDropdownMenuAvatar"
                                role="button"
                                aria-expanded="false"
                                >
                                <img
                                    src="${sessionScope.user.image}"
                                    class="rounded-circle"
                                    height="30"
                                    width="30"
                                    alt="Black and White Portrait of a Man"
                                    loading="lazy"
                                    />
                            </a>
                            <ul
                                class="dropdown-menu dropdown-menu-end"
                                aria-labelledby="navbarDropdownMenuAvatar"
                                >
                                <li>
                                    <a class="dropdown-item" href="profile">My profile</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">Settings</a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="login">Logout</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </c:if>
                <!-- Right elements -->
            </div>
            <!-- Container wrapper -->
        </nav>
        <!-- End Navbar --> 
        <script >
            // Initialization for ES Users
            import { Dropdown, Collapse, initMDB } from "mdb-ui-kit";
            initMDB({Dropdown, Collapse});
        </script>

        <!-- MDB -->
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js";
            initMDB({ Dropdown, Ripple });
        ></script>
        <!-- product list -->
        <div class="main">
            
            <c:if test="${requestScope.product!=null}">
                <jsp:include page="productList.jsp"/>
            </c:if>
            <c:if test="${requestScope.productDetail!=null}">
                <jsp:include page="productDetail.jsp"/>
            </c:if>
            <c:if test="${requestScope.user!=null}">
                <jsp:include page="profile.jsp"/>
            </c:if>
            <c:if test="${requestScope.getListOfListProductNewList!=null}">
                <jsp:include page="productNew.jsp"/>
            </c:if>
            <c:if test="${requestScope.fix == 1}">
                <jsp:include page="profile.jsp"/>
            </c:if>
            <c:if test="${requestScope.OrderList != null}">
                <jsp:include page="ordered.jsp"/>
            </c:if>
            <c:if test="${requestScope.recievedList != null}">
                <jsp:include page="recievedOrder.jsp"/>
            </c:if>
            <c:if test="${requestScope.fix!=null && requestScope.fix != 1}">
                <jsp:include page="productNew.jsp"/>
            </c:if>

        </div>





            
        <script>
            function showDropdown(index) {
                var dropdownId = "myDropdown" + index;
                var dropdown = document.getElementById(dropdownId);
                dropdown.classList.add("show");
            }

            function hideDropdown(index) {
                var dropdownId = "myDropdown" + index;
                var dropdown = document.getElementById(dropdownId);
                dropdown.classList.remove("show");
            }

            function cancelHideDropdown(index) {
                var dropdownId = "myDropdown" + index;
                var dropdown = document.getElementById(dropdownId);
                dropdown.classList.remove("show");
            }
        </script>

    </body>
</html>
