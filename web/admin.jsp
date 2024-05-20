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
            body {
                margin: 0;
                padding: 0;
                background-image: url('https://i.pinimg.com/originals/c1/fc/9d/c1fc9d7f6ae08d56f2b84e81799790a5.gif');
                background-size: cover; /* Để hình nền phủ toàn bộ kích thước của body */

            }
        </style>


    </head>
    <body>
        <!-- Navbar --------------------------------------------------------------------------->
        <nav class="navbar navbar-expand-lg navbar-nav bg-body-tertiary sticky-top" >
            <!-- Container wrapper -->
            <div class="container-fluid" style="background-color:darkslategrey;">
                <!-- Toggle button -->


                <!-- Collapsible wrapper -->
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <!-- Navbar brand -->
                    <a class="navbar-brand mt-2 mt-lg-0" href="adminHomePage">
                        <img
                            src="img_icon/Neon Blue and Black Gamer Badge Logo.png"
                            height="60"
                            alt="MDB Logo"
                            loading="lazy"
                            />
                    </a>
                    <!-- Left links -->            
                    <form action="productList">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <div class="btn-group">
                                    <button
                                        type="button"
                                        class="btn btn-light dropdown-toggle"
                                        data-mdb-dropdown-init
                                        data-mdb-ripple-init
                                        aria-expanded="false">
                                        Right-aligned menu
                                    </button>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li><a class="dropdown-item" href="managerProduct">Product manager</a></li>
                                        <li><a class="dropdown-item" href="managerAccount" >Client manager</a></li>
                                        <li><a class="dropdown-item" href="manaCategory" >Brand manager</a></li>
                                        <li><a class="dropdown-item" href="manaType" >Type manager</a></li>
                                        <li><a class="dropdown-item" href="manaOrdering" >Order manager</a></li>
                                        <li><a class="dropdown-item" href="histotyTransaction">History transaction</a></li>
                                    </ul>
                                </div>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" style="color: white;" href="statistic">Statistic</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" style="color: white;" href="#">Projects</a>
                            </li>
                        </ul>
                    </form>
                    <!-- Left links -->
                </div>

                <!-- Right elements -->

                <div class="d-flex align-items-center">               
                    <!-- Icon -->

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
                            <i class="fas fa-bell text-light"></i>
                            <span class="badge rounded-pill badge-notification bg-danger">1</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink" >
                            <li>
                                <a class="dropdown-item" href="#">Some news</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">Another news</a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="#">Something else here</a>
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

                <!-- Right elements -->
            </div>
            <!-- Container wrapper -->
        </nav>
        <!-- End Navbar -->    
        <div class="main">
            <c:if test="${not empty requestScope.product}">
                <jsp:include page="manaProduct.jsp"/>
            </c:if>
            <c:if test="${requestScope.user!=null}">
                <jsp:include page="adminProfile.jsp"/>
            </c:if>
            <c:if test="${requestScope.accountList!=null}">
                <jsp:include page="manaAccount.jsp"/>
            </c:if>
            <c:if test="${requestScope.categoryList!=null}">
                <jsp:include page="manaProductCategory.jsp"/>
            </c:if>
            <c:if test="${requestScope.typeLists!=null}">
                <jsp:include page="manaType.jsp"/>
            </c:if>
            <c:if test="${requestScope.orderListNeedConfirm!=null}">
                <jsp:include page="manaOrdering.jsp"/>
            </c:if>
            <c:if test="${requestScope.histotyTransaction!=null}">
                <jsp:include page="histotyTransaction.jsp"/>
            </c:if>
            <c:if test="${requestScope.productTypeList1 !=null}">
                <div style="background: white">
                   <jsp:include page="chart.jsp"/> 
                </div>
                
            </c:if>
            

        </div>


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
