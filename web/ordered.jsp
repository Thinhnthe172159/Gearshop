<%-- 
    Document   : recievedOrder
    Created on : Mar 18, 2024, 10:00:26 PM
    Author     : thinh
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.2.0/mdb.min.css"
            rel="stylesheet"
            />
        <style>
            @media (max-width: 767.98px) {
                .border-sm-start-none {
                    border-left: none !important;
                }
            }
        </style>
    </head>
    <body>
        <section style="background-color: #eee;">
            <div class="container py-5">
                <c:if test="${requestScope.OrderList == null}">
                    <h1>Bạn chưa đặt bất cứ đơn hàng nào</h1>
                    <img src="https://morimon.vn/images/empty-cart.jpg" alt="alt"/>
                </c:if>
                <c:forEach items="${requestScope.OrderList}" var="re"> 

                    <div class="row justify-content-center mb-3">                   
                        <div class="col-md-12">${re.dateOrder}<div>
                        <c:forEach items="${requestScope.oderLineList}" var="ord"> 
                            <c:if test="${ord.order.id == re.id}" >
                                <div class="col-md-12 col-xl-10">
                                    <div class="card shadow-0 border rounded-3">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-md-12 col-lg-3 col-xl-3 mb-4 mb-lg-0">
                                                    <div class="bg-image hover-zoom ripple rounded ripple-surface">
                                                        <img src="img_product/${ord.product.image}"
                                                             class="w-100" width="60px" height="60px" />
                                                        <a href="#!">
                                                            <div class="hover-overlay">
                                                                <div class="mask" style="background-color: rgba(253, 253, 253, 0.15);"></div>
                                                            </div>
                                                        </a>
                                                    </div>
                                                </div>
                                                <div class="col-md-6 col-lg-6 col-xl-6">
                                                    <h5>${ord.product.name}</h5>             
                                                </div>
                                                <div class="col-md-6 col-lg-3 col-xl-3 border-sm-start-none border-start">
                                                    <div class="d-flex flex-row align-items-center mb-1">
                                                        <h4 class="mb-1 me-1"><fmt:formatNumber value="${ord.price}" pattern="#,###"/>đ</h4>                                                      
                                                    </div>
                                                    <h6 class="text-success">quantity ${ord.quantity}</h6>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </c:forEach>
            </div>
        </section>
        <jsp:include page="footer.jsp"/> 
    </body>
</html>
