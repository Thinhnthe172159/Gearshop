<%--
    Document   : productList
    Created on : Feb 26, 2024, 4:02:14 PM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
        <!-- MDB -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css" rel="stylesheet"/>
        <style>


            .sidenav {
                color: wheat;
                height: 100%; /* 100% Full-height */
                width: 0; /* 0 width - change this with JavaScript */
                position: fixed; /* Stay in place */
                z-index: 1; /* Stay on top */
                top: 66; /* Stay at the top */
                left: 0;
                background:#062c33; /* Black*/
                overflow-x: hidden; /* Disable horizontal scroll */
                padding-top: 60px; /* Place content 60px from the top */
                transition: 0.5s; /* 0.5 second transition effect to slide in the sidenav */
            }

            /* The navigation menu links */
            .sidenav a {
                padding: 8px 8px -9px 32px;
                text-decoration: none;
                font-size: 25px;
                color: #818181;
                display: block;
                transition: 0.3s;
            }

            /* When you mouse over the navigation links, change their color */
            .sidenav a:hover {
                color: #f1f1f1;
            }

            /* Position and style the close button (top right corner) */
            .sidenav .closebtn {
                position: absolute;
                top: 0;
                right: 25px;
                font-size: 36px;
                margin-left: 50px;
            }

            /* Style page content - use this if you want to push the page content to the right when you open the side navigation */
            #main {
                transition: margin-left .5s;
                padding: 0;

            }

            /* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
            @media screen and (max-height: 450px) {
                .sidenav {
                    padding-top: 15px;
                }
                .sidenav {
                    font-size: 10px;
                }
            }

            .p {
                font-size: 5px;
            }
            .search{
                position: fixed;
                width: 100%;
                z-index: 2;
            }

            .sidenav {
                /* your existing styles */
                position: fixed;
                z-index: 1;
                transition: 0.5s;
            }

            .pagination {
                display: inline-block;
            }

            .pagination a {
                color: black;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
            }

            .pagination a.active {
                background-color: #4CAF50;
                color: white;
            }

            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }


        </style>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </head>
    <body class='body'>
        <c:set var="cid" value="${requestScope.id}"/>
        <c:set var="name" value="${requestScope.name}"/>
        <c:set var="price_from" value="${requestScope.priceFrom}"/>
        <c:set var="price_to" value="${requestScope.priceTo}"/>
        <c:set var="type_raw" value="${requestScope.Type}"/>
        <c:set var="type_raw_id" value="${requestScope.type_id_raw}"/>
        <form id="myForm" action="productList" onsubmit="submitForm(event)">
            <div id="mySidenav" class="sidenav" >    
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <p class="a">Tìm kiếm</p>
                <div class="input-group mb-3">
                    <span class="input-group-text " style="color: white">Name</span>
                    <input name="name" type="text" class="form-control" aria-label="Dollar amount (with dot and two decimal places)">
                </div>
                <p class="a">Mức giá trên</p>
                <div class="input-group mb-3">
                    <span class="input-group-text " style="color: white">Đồng</span>
                    <input name="price_from" type="text" class="form-control" aria-label="Dollar amount (with dot and two decimal places)">
                </div>
                <p class="a">Mức giá dưới</p>
                <div class="input-group mb-3">
                    <span class="input-group-text " style="color: white">Đồng</span>
                    <input name="price_To" type="text" class="form-control" aria-label="Dollar amount (with dot and two decimal places)">
                </div>
                <p class="a">Thương hiệu sản phẩm</p>
                <select name="cid"  class="form-select a" aria-label="Default select example">
                    <option value="0">All</option>
                    <c:forEach items="${requestScope.categories}" var="c">                       
                        <option value="${c.id}">${c.productType.name} ${c.name}</option>
                    </c:forEach>                
                </select>

                <br/><br/>
                <!-- Default checkbox -->
                <div class="form-check">
                    <input class="form-check-input " name="type_raw" type="checkbox" value ="1" id="option1"  onclick="handleCheckboxClick('option1')">
                    <label class="form-check-label " for="option1">Sắp xếp tăng dần theo giá</label>
                </div>

                <!-- Checked checkbox -->
                <div class="form-check">
                    <input class="form-check-input " name="type_raw" type="checkbox"  value ="2" id="option2" onclick="handleCheckboxClick('option2')">
                    <label class="form-check-label " for="option2">sắp xếp giảm dần theo giá</label>
                </div>
                <button type="submit" class="btn btn-primary btn-lg btn-block">Tìm</button>
            </div> 
        </form>

        <!-- sidenav -->


        <div id="main" style="">
            <form id="myForm" action="productList">
                <div class="row flex-fill search align-items-center" style="background: #062c33;height: 60px;margin-left: 1px">
                    <div class="open col-sm-1 d-flex align-items-center justify-content-cente" onclick="option()" style="background: white"><img src="https://static.vecteezy.com/system/resources/previews/000/572/119/non_2x/menu-icon-vector.jpg" style="width:35%;"/></div>

                    <c:forEach items="${requestScope.type}" var="t">   
                        <div class="col-md-1">

                            <div class="dropdown">
                                <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" style="width: 150px">
                                    ${t.name}
                                </button>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${requestScope.categories}" var="c">
                                        <c:if test="${c.productType.id == t.id}">
                                            <li><a class="dropdown-item"  href="productList?cid=${c.id}&type_id=${t.id}">${c.name}</a></li>
                                            </c:if>
                                        </c:forEach>
                                </ul>
                            </div> 
                        </div>
                    </c:forEach>
                    <div class="col-md-1">

                        <div class="dropdown">
                            <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false" style="width: 150px">
                                type
                            </button>
                            <ul class="dropdown-menu">
                                <c:forEach items="${requestScope.type}" var="t">

                                    <li><a class="dropdown-item"  href="productList?cid=0&type_id=${t.id}">${t.name}</a></li>

                                </c:forEach>
                            </ul>
                        </div> 
                    </div>
                </div> 
            </form>



            <!-- Use any element to open the side nav -->
            <section>
                <div class="text-center container py-5">                    
                    <div class="mt-4 mb-5"><strong>có ${requestScope.count} sản phẩm</strong></div> 
                    <c:if test="${requestScope.product == null}">
                        <img src="https://www.kalpamritmarketing.com/design_img/no-product-found.jpg" alt="No Product Found"/>
                    </c:if>


                    <!--Phần phân trang-->
                    <form id="myForm2" action="productList" method="GET">
                        <input type="hidden" id="selectedCategories" name="selectedCategories">

                        <div class="row text-lg-left text-nowrap">
                            <c:forEach items="${requestScope.categories}" var="cat">
                                <span class="col-lg-2">
                                    <input class="form-check-input" type="checkbox" value="${cat.id}" name="cid" onchange="updateSelectedCategories(this); submitFormOnCheckboxChange()"
                                           <c:if test="${not empty param.cid}">
                                               <c:forEach var="selectedId" items="${paramValues.cid}">
                                                   <c:if test="${selectedId == cat.id}">checked="checked"</c:if>
                                               </c:forEach>
                                           </c:if>
                                           >
                                    ${cat.productType.name}-${cat.name}
                                </span>
                            </c:forEach>
                        </div>                      
                    </form>
                    <!-- phần phân trang sản phẩm ở dây -->
                    <c:if test="${requestScope.product != null}">
                        <div class="pagination">
                            <c:set var="page" value="${requestScope.page}"/>
                            <a href="productList?page=${1}<c:forEach items="${id}" var="cid_arr">&cid=${cid_arr}</c:forEach>&name=${name}&price_from=${priceFrom}&price_To=${priceTo}&type_raw=${Type}&type_id=${type_id_raw}">&laquo;</a>
                            <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                <a class="${i==page?"active":""}" href="productList?page=${i}<c:forEach items="${id}" var="cid_arr">&cid=${cid_arr}</c:forEach>&name=${name}&price_from=${priceFrom}&price_To=${priceTo}&type_raw=${Type}&type_id=${type_id_raw}">${i}</a>
                            </c:forEach> 
                            <a href="productList?page=${num}<c:forEach items="${requestScope.id}" var="cid_arr">&cid=${cid_arr}</c:forEach>&name=${name}&price_from=${priceFrom}&price_To=${priceTo}&type_raw=${Type}&type_id=${type_id_raw}">&raquo;</a>
                            </div>
                    </c:if>

                    <!-- kết thức phần phân trang -->  
                    <div class="row">
                        <c:forEach items="${requestScope.product}" var="p">     
                            <div class="col-lg-4 col-md-12  mb-4">

                                <div class="card">
                                    <div class="bg-image hover-zoom ripple ripple-surface ripple-surface-light" data-mdb-ripple-color="light">
                                        <img src="img_product/${p.image}" class="w-100" />

                                        <!-- phần này là phần link sản phẩm và khi click chuột vào thì nó sẽ dãn đến trang sản phẩm chi tiết-->
                                        <a href="productDetail?id=${p.id}">
                                            <div class="mask">
                                                <div class="d-flex justify-content-start align-items-end h-100">
                                                    <h5><span class="badge bg-primary ms-2"></span></h5>
                                                </div>
                                            </div>
                                            <div class="hover-overlay">
                                                <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="card-body">
                                        <a href="productDetail?id=${p.id}" class="text-reset">
                                            <h5 class="card-title mb-3">${p.name}</h5>
                                        </a>
                                        <a href="#" class="text-reset">
                                            <h6>${p.productCategoty.getName()}</h6>
                                        </a>
                                        <p class="card-text" style="text-decoration: line-through;color: red;"><fmt:formatNumber value="${p.price+(0.15*p.price)}" pattern="#,###"/> </p>
                                        <h6 class="mb-3"><fmt:formatNumber value="${p.price}" pattern="#,###"/>VNĐ</h6>
                                    </div>
                                </div>
                            </div>  
                        </c:forEach>
                    </div>
                    <!--Phần phân trang-->


                    <c:if test="${requestScope.product != null}">
                        <div class="pagination">
                            <c:set var="page" value="${requestScope.page}"/>
                            <a href="productList?page=${1}<c:forEach items="${id}" var="cid_arr">&cid=${cid_arr}</c:forEach>&name=${name}&price_from=${priceFrom}&price_To=${priceTo}&type_raw=${Type}&type_id=${type_id_raw}">&laquo;</a>
                            <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                <a class="${i==page?"active":""}" href="productList?page=${i}<c:forEach items="${id}" var="cid_arr">&cid=${cid_arr}</c:forEach>&name=${name}&price_from=${priceFrom}&price_To=${priceTo}&type_raw=${Type}&type_id=${type_id_raw}">${i}</a>
                            </c:forEach> 
                            <a href="productList?page=${num}<c:forEach items="${id}" var="cid_arr">&cid=${cid_arr}</c:forEach>&name=${name}&price_from=${priceFrom}&price_To=${priceTo}&type_raw=${Type}&type_id=${type_id_raw}">&raquo;</a>
                            </div>
                    </c:if>

                    <!-- kết thức phần phân trang -->  
                </div>
            </section>
        </div>
        <!-- sidenav end -->
        <script>
            let isOpen = false;

            function option() {
                if (isOpen) {
                    closeNav();
                } else {
                    openNav();
                }
            }

            function handleCheckboxClick(clickedCheckboxId) {
                const checkboxes = document.querySelectorAll('input[type="checkbox"]');
                checkboxes.forEach(checkbox => {
                    if (checkbox.id !== clickedCheckboxId) {
                        checkbox.checked = false;
                    }
                });
            }

            function openNav() {
                document.getElementById("mySidenav").style.width = "300px";
                document.getElementById("main").style.marginLeft = "300px";

                isOpen = true;
            }

            function closeNav() {
                document.getElementById("mySidenav").style.width = "0";
                document.getElementById("main").style.marginLeft = "0";

                isOpen = false;
            }

            function submitForm(event) {
                event.preventDefault(); // Prevent the default form submission behavior
                document.getElementById("myForm").submit();
                // Remove the closeNav() call from here so that the sidebar stays open after form submission
            }

        </script>
        <script>
            let selectedCategories = [];

            function updateSelectedCategories(checkbox) {
                const categoryId = checkbox.value;
                if (checkbox.checked) {
                    selectedCategories.push(categoryId);
                } else {
                    selectedCategories = selectedCategories.filter(id => id !== categoryId);
                }
            }

            function submitFormOnCheckboxChange() {
                // Update hidden input value with selected categories
                document.getElementById("selectedCategories").value = selectedCategories.join(",");
                // Submit the form
                document.getElementById("myForm2").submit();
            }
        </script>
        <jsp:include page="footer.jsp"/> 
    </body>
</html>



