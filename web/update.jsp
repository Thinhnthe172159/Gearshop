<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Product</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

        <!-- Custom Styles -->
        <style>
            body {
                background-color: #f8f9fa;
            }

            .container {
                margin-top: 50px;
            }

            .panel-body {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                font-weight: bold;
            }

            button.btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }

            .img-container {
                text-align: center;
                margin-bottom: 20px;
            }

            .img-container img {
                max-width: 100%;
                height: auto;
            }
        </style>
        <script type="text/javascript" src="LIBARY/ckeditor/ckeditor.js"></script>
    </head>

    <body>
        <jsp:include page="admin.jsp"/>
        <div class="container">
            <section class="panel panel-default">
                <div class="panel-body">
                    <form action="update" method="post" class="form-horizontal">
                        <a style="" href="managerProduct?page=${requestScope.page}"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/BackButton.svg/2048px-BackButton.svg.png" width="50px" alt="alt"/></a>
                        <div class="img-container">
                            <c:if test="${requestScope.success != null}">
                                <img src="https://cdn.dribbble.com/users/1303437/screenshots/3686809/success_400x300.gif"
                                     alt="Success Image" height="150px" width="200px" />
                                <h1 style="color: #0062cc">${requestScope.success}</h1>
                            </c:if>
                            <c:if test="${requestScope.fail != null}">
                                <img src="https://i.pinimg.com/originals/6e/f9/f2/6ef9f2fd6425c578274e72ce1f44a778.gif"
                                     alt="Fail Image" height="150px" width="200px" />
                                <h1 style="color: red">${requestScope.fail}</h1>
                            </c:if>
                        </div>
                        <div class="form-group row">
                            <label for="type" class="col-sm-4 col-form-label">Mã ${requestScope.products.id}</label>
                            <label for="type" class="col-sm-4 col-form-label">Chọn kiểu loại sản phẩm</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="type" required="">
                                    <option selected style="background-color: red;color: white;" value="${requestScope.products.productType.id}"> ${requestScope.products.productType.name} </option>
                                    <c:forEach items="${requestScope.productTypeList}" var="pt">
                                        <option value="${pt.id}">${pt.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <input type="text" name="id" value="${requestScope.products.id}" hidden="">
                        <input type="text" name="page" value="${requestScope.page}" hidden="">


                        <!-- Rest of your form remains unchanged -->
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Thêm sản phẩm</label>
                        </div> <!-- form-group // -->

                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Tên sản phẩm</label>
                            <div class="col-sm-9">
                                <input type="text" required="" class="form-control" name="name" value="${requestScope.products.name}" id="name"
                                       placeholder="Điền tên sản phẩm">
                            </div>
                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Đường dẫn ảnh</label>
                            <div class="col-sm-9">
                                <input type="text" required="" class="form-control" name="image" value="${requestScope.products.image}" id="name"
                                       placeholder="Điền đường dẫn ảnh sản phẩm">
                                <label class="control-label small" for="file_img">tải file ảnh (jpg/png):</label> <input type="file"
                                                                                                                         name="img">
                            </div>
                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label for="about" class="col-sm-3 control-label">Mô tả</label>
                            <div class="col-sm-9">
                                 <textarea id="editor" name="describe" rows="5" cols="100">${requestScope.products.description}</textarea>
<!--                                <input type="text" class="form-control" name="describe" value="${requestScope.products.description}" placeholder="Mô tả sản phẩm"/>-->
                            </div>
                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label for="qty" class="col-sm-3 control-label">Giá tiền của sản phẩm</label>
                            <div class="col-sm-3">
                                <input type="text" required="" class="form-control" value="${requestScope.products.price}" name="price" id="qty"
                                       placeholder="Điền giá tiền sản phẩm">
                            </div>
                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label class="col-sm-3 control-label">Số lượng của sản phẩm</label>
                            <div class="col-sm-3">
                                <input type="text" required="" class="form-control" value="${requestScope.products.quantity}" name="quantity" id="date_start"
                                       placeholder="Nhập số lượng sản phẩm">
                            </div>
                        </div> <!-- form-group // -->
                        <div class="form-group">
                            <label for="tech" class="col-sm-3 control-label">Chọn thương hiệu sản phẩm</label>
                            <div class="col-sm-3">
                                <select class="form-control" name="category" required="">
                                    <option selected style="background-color: red;color: white;" value="${requestScope.products.productCategoty.id}">${requestScope.products.productCategoty.name}---${requestScope.products.productType.name}</option>
                                    <c:forEach items="${requestScope.productCategoryList}" var="p">
                                        <option value="${p.id}">${p.name}---${p.productType.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-9 offset-sm-3">
                                    <button type="submit" class="btn btn-primary">Lưu sản phẩm</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div><!-- panel-body // -->
            </section><!-- panel// -->
        </div> <!-- container// -->
        <script>
            // Initialize CKEditor
            CKEDITOR.replace('editor');
        </script>

        <script src="https://code.jquery.com/jquery-3.6.4.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
                integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

    </body>

</html>