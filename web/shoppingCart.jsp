<%-- 
    Document   : shoppingCart
    Created on : Mar 3, 2024, 12:18:50 AM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <style>
            @media (min-width: 1025px) {
                .h-custom {
                    height: 100vh !important;
                }
            }
            .content-scroll-block {
                width: 500px; /* Set the width of the block */
                height: 300px; /* Set the height of the block */
                overflow-y: scroll; /* Allow vertical scrolling if content overflows */
            }

            .content {
                width: 100%; /* Set the width of the content equal to the block */
            }

            .content-scroll-block_2 {
                width: max; /* Set the width of the block */
                height: 500px; /* Set the height of the block */
                overflow-y: scroll; /* Allow vertical scrolling if content overflows */
            }

            .content_2 {
                width: 100%; /* Set the width of the content equal to the block */
            }

        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="item" value="${requestScope.OrderlineList}" />
        <section  style="background-color: #eee;">
            <div class="container py-5 h-200">
                <div class=" d-flex justify-content-center align-items-center h-100">
                    <div class="col">
                        <div class="card">
                            <div class="card-body p-5">

                                <div class="row">

                                    <div class="col-lg-7">
                                        <h5 class="mb-3"><a href="home" class="text-body"><i class="fas fa-long-arrow-alt-left me-2"></i>Continue shopping</a></h5>
                                        <hr>

                                        <div class="d-flex justify-content-between align-items-center mb-4">
                                            <div>
                                                <p class="mb-1">Shopping cart</p>
                                                <p class="mb-0">You have ${requestScope.OrderlineList.size()} items in your cart</p>
                                            </div>
                                            <div>

                                            </div>
                                        </div>
                                        <c:set var="sum" value="0" />
                                        <form action="process" method="post">
                                            <div class="content-scroll-block_2">
                                                <div class="content_2">
                                                    <c:forEach items="${requestScope.OrderlineList}" var="ol">
                                                        <div class="card m  b-3">
                                                            <div class="card-body">
                                                                <div class="d-flex justify-content-between">
                                                                    <div class="d-flex flex-row align-items-center">
                                                                        <div>
                                                                            <div class="form-check">

                                                                                <input class="form-check-input" name="product" type="checkbox" value="${ol.product.id}" onchange="this.form.submit()"
                                                                                       <c:forEach items="${requestScope.check}" var="c">
                                                                                           <c:if test="${ol.product.id == c}">
                                                                                               checked
                                                                                           </c:if>
                                                                                       </c:forEach >>    
                                                                                <input type="hidden" name="price_${ol.product.id}" value="${ol.product.price}">
                                                                                <input type="hidden" name="quantity_${ol.product.id}" value="${ol.quantity}">


                                                                                <label class="form-check-label" for="flexCheckDefault">
                                                                                </label>
                                                                            </div>
                                                                            <a href="productDetail?id=${ol.product.id}">
                                                                                <img
                                                                                    src="img_product/${ol.product.image}"
                                                                                    class="img-fluid rounded-3" alt="Shopping item" style="width: 150px;">
                                                                            </a>  
                                                                        </div>

                                                                        <div class="ms-3">
                                                                            <h5>${ol.product.name}</h5></br>
                                                                            <h5><fmt:formatNumber value="${ol.product.getPrice()}" pattern="#,###"/>VNĐ</h5>
                                                                        </div>
                                                                    </div>
                                                                    <div class="d-flex flex-row align-items-center">
                                                                        <div style="width: 180px;">

                                                                            <h5 class="mb-0 "><fmt:formatNumber value="${ol.price}" pattern="#,###"/>VNĐ</br></br><a id="delete" href="#" onclick="doDelete('${ol.product.id}')" class="fas fa-trash-alt text-danger"></a></h5></br>


                                                                            <div class="input-group">
                                                                                <button class="btn btn-outline-secondary" type="button" onclick="updateQuantityUp('${ol.product.id}', 'increase')">+</button>
                                                                                <input type="number" class="form-control" value="${ol.quantity}" readonly>
                                                                                <button class="btn btn-outline-secondary" type="button" onclick="updateQuantityDown('${ol.product.id}', 'decrease')">-</button>
                                                                            </div>
                                                                        </div>               
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                    </c:forEach>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                    <div class="col-lg-5">

                                        <div class="col-md-12 card bg-primary text-white rounded-3" >
                                            <div class="card-body">
                                                <div class="d-flex justify-content-between align-items-center mb-4">
                                                    <h5 class="mb-0">Card details</h5>
                                                    <img src="${sessionScope.user.image}" class="img-fluid rounded-3" style="width: 45px;" alt="Avatar">
                                                </div>

                                                <p class="small mb-2">Order detail</p>


                                                <form class="mt-4" action="ordering" method="post">
                                                    <div class="content-scroll-block">
                                                        <div class="content">
                                                            <c:forEach items="${requestScope.orderLineList2}" var="o">
                                                                <div class="row form-outline form-white mb-4">
                                                                    <input type="text" hidden="" value="${o.product.id}">
                                                                    <img class="col-md-3" src="img_product/${o.product.image}" alt="alt" width="50px" height="50px"/>
                                                                    <p class="col-md-4">${o.product.name}</p>
                                                                    <p class="col-md-1">${o.quantity}</p>
                                                                    <input type="checkbox" name="item_id" value="${o.id}" hidden="" checked>
                                                                    <p class="col-md-4"><fmt:formatNumber value="${o.price}" pattern="#,###"/></p>
                                                                </div>               
                                                            </c:forEach>   
                                                        </div>
                                                    </div>


                                                    <hr class="my-4">
                                                    <div class="d-flex justify-content-between mb-4">
                                                        <p class="mb-2">Total</p>
                                                        <input type="text" hidden="" value="${requestScope.Total}" name="total">
                                                        <p class="mb-2"><fmt:formatNumber value="${requestScope.Total}" pattern="#,###"/>VNĐ</p>
                                                    </div>
                                                    
                                                        <button <c:if test="${requestScope.Total !=null}">type="submit"</c:if><c:if test="${requestScope.Total ==null}">type="button"</c:if> class="btn btn-info btn-block btn-lg">
                                                            <div class="d-flex justify-content-between">
                                                                <span>Checkout <i class="fas fa-long-arrow-alt-right ms-2"></i></span>
                                                            </div>
                                                        </button>
                                                    
                                                </form>
                                            </div>
                                        </div>

                                    </div>

                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script type="text/javascript">
            function doDelete(id, ) {
                if (confirm('Bạn có muốn xóa sản phẩm có mã là ' + id + ' không ?')) {
                    window.location = 'deleteItem?id=' + id;
                }
            }

            function updateQuantityUp(id, ) {
                window.location = 'process?id=' + id + '&mode=1';
            }
            function updateQuantityDown(id, ) {
                window.location = 'process?id=' + id + '&mode=0';
            }
            function getvalue(id, ) {
                window.location = 'process?orderLine=' + id;
            }
        </script>
        <jsp:include page="footer.jsp"/> 
    </body>
</html>
