<%-- 
    Document   : manaProductCategory
    Created on : Mar 18, 2024, 10:53:15 AM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>

        <div class="container">
            <div class="row flex-lg-nowrap">
                <div class="col-12 col-lg-auto mb-3" style="width: 200px;">

                </div>

                <div class="col">
                    <div class="e-tabs mb-3 px-3">
                        <ul class="nav nav-tabs">
                            <li class="nav-item"><a class="nav-link active" href="#">Brand</a></li>
                        </ul>
                    </div>

                    <div class="row flex-lg-nowrap">
                        <div class="col mb-3">
                            <div class="e-panel card">
                                <div class="card-body">
                                    <div class="card-title">
                                    </div>
                                    <div class="e-table">
                                        <div class="table-responsive table-lg mt-3">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th class="align-top">
                                                            <div class="custom-control custom-control-inline custom-checkbox custom-control-nameless m-0">

                                                                <label class="" ></label>
                                                            </div>
                                                        </th>
                                                        <th class="max-width">Name</th>                          
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${requestScope.typeLists}" var="c">
                                                        <tr>
                                                            <td class="align-middle">
                                                                <div class="custom-control custom-control-inline custom-checkbox custom-control-nameless m-0 align-top">
                                                                </div>
                                                            </td>       
                                                            <td class="text-nowrap align-middle">${c.name}</td>
                                                            <td class="text-center align-middle">
                                                                <div class="btn-group align-top">
                                                                    <a class="btn btn-sm btn-outline-dark badge" href="updateType?id=${c.id}"style="color: burlywood;">Edit</a>
                                                                    <a class="btn btn-sm btn-outline-secondary badge" href="deleteType?id=${c.id}" onclick="return confirmDelete();"><i class="fa fa-trash bg-danger"></i></a>
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
                        <div class="col-12 col-lg-3 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="text-center px-xl-3">
                                        <a class="btn btn-success btn-block" href="addType" >New Type</a>
                                    </div>
                                    <div>
                                        <div class="form-group">
                                            <form action="manaType">
                                                <label>Search by Name:</label>
                                                <div><input class="form-control w-100" type="text" name="name"></div>
                                                <input type="submit" class="btn-block" value="Search">
                                            </form>
                                        </div>                                      
                                    </div>
                                    <hr class="my-3">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function confirmDelete() {
                var result = confirm("Are you sure you want to delete this category?");
                if (result) {
                    
                    return true;
                } else {
                    
                    return false;
                }
            }
        </script>

    </body>
</html>
