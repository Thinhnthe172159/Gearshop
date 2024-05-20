/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dal.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author thinh
 */
@WebServlet(name = "deleteProduct", urlPatterns = {"/deleteProduct"})
public class deleteProduct extends HttpServlet {

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
            out.println("<title>Servlet deleteProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deleteProduct at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("product_id");

        String name_item = request.getParameter("name");

        String price_from = request.getParameter("price_from");

        String price_To = request.getParameter("price_To");

        String cid_raw = request.getParameter("cid");

        String type_id_raw = request.getParameter("type_id");

        String type_raw = request.getParameter("type_raw");

        String page_x = request.getParameter("page");

        int cid, type, typeId, page;
        double priceFrom, priceTo;

        try {
            typeId = (type_id_raw == null) ? 0 : Integer.parseInt(type_id_raw);

            page = (page_x == null) ? 0 : Integer.parseInt(page_x);

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

            ProductDao pd = new ProductDao();
            int id = (id_raw == null) ? 0 : Integer.parseInt(id_raw);
            pd.deleteProduct(id);
            response.sendRedirect("managerProduct?page="+page+"&cid="+cid+"&name="+name_item+"&price_from="+(int)priceFrom+"&price_To="+(int)priceTo+"&type_raw="+type+"&type_id="+typeId);
        } catch (NumberFormatException e) {
            
        }
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
        processRequest(request, response);
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
