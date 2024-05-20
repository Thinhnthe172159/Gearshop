<%-- 
    Document   : chart
    Created on : Mar 19, 2024, 5:58:46 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    </head>
    <body style="color:">


        <div class="row">
            <div class="col-lg-12  text-center">
                <h1>Statistic ${requestScope.year1}</h1>
            </div>
            <div class="col-md-8">
                <div class="dropdown">
                    <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        --------------------Year---------------------- 
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="statistic?year=2024">2024</a></li>
                        <li><a class="dropdown-item" href="statistic?year=2023">2023</a></li>
                        <li><a class="dropdown-item" href="statistic?year=2022">2022</a></li>
                        <li><a class="dropdown-item" href="#">...</a></li>
                    </ul>
                </div>
                <canvas id="barChart" style="max-width: 1200px;"></canvas>  
                <h2>Total income this year is :<fmt:formatNumber value="${requestScope.totalIncome1}" pattern="#,###"/>VNĐ</h2>
            </div>

            <div class="col-md-4">
                <canvas id="myChart" style="width:100%;max-width:1000px"></canvas>
            </div>
            <hr>
            <div class="col-md-12">
                <canvas id="myChart2" style="width:100%;max-width:1000px"></canvas>
            </div>
            <hr>
            <div class="col-md-6">
                <div class="event-schedule-area-two bg-color pad100">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="section-title text-center">
                                    <div class="title-text">
                                        <h2>Top 5 product of ${requestScope.year1}</h2>
                                    </div>
                                </div>
                            </div>
                            <!-- /.col end-->
                        </div>
                        <!-- row end-->
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="tab-content" id="myTabContent">
                                    <div class="tab-pane fade active show" id="home" role="tabpanel">
                                        <div class="table-responsive">
                                            <table class="table">
                                                <thead>
                                                    <tr>

                                                        <th scope="col">Product</th>
                                                        <th scope="col">Name</th>
                                                        <th scope="col">Brand</th>
                                                        <th scope="col">type</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.productListBestSale1}" var="po"> 
                                                        <tr class="inner-box">
                                                            <td>
                                                                <div class="event-img">
                                                                    <img src="img_product/${po.image}" alt="" width="100px" />
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="event-wrap">
                                                                    <h3>${po.name}</h3>
                                                                    <div class="meta">
                                                                    </div>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="r-no">
                                                                    <span>${po.productCategoty.name}</span>
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <div class="r-no">
                                                                    <span>${po.productType.name}</span>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <!-- /col end-->
                        </div>
                        <!-- /row end-->
                    </div>
                </div> 
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title text-center">
                            <div class="title-text">
                                <h2>Top 5 Buyer of ${requestScope.year1}</h2>
                            </div>
                        </div>
                    </div>
                    <!-- /.col end-->
                </div>
                <div class="col-lg-12">
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade active show" id="home" role="tabpanel">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>

                                            <th scope="col">User</th>
                                            <th scope="col">user name</th>
                                            <th scope="col">emal</th>
                                            <th scope="col">address</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.TopBuyer1}" var="u"> 
                                            <tr class="inner-box">
                                                <td>
                                                    <div class="event-img">
                                                        <img src="${u.image}" alt="" width="100px" />
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="event-wrap">
                                                        <h3>${u.name}</h3>
                                                        <div class="meta">
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="r-no">
                                                        <span>${u.email}</span>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="r-no">
                                                        <span>${u.address}</span>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>




        <script>

            var xValues = [];
            <c:forEach items="${requestScope.productTypeList1}" var="p">
            xValues.push("${p.name}");
            </c:forEach>
            var yValues = [];
            <c:forEach items="${requestScope.quantityList1}" var="p">
            yValues.push(${p});
            </c:forEach>
            var barColors = [
                "#b91d47",
                "#00aba9",
                "#2b5797",
                "#e8c3b9",
                "#1e7145"
            ];
            new Chart("myChart", {
                type: "pie",
                data: {
                    labels: xValues,
                    datasets: [{
                            backgroundColor: barColors,
                            data: yValues
                        }]
                },
                options: {
                    title: {
                        display: true,
                        text: "All quantity product of each type"
                    }
                }
            });
        </script>
        <script>
            var ctx = document.getElementById('barChart').getContext('2d');
            var incomeData = [];
            <c:forEach items="${requestScope.dateOrderList1}" var="order">
            incomeData.push(${order.income});
            </c:forEach>

            var myChart = new Chart(ctx, {
                type: 'line',
                data: {
                    labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                    datasets: [{
                            label: 'Thu nhập',
                            data: incomeData,
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
        <script>
            const xValues2 = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
            const yValues2 = [];
            <c:forEach items="${requestScope.dateOrderList1}" var="ord">
            yValues2.push(${ord.quantity});
            </c:forEach>
            const barColors2 = ["red", "green", "blue", "orange", "brown", "red", "green", "blue", "orange", "brown", "red", "green"];
            new Chart("myChart2", {
                type: "bar",
                data: {
                    labels: xValues2,
                    datasets: [{
                            backgroundColor: barColors2,
                            data: yValues2
                        }]
                },
                options: {
                    legend: {display: false},
                    title: {
                        display: true,
                        text: "World Wine Production 2018"
                    }
                }
            });
        </script>



    </body>
</html>
