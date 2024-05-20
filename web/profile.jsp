<%-- 
    Document   : profile
    Created on : Mar 5, 2024, 1:57:41 PM
    Author     : thinh
--%>

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
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css"
            rel="stylesheet"
            />
        <style>
            .gradient-custom {
                /* fallback for old browsers */
                background: #f6d365;

                /* Chrome 10-25, Safari 5.1-6 */
                background: -webkit-linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1));

                /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
                background: linear-gradient(to right bottom, rgba(246, 211, 101, 1), rgba(253, 160, 133, 1))
            }
            input {
                width: 300px;
                box-shadow:none;
                border: 0;
                outline:none;
            }

            input:focus:invalid {
                box-shadow:none;
                border: 2px solid red !important;
                outline:none;
            }
        </style>
    </head>
    <body>
        <form action="updateUser" method="post">
            <section class="vh-150" style="background-color: #f4f5f7;">
                <c:set var="po" value="${requestScope.user}"/>
                <div class="container py-5 h-100">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col col-lg-10 mb-4 mb-lg-0">
                            <div class="card mb-3" style="border-radius: .5rem;">
                                <div class="row g-0">
                                    <div class="col-md-4 gradient-custom text-center text-white"
                                         style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">


                                        <c:if test="${requestScope.fix == null}">
                                            <img src="${po.image}" alt="Avatar" class="img-fluid my-5" style="width: 200px;border-radius: 4%;" />
                                        </c:if>
                                        <c:if test="${requestScope.fix != null}">
                                            <img src="${sessionScope.user.image}" alt="Avatar" class="img-fluid my-5" style="width: 200px;border-radius: 4%;"/>
                                            <input  type="url" name="image" value="${sessionScope.user.image}" style="background: #fabd73;">img url<hr>
                                        </c:if>
                                        <c:if test="${requestScope.fix == null}">
                                            <h5>User ${po.username}</h5>
                                            <p>name ${po.name}</p>
                                        </c:if>


                                        <input type="text" value="${sessionScope.user.id}" hidden="" name="id">
                                        <c:if test="${requestScope.fix != null}">
                                            <input style="background: #fabd73;" type="text" name="name" value="${sessionScope.user.name}">name<hr>
                                        </c:if>
                                        <c:if test="${requestScope.fix == null}">
                                            <a href="updateUser?id${po.id}&fix=1"><i class="far fa-edit mb-5"></i></a>
                                            </c:if>
                                            <c:if test="${requestScope.fix != null}">
                                            <button type="submit" class="btn btn-light" data-mdb-ripple-init data-mdb-ripple-color="dark">Save</button>
                                            <a class="btn-block" href="profile?id=${requestScope.user.id}">cancel</a></br>
                                        </c:if>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body p-4">
                                            <h6>Information</h6>
                                            <hr class="mt-0 mb-4">
                                            <div class="row pt-1">
                                                <div class="col-6 mb-3">
                                                    <h6>Email</h6>
                                                    <c:if test="${requestScope.fix == null}">
                                                        <p class="text-muted">${po.email}</p>
                                                    </c:if>
                                                    <c:if test="${requestScope.fix != null}">
                                                        <input type="text" value="${sessionScope.user.email}" name="email"><hr>
                                                    </c:if>

                                                </div>
                                                <div class="col-6 mb-3">
                                                    <h6>Phone</h6>
                                                    <c:if test="${requestScope.fix == null}">
                                                        <p class="text-muted">${po.phone}</p>
                                                    </c:if>
                                                    <c:if test="${requestScope.fix != null}">
                                                        <input type="text" value="${sessionScope.user.phone}" name="phone"><hr>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <c:if test="${requestScope.fix == null}">
                                                <h6>Username</h6><p>${sessionScope.user.username}</p>
                                            </c:if>
                                            <c:if test="${requestScope.fix != null}">
                                                <h6>Username</h6><input type="text" value="${sessionScope.user.username}" name="username">
                                            </c:if>
                                            <hr class="mt-0 mb-4">
                                            <div class="row pt-1">
                                                <c:if test="${requestScope.fix == null}">
                                                    <div class="col-6 mb-3">

                                                        <h6>Password</h6>
                                                        <p class="text-muted">**********</p>

                                                    </div> </c:if>
                                                <c:if test="${requestScope.fix != null}">
                                                    <div class="col-6 mb-3">

                                                        <h6>Password</h6>
                                                        <input type="text" name="password" value="${sessionScope.user.password}">

                                                    </div>
                                                </c:if>
                                                <div class="col-6 mb-3">
                                                    <h6>Address</h6>
                                                    <c:if test="${requestScope.fix == null}">
                                                        <p class="text-muted">${sessionScope.user.address}</p>
                                                    </c:if>
                                                    <c:if test="${requestScope.fix != null}">
                                                        <input type="text" name="address" value="${sessionScope.user.address}"><hr>
                                                    </c:if>

                                                </div>
                                            </div>
                                            <div class="d-flex justify-content-start">
                                                <a href="#!"><i class="fab fa-facebook-f fa-lg me-3"></i></a>
                                                <a href="#!"><i class="fab fa-twitter fa-lg me-3"></i></a>
                                                <a href="#!"><i class="fab fa-instagram fa-lg"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>
        <jsp:include page="footer.jsp"/> 
    </body>
</html>
