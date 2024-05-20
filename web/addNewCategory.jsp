<%-- 
    Document   : addNewCategory
    Created on : Mar 18, 2024, 12:41:37 PM
    Author     : thinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="background: white;">
        <jsp:include page="admin.jsp"/>
        <form class="row justify-content-cente" action="addCategory" method="post">
            <div class="col-md-5">     
                <div class="input-group input-group-sm mb-3 ">
                    <span class="input-group-text" id="inputGroup-sizing-sm">Enter name category</span>
                    <input name="name" type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm" name="name">
                </div>
                <select class="form-select" aria-label="Default select example" name="tid">
                    <c:forEach items="${requestScope.typeList}" var="t">
                        <option value="${t.id}">${t.name}</option>
                    </c:forEach>
                </select>
                <div class="input-group input-group-sm mb-3 ">
                    <input type="submit" class="btn-block"  value="Save">
                </div>
            </div>

        </form>
    </body>
</html>
