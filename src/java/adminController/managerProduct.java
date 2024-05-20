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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Product;
import model.ProductCategory;
import model.ProductType;
import model.User;

/**
 *
 * @author thinh
 */
@WebServlet(name = "ProductManagerment", urlPatterns = {"/managerProduct"})
public class managerProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet productList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productList at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        ProductCategoryDao pcd = new ProductCategoryDao();
        ProductDao pd = new ProductDao();
        ProductTypeDao ptd = new ProductTypeDao();

        List<ProductType> listType = ptd.getAll();
        List<ProductCategory> listCategory = pcd.getAll();
        List<ProductCategory> listCategory2 = new ArrayList<>();

        Set<String> addedNames = new HashSet<>();
        for (ProductCategory category : listCategory) {
            String categoryName = category.getName();
            if (addedNames.add(categoryName)) {
                listCategory2.add(category);
            }
        }
        PrintWriter pt = response.getWriter();
        request.setAttribute("listCategory", listCategory2);
        request.setAttribute("categories", listCategory);

        String name_item = request.getParameter("name");

        String price_from = request.getParameter("price_from");

        String price_To = request.getParameter("price_To");

        String cid_raw = request.getParameter("cid");

        String type_id_raw = request.getParameter("type_id");

        String type_raw = request.getParameter("type_raw");

        request.setAttribute("type", listType);

        int cid, type, typeId;
        double priceFrom, priceTo;

        try {
            typeId = (type_id_raw == null) ? 0 : Integer.parseInt(type_id_raw);

            cid = (cid_raw == null) ? 0 : Integer.parseInt(cid_raw);

            type = (type_raw == null) ? 0 : Integer.parseInt(type_raw);

            if (price_from == null || price_from.isEmpty()) {
                priceFrom = 0;
            } else {
                priceFrom = Double.parseDouble(price_from);
            }

            if (price_To == null || price_To.isEmpty()) {
                priceTo = 0;
            } else {
                priceTo = Double.parseDouble(price_To);
            }
            List<Product> productList = pd.searchProduct(name_item, priceFrom, priceTo, cid, type, typeId);
            int count = productList.size();

            //xử lý phần pagging
            int size = productList.size();
            int pageIndex, elementPerPage = 20;
            int numOfPage = (size % elementPerPage == 0 ? size / elementPerPage : (size / elementPerPage + 1));// số trang
            String xpage = request.getParameter("page");
            if (xpage == null) {
                pageIndex = 1;
            } else {
                pageIndex = Integer.parseInt(xpage);
            }
            int start, end;
            start = (pageIndex - 1) * elementPerPage;
            end = Math.min(pageIndex * elementPerPage, size);
            List<Product> listProduct = pd.getListByPage(productList, start, end);
            //-kết thúc xử lý phân trang
            request.setAttribute("count", count);
            request.setAttribute("product", listProduct);
            request.setAttribute("page", pageIndex);
            request.setAttribute("num", numOfPage);
            request.setAttribute("id",cid);
            request.setAttribute("name", name_item);
            request.setAttribute("priceFrom", (int)priceFrom);
            request.setAttribute("priceTo", (int)priceTo);
            request.setAttribute("Type", type);
            request.setAttribute("type_id_raw", typeId);
            request.getRequestDispatcher("adminHomePage").forward(request, response);
        } catch (NumberFormatException e) {

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
