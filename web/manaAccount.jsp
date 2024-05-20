<%-- 
    Document   : manaAccount
    Created on : Mar 13, 2024, 2:45:20 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Bootstrap User Management Data Table</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                min-width: 1000px;
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 15px;
                background: #299be4;
                color: #fff;
                padding: 16px 30px;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn {
                color: #566787;
                float: right;
                font-size: 13px;
                background: #fff;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn:hover, .table-title .btn:focus {
                color: #566787;
                background: #f2f2f2;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.settings {
                color: #2196F3;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .status {
                font-size: 30px;
                margin: 2px 2px 0 0;
                display: inline-block;
                vertical-align: middle;
                line-height: 10px;
            }
            .text-success {
                color: #10c469;
            }
            .text-info {
                color: #62c9e8;
            }
            .text-warning {
                color: #FFC107;
            }
            .text-danger {
                color: #ff5b5b;
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
        <script>
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
            });
        </script>
    </head>
    <body>
        <div class="container">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-5">
                                <h2>User <b>Management</b></h2>
                            </div>
                            <div class="col-sm-7">

                                <form class="row" action="managerAccount">
                                    <input class="col-md-4 input-group-text" placeholder="Enter name user" name="name"> 
                                    <button class="col-md-2 btn-lg" type="submit">Find</button>
                                    <div class="col-md-1"></div>
                                    <div class="form-check  col-md-1">
                                        <input name="sort" value="1" class="form-check-input" type="checkbox" id="flexSwitchCheckDefault">
                                        <label class="form-check-label" for="flexSwitchCheckDefault">...A-..Z</label>
                                    </div>
                                    <div class="col-md-1"></div>
                                    <div class="form-check  col-md-1">

                                        <input name="sort" value="2"class="form-check-input" type="checkbox" id="flexSwitchCheckChecked" checked>
                                        <label class="form-check-label" for="flexSwitchCheckChecked">...Z-..A</label>
                                    </div>
                                </form>
                            </div>

                        </div>
                        <c:set var="name" value="${requestScope.name}"/>
                        <c:if test="${requestScope.userlist != null}">
                            <div class="pagination">
                                <c:set var="page" value="${requestScope.page}"/>
                                <a style="color: white;" href="managerAccount?page=${1}">&laquo;</a>
                                <c:forEach begin="${1}" end="${requestScope.num}" var="i">
                                    <a style="color: white;" class="${i==page?"active":""}" href="managerAccount?page=${i}">${i}</a>
                                </c:forEach> 

                                <a style="color: white;" href="managerAccount?page=${num}">&raquo;</a>
                            </div>
                        </c:if>
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>						
                                    <th>Phone</th>
                                    <th>Email</th>
                                    <th>City</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="num" value="${0}"/>
                                <c:forEach items="${requestScope.userlist}" var="u">
                                    <tr>
                                        <td>${num+1}</td>
                                        <td><a href="#"><img src="${u.image}" class="avatar" alt="Avatar" width="60px" heigh="60px"> ${u.name}</a></td>
                                        <td>${u.phone}</td>                        
                                        <td>${u.email}</td>
                                        <td><span class="status text-success">&bull;</span> ${u.address}</td>
                                        <td>
                                            <a href="updateAccount?id=${u.id}" class="settings" title="Settings" data-toggle="tooltip"><i class="material-icons">&#xE8B8;</i></a>
                                            <a href="#" onclick="doDelete('${u.id}')" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE5C9;</i></a>
                                        </td>
                                    </tr>  
                                </c:forEach>


                            </tbody>
                        </table>
                        <div class="clearfix">
                            <div class="hint-text"> <b>${requestScope.accountList.size()}</b> entries</div>
                        </div>
                    </div>
                </div>
            </div>     
            <script>
                document.addEventListener("DOMContentLoaded", function () {
                    var checkboxAtoZ = document.getElementById("flexSwitchCheckDefault");
                    var checkboxZtoA = document.getElementById("flexSwitchCheckChecked");

                    checkboxAtoZ.addEventListener("change", function () {
                        if (this.checked) {
                            checkboxZtoA.checked = false;
                        }
                    });

                    checkboxZtoA.addEventListener("change", function () {
                        if (this.checked) {
                            checkboxAtoZ.checked = false;
                        }
                    });
                });
            </script>
            <script type="text/javascript">
                function doDelete(id, ) {
                    if (confirm('Bạn có muốn xóa người dùng có id ' + id + ' không ?')) {
                        window.location = 'deleteAccount?id=' + id + '&page=${requestScope.page}';
                    }
                }
            </script>
    </body>
</html>