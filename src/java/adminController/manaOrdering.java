/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dal.OrderDao;
import dal.OrderLineDao;
import dal.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Order;
import model.OrderLine;
import model.Product;

/**
 *
 * @author thinh
 */
public class manaOrdering extends HttpServlet {

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
            out.println("<title>Servlet manaOrdering</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet manaOrdering at " + request.getContextPath() + "</h1>");
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
        OrderLineDao old = new OrderLineDao();
        OrderDao od = new OrderDao();
        String id_raw = request.getParameter("id");
        
        String id_delete_raw = request.getParameter("id_deleted_raw");
        if (id_delete_raw != null) {
            int idd = Integer.parseInt(id_delete_raw);
            od.deleteOrder(idd);
        }
        if (id_raw != null) {
            int id = Integer.parseInt(id_raw);
            Order odr = od.searchOrderByOrderId(id);
            List<OrderLine> orderLine = old.getAll(id);
            request.setAttribute("OrderId", id);
            request.setAttribute("OrderdetailList", orderLine);
            request.setAttribute("Orderdetail", odr);
        }
        List<Order> orderList = od.getAllByStatuss(1);
        request.setAttribute("orderListNeedConfirm", orderList);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
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
        OrderLineDao old = new OrderLineDao();
        OrderDao od = new OrderDao();
        String id_raw = request.getParameter("id");

        if (id_raw != null) {
            int id = Integer.parseInt(id_raw);
            Order odr = od.searchOrderByOrderId(id);
            odr.setStatus(2);
            od.updateOrder(odr);

            List<OrderLine> orderLineList = old.getAll(id);
            for (OrderLine item : orderLineList) {
                Product p = pd.searchProductById(item.getProduct().getId());
                p.setQuantity(p.getQuantity() - item.getQuantity());
                pd.updateProduct(p);
            }

        }
        response.sendRedirect("manaOrdering");
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
