<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css" rel="stylesheet"/>
        <style>
            html, body {
                height: 100%;
                width: 100%;
                margin: 0;
                font-family: 'Roboto', sans-serif;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 15px;
                display: flex;
            }

            .left-column {
                width: 65%;
                position: relative;
            }

            .left-column img {
                width: 100%;
                position: absolute;
                left: 0;
                top: 0;
                opacity: 0;
                transition: all 0.3s ease;
            }

            .left-column img.active {
                opacity: 1;
            }

            .right-column {
                width: 35%;
                margin-top: 60px;
            }

            .product-description {
                border-bottom: 1px solid #E1E8EE;
                margin-bottom: 20px;
            }

            .product-description span {
                font-size: 12px;
                color: #358ED7;
                letter-spacing: 1px;
                text-transform: uppercase;
                text-decoration: none;
            }

            .product-description h1 {
                font-weight: 300;
                font-size: 52px;
                color: #43484D;
                letter-spacing: -2px;
            }

            .product-description p {
                font-size: 16px;
                font-weight: 300;
                color: #86939E;
                line-height: 24px;
            }

            .product-price {
                display: flex;
                align-items: center;
            }

            .product-price span {
                font-size: 26px;
                font-weight: 300;
                color: #43474D;
                margin-right: 20px;
            }

            .cart-btn {
                display: inline-block;
                background-color: #7DC855;
                border-radius: 6px;
                font-size: 16px;
                color: #FFFFFF;
                text-decoration: none;
                padding: 12px 30px;
                transition: all .5s;
            }

            .cart-btn:hover {
                background-color: #64af3d;
            }

            @media (max-width: 940px) {
                .container {
                    flex-direction: column;
                    margin-top: 60px;
                }

                .left-column,
                .right-column {
                    width: 100%;
                }

                .left-column img {
                    width: 300px;
                    right: 0;
                    top: -65px;
                    left: initial;
                }
            }
        </style>
        <style>
            .scrollable {
                width: max; /* Đặt chiều rộng của thanh cuộn */
                height: 500px; /* Đặt chiều cao của thanh cuộn */
                overflow-y: scroll; /* Tạo thanh cuộn dọc */
                border: 1px solid #ccc; /* Viền */
                padding: 10px; /* Khoảng cách bên trong */
            }
        </style>
    </head>
    <body>
        <c:set var="p" value="${requestScope.productDetail}" />
        <main class="container">
            <!-- Left Column -->
            <div class="left">
                <a target="_blank" href="img_product/${p.image}">
                    <img src="img_product/${p.image}" height="650px" width="650px">
                </a>
            </div>

            <!-- Right Column -->
            <div class="right-column">
                <!-- Product Description -->
                <div class="product-description">
                    <span>${p.productType.name}</span>
                    <h1>${p.name}</h1>
                    <p>${p.description}</p>
                </div>

                <!-- Product Pricing -->
                <div class="product-price">
                    <p class="card-text" style="text-decoration: line-through;color: red;"><fmt:formatNumber value="${p.price+(0.15*p.price)}" pattern="#,###"/> </p>
                    <span><fmt:formatNumber value="${p.price}" pattern="#,###"/>VNĐ</span>
                    <a href="addToCart?id=${p.id}" class="cart-btn">Add to cart</a>
                </div>
            </div>
        </main>

        <div class="row justify-content-center" style="border: 1px solid #E1E8EE; padding: 10px;">
            <h3 class="text-center">Bình luận sản phẩm</h3>

            <c:if test="${empty requestScope.commentList}">
                Chưa có bình luận nào, bạn sẽ là người bình luận đầu tiên.
            </c:if>




            <div class=" row d-flex justify-content-center">
                <div class="col-md-8 col-lg-6">
                    <div class="card shadow-0 border" style="background-color: #f0f2f5;">
                        <div class="card-body p-4">
                            <form action="process_comment"  method="post" onsubmit="loadNewComments()">
                                <div data-mdb-input-init class="form-outline mb-4">
                                    <input type="hidden" value="${p.id}" name="id">
                                    <input type="text" id="addANote" name="mess" class="form-control" placeholder="Type comment..." required=""/>
                                    <label class="form-label" for="addANote">+ Add a note</label>
                                    <input type="submit" value="send" onclick="loadNewComments()">
                                </div>
                            </form>
                            <div class="scrollable">
                                <c:forEach items="${requestScope.commentList}" var="comment">                
                                    <div class="card mb-4">
                                        <div class="card-body">
                                            <p>${comment.mess}</p>

                                            <div class="d-flex justify-content-between">
                                                <div class="d-flex flex-row align-items-center">
                                                    <img src="${comment.user.image}" alt="avatar" width="25"
                                                         height="25" />
                                                    <p class="small mb-0 ms-2">${comment.user.name}</p>
                                                </div>
                                                <div class="d-flex flex-row align-items-center">
                                                    
                                                    
                                                   
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>  
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- Container for Related Products -->
        <div class="row justify-content-center" style="border: 1px solid #E1E8EE; padding: 10px;">
            <h3 class="text-center">Sản phẩm cùng hãng khác</h3>
            <c:forEach items="${requestScope.relatedProduct}" var="po">
                <a class="col-md-2 justify-content-center" style="border: 1px solid #E1E8EE; margin: 10px;" href="productDetail?id=${po.id}">
                    <img class="col-md-12" src="img_product/${po.image}" alt="alt"/>
                    <p class="col-md-12">${po.name}</p>
                    <p  style="text-decoration: line-through;color: red;"><fmt:formatNumber value="${po.price+(0.15*po.price)}" pattern="#,###"/> </p>
                    <p><fmt:formatNumber value="${po.price}" pattern="#,###"/>VNĐ</p>
                </a>
            </c:forEach>
        </div>
        <div class="row justify-content-center" style="border: 1px solid #E1E8EE; padding: 10px;">
            <h3 class="text-center">Sản phẩm cùng phân khúc giá</h3>
            <c:forEach items="${requestScope.productList}" var="po">
                <a class="col-md-2 justify-content-center" style="border: 1px solid #E1E8EE; margin: 10px;" href="productDetail?id=${po.id}">
                    <img class="col-md-12" src="img_product/${po.image}" alt="alt"/>
                    <p class="col-md-12">${po.name}</p>
                    <p  style="text-decoration: line-through;color: red;"><fmt:formatNumber value="${po.price+(0.15*po.price)}" pattern="#,###"/> </p>
                    <p><fmt:formatNumber value="${po.price}" pattern="#,###"/>VNĐ</p>
                </a>
            </c:forEach>
        </div>
        <script>
            function loadNewComments() {
                var xhr = new XMLHttpRequest();
                xhr.open('GET', 'refreshComments?id=${p.id}', true); // Đảm bảo bạn truyền ID sản phẩm vào đường dẫn
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        var comments = JSON.parse(xhr.responseText);
                        var commentList = document.getElementById('comment-list');

                        // Xóa các bình luận cũ
                        commentList.innerHTML = '';

                        // Thêm các bình luận mới vào danh sách
                        comments.forEach(function (comment) {
                            var commentItem = document.createElement('div');
                            commentItem.textContent = comment.mess;
                            commentList.appendChild(commentItem);
                        });
                    }
                };
                xhr.send();
            }

            setInterval(loadNewComments, 5000); // Ví dụ: cập nhật mỗi 5 giây

        </script>
        <jsp:include page="footer.jsp"/> 
    </body>
</html>
