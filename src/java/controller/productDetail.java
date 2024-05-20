/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CommentDao;
import dal.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Comment;
import model.Product;

/**
 *
 * @author thinh
 */
@WebServlet(name = "productDetail", urlPatterns = {"/productDetail"})
public class productDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet productDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productDetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDao d = new ProductDao();
        CommentDao cdao = new CommentDao();
        String id_raw = request.getParameter("id");
        int id;
        try {
            id = Integer.parseInt(id_raw);
            Product p = d.getProductById(id);
            List<Product> list = d.getRelateProduct(id, p.getProductCategoty().getId());
            List<Product> productList = d.getRelateProductWithSamePrice(id, p.getPrice(), p.getProductType().getId());
            List<Comment> commentList = cdao.showCommentList(id);
            StringBuilder jsonCommentList = new StringBuilder();
            jsonCommentList.append("[");
            for (int i = 0; i < commentList.size(); i++) {
                Comment comment = commentList.get(i);
                jsonCommentList.append("{\"mess\":\"").append(comment.getMess()).append("\",\"user\":\"").append(comment.getUser().getName()).append("\"}");
                if (i < commentList.size() - 1) {
                    jsonCommentList.append(",");
                }
            }
            jsonCommentList.append("]");

            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonCommentList.toString());
            request.setAttribute("relatedProduct", list);
            request.setAttribute("productList", productList);
            request.setAttribute("productDetail", p);
            request.setAttribute("commentList", commentList);

            request.getRequestDispatcher("home.jsp").forward(request, response);
        } catch (NumberFormatException e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
