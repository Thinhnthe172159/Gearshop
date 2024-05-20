/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dal.ProductCategoryDao;
import dal.ProductDao;
import dal.ProductTypeDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;
import model.ProductCategory;
import model.ProductType;

/**
 *
 * @author thinh
 */
@WebServlet(name = "addProduct", urlPatterns = {"/addProduct"})
public class addProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductTypeDao ptd = new ProductTypeDao();
        ProductCategoryDao pcd = new ProductCategoryDao();

        List<ProductType> productTypeList = ptd.getAll();
        List<ProductCategory> productCategoryList = pcd.getAll();
        request.setAttribute("productTypeList", productTypeList);
        request.setAttribute("productCategoryList", productCategoryList);

        request.getRequestDispatcher("addProduct.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String image = request.getParameter("image");

        String describe = request.getParameter("describe");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String product_category_id = request.getParameter("category");
        String product_type_id = request.getParameter("type");

        ProductTypeDao ptd = new ProductTypeDao();
        ProductCategoryDao pcd = new ProductCategoryDao();
        ProductDao pd = new ProductDao();

        List<ProductType> productTypeList = ptd.getAll();
        List<ProductCategory> productCategoryList = pcd.getAll();
        List<Product> productList = pd.getAll();
        

        int quantities, cid, ptid;
        double price_product;
        try {
            quantities = (quantity == null) ? 0 : Integer.parseInt(quantity);

            ProductCategory pc;
            if (product_category_id == null) {
                pc = null;
            } else {
                cid = Integer.parseInt(product_category_id);
                pc = pcd.getCategoryById(cid);
            }
            ProductType pt;

            if (product_type_id == null) {
                pt = null;
            } else {
                ptid = Integer.parseInt(product_type_id);
                pt = ptd.getProductTypeById(ptid);
            }

            if (price == null || price.isEmpty()) {
                price_product = 0;
            } else {
                price_product = Double.parseDouble(price);
            }

            Product p = new Product(0, describe, image, name, price_product, quantities, pc, pt);
            if (pd.searchProductByName(p.getName()) == null) {
                pd.InputProduct(p);
                request.setAttribute("success", "Sản phẩm của bạn đã được thêm thành công!");
            }else{
                 request.setAttribute("fail", "Thêm sản phẩm thất bại!, Sản phẩm đã tồn tại trước đó!");
            }

        } catch (NumberFormatException e) {

        }
        request.setAttribute("productTypeList", productTypeList);
        request.setAttribute("productCategoryList", productCategoryList);
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
    }

}
