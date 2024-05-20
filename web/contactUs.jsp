<%-- 
    Document   : contactUs
    Created on : Mar 20, 2024, 9:00:24 AM
    Author     : thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="home.jsp"/>
        


        <div class="container">
            <div class="lienhe">
                <h1 style="
                    font-family: auto;
                    text-align: center;
                    margin: 60px 0px;
                    color: blue;">Thông tin liên hệ
                </h1>
                <ul style="margin-left: 100px; list-style: none; color: black" class="lienhe_ul">
                    <li style="color: gray;"><i class="bi bi-shop"></i>   Ng. 82 P. Duy Tân, Dịch Vọng Hậu, Cầu Giấy, Hà Nội 85000</li>
                    <li><i class="bi bi-telephone"></i> <a href="tel:0969792742" title="0969792742" style="color: gray;">   0123456789</a></li>
                    <li><i class="bi bi-envelope"></i> <a href="mailto:hezz281103@gmail.com" title="hezz281103@gmail.com" style="color: gray;">   player7377@gmail.com</a></li>
                </ul> 
            </div>
            <div class="col-md-12 justify-content-center">
                <p>Steff là địa chỉ đáng tin cậy cho những người đam mê công nghệ và muốn tạo ra không gian làm việc hoặc giải trí trên máy tính đẳng cấp và tiện ích. Với sứ mệnh cung cấp các sản phẩm chất lượng cao và đa dạng, Steff tự hào là điểm đến lý tưởng cho những ai đang tìm kiếm các giải pháp về bàn phím, chuột và cấu hình góc làm việc.

                    Bên cạnh việc cung cấp các loại bàn phím chất lượng cao từ các thương hiệu uy tín, Steff còn mang đến cho khách hàng một bộ sưu tập đa dạng các loại chuột, bàn di chuột và các sản phẩm dùng để setup góc làm việc, như các loại giá đỡ máy tính, kệ đỡ màn hình, bàn làm việc, ghế chơi game và nhiều hơn nữa.

                    Khách hàng khi đến với Steff sẽ được trải nghiệm không gian mua sắm thoải mái và thân thiện, cùng với sự hỗ trợ nhiệt tình và chuyên nghiệp từ đội ngũ nhân viên giàu kinh nghiệm. Steff cam kết luôn đồng hành cùng khách hàng từ quá trình chọn lựa sản phẩm cho đến sau khi mua hàng, đảm bảo mọi nhu cầu và mong muốn của khách hàng được đáp ứng tối đa.

                    Với Steff, không chỉ là việc cung cấp các sản phẩm công nghệ, mà còn là việc tạo ra không gian làm việc và giải trí đầy phong cách và tiện ích cho mọi người. Chúng tôi tin rằng, việc sở hữu những sản phẩm từ Steff sẽ mang lại trải nghiệm tuyệt vời và nâng cao hiệu suất làm việc cũng như trải nghiệm giải trí của bạn.</p>
            </div>
            <div class="col-md-12">
                <div class="contact-map">
                    <div class="page-login text-center">
                        <h1 class="title-head" style="
                            font-family: auto;
                            margin: 40px 0px;
                            color: blue;
                            ">Đia chỉ cửa hàng</h1>
                    </div>

                </div>

            </div>
            <div class="col-md-12">
                <iframe style="width: 100%" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14895.728959127371!2d105.77495094853093!3d21.035397089846285!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab9ec0903dc3%3A0x6b8a399004974321!2sICSP%20Vietnam!5e0!3m2!1svi!2s!4v1710900197877!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.2/dist/chart.umd.min.js"></script>
