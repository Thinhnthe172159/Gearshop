<%-- 
    Document   : manaOrdering.jsp
    Created on : Mar 19, 2024, 9:13:58 AM
    Author     : thinh
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .scrollable {
                width: 835px; /* Độ rộng của vùng cuộn */
                height: 500px; /* Chiều cao của vùng cuộn */
                overflow: auto; /* Tạo thanh cuộn khi nội dung vượt quá kích thước */
                border: 1px solid #ccc; /* Đường viền cho vùng cuộn */
                padding: 10px; /* Khoảng cách bên trong vùng cuộn */
            }
        </style>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <div class="container-fluid p-0">
                <h1 class="h3 mb-3 text-light">Clients</h1>
                <div class="row">
                    <div class="col-xl-8">
                        <div class="card">
                            <div class="card-header pb-0">
                                <div class="card-actions float-right">
                                    <div class="dropdown show">
                                        <a href="#" data-toggle="dropdown" data-display="static">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-horizontal align-middle"><circle cx="12" cy="12" r="1"></circle><circle cx="19" cy="12" r="1"></circle><circle cx="5" cy="12" r="1"></circle></svg>
                                        </a>

                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="#">Action</a>
                                            <a class="dropdown-item" href="#">Another action</a>
                                            <a class="dropdown-item" href="#">Something else here</a>
                                        </div>
                                    </div>
                                </div>                              
                            </div>
                            <div class="card-body">
                                <h2>Clients</h2><div class="scrollable">
                                    <table class="table table-striped" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>Name</th>
                                                <th>Date order</th>
                                                <th>Status</th>
                                                <th>Detail</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead> 
                                        <tbody>


                                            <c:forEach items="${requestScope.orderListNeedConfirm}" var="od">
                                                <tr>
                                                    <td><img src="${od.user.image}" width="32" height="32" class="rounded-circle my-n1" alt="Avatar"></td>
                                                    <td>${od.user.name}</td>
                                                    <td>${od.dateOrder}</td>
                                                    <td><span class="badge bg-success">Ordering</span></td>
                                                    <td><a class="btn-block" href="manaOrdering?id=${od.id}">More detail</a></td>
                                                    <td><a class="btn-block" href="manaOrdering?id_deleted_raw=${od.id}">delete order</td>
                                                </tr>  
                                            </c:forEach>


                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-xl-4" style="z-index: 2;">
                        <div class="card">
                            <div class="card-header">
                                <div class="card-actions float-right">
                                    <div class="dropdown show">
                                        <a href="#" data-toggle="dropdown" data-display="static">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-horizontal align-middle"><circle cx="12" cy="12" r="1"></circle><circle cx="19" cy="12" r="1"></circle><circle cx="5" cy="12" r="1"></circle></svg>
                                        </a>

                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="#">Action</a>
                                            <a class="dropdown-item" href="#">Another action</a>
                                            <a class="dropdown-item" href="#">Something else here</a>
                                        </div>
                                    </div>
                                </div>
                                <h5 class="card-title mb-0">${requestScope.Orderdetail.user.username}</h5>
                            </div>
                            <c:if test="${requestScope.Orderdetail!=null}"> 
                                <div class="card-body">
                                    <div class="row g-0">
                                        <div class="col-sm-3 col-xl-12 col-xxl-3 text-center">
                                            <img src="${requestScope.Orderdetail.user.image}" width="64" height="64" class="rounded-circle mt-2" alt="Angelica Ramos">
                                        </div>
                                        <div class="col-sm-9 col-xl-12 col-xxl-9">

                                        </div>
                                    </div>

                                    <table class="table table-sm mt-2 mb-4">
                                        <tbody>
                                            <tr>
                                                <th>Name</th>
                                                <td>${requestScope.Orderdetail.user.name}</td>
                                            </tr>
                                            <tr>
                                                <th>Email</th>
                                                <td>${requestScope.Orderdetail.user.email}</td>
                                            </tr>
                                            <tr>
                                                <th>Phone</th>
                                                <td>${requestScope.Orderdetail.user.phone}</td>
                                            </tr>
                                            <tr>
                                                <th>Status</th>
                                                <td><span class="badge bg-warning">Ordering</span></td>
                                            </tr>
                                            <tr>
                                                <th>Action</th>
                                                <td><span class="badge bg-warning"> <a href="manaOrdering?id_delete=${requestScope.Orderdetail.id}"></a> </span></td>
                                            </tr>
                                        </tbody>
                                    </table>

                                    <strong>Activity</strong>

                                    <ul class="timeline mt-2 mb-0">
                                        <c:set var="total" value="0"/>
                                        <c:forEach items="${requestScope.OrderdetailList}" var="ord">
                                            <li class="timeline-item">(${ord.quantity} item) ${ord.product.name} |<fmt:formatNumber value="${ord.quantity * ord.product.price}" pattern="#,###"/>VNĐ</li> 
                                            <c:set var="total2" value="${total + (ord.quantity * ord.product.price)}"/>
                                        </c:forEach>

                                    </ul>
                                    <hr>
                                    <form action="manaOrdering?id=${requestScope.OrderId}" method="post">
                                        <button class="btn btn-primary" type="submit">Finish order ${total2}</button>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </body>
</html>
