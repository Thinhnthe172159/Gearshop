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
@WebServlet(name = "update", urlPatterns = {"/update"})
public class update extends HttpServlet {

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
            out.println("<title>Servlet update</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet update at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductTypeDao ptd = new ProductTypeDao();
        ProductDao pd = new ProductDao();
        ProductCategoryDao pcd = new ProductCategoryDao();
        String id_ra = request.getParameter("id");
        String page = request.getParameter("page");
        request.setAttribute("page", page);
        int id;
        try {
            id = (id_ra == null) ? 0 : Integer.parseInt(id_ra);

            Product p = pd.searchProductById(id);
            request.setAttribute("products", p);
        } catch (NumberFormatException e) {

        }
        List<ProductType> productTypeList = ptd.getAll();
        List<ProductCategory> productCategoryList = pcd.getAll();
        request.setAttribute("productTypeList", productTypeList);
        request.setAttribute("productCategoryList", productCategoryList);
        request.getRequestDispatcher("update.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDao pd = new ProductDao();
        ProductCategoryDao pcd = new ProductCategoryDao();
        ProductTypeDao ptd = new ProductTypeDao();
        
        String page = request.getParameter("page");
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        String product_id = request.getParameter("id");
        String describe = request.getParameter("describe");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String product_category_id = request.getParameter("category");
        String product_type_id = request.getParameter("type");
        PrintWriter out = response.getWriter();
        int id, pci, pti, quantities,pages;
        double prices;
        id = (product_id == null) ? 0 : Integer.parseInt(product_id);
        try {
            quantities = (quantity == null) ? 0 : Integer.parseInt(quantity);
            pages = (page == null) ? 0 : Integer.parseInt(page);
            pci = (product_category_id == null) ? 0 : Integer.parseInt(product_category_id);
            pti = (product_type_id == null) ? 0 : Integer.parseInt(product_type_id);
            prices = (price == null) ? 0 : Double.parseDouble(price);
            Product p = new Product(id, describe, image, name, prices, quantities, pcd.getCategoryById(pci), ptd.getProductTypeById(pti));
        
            pd.updateProduct(p);
         
            response.sendRedirect("managerProduct?page="+pages);
        } catch (NumberFormatException e) {
            request.setAttribute("fail", "Bạn đã cập nhật sản phẩm thất bại");
            request.setAttribute("products", pd.searchProductById(id));
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }

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
