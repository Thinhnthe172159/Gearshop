/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cart;

import dal.OrderDao;
import dal.OrderLineDao;
import dal.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Order;
import model.OrderLine;
import model.Product;
import model.User;

/**
 *
 * @author thinh
 */
@WebServlet(name = "addToCart", urlPatterns = {"/addToCart"})
public class addToCart extends HttpServlet {

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
            out.println("<title>Servlet addToCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addToCart at " + request.getContextPath() + "</h1>");
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
        OrderDao od = new OrderDao();
        ProductDao pd = new ProductDao();
        OrderLineDao pld = new OrderLineDao();
        HttpSession session = request.getSession();
        String id_raw = request.getParameter("id");
        User user = (User) session.getAttribute("user");
        int Product_id;
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else {
            // Check if an order already exists for the user
            Order order = od.getNewOrderOfUserId(user.getId());

            if (order == null) {
                order = new Order(0, null, 0, user, 0);
                od.addOrder(order);
            }

            try {
                List<OrderLine> orderLineList = pld.getAll(order.getId());
                Product_id = (id_raw == null) ? 0 : Integer.parseInt(id_raw);
                Product product = pd.getProductById(Product_id);
                OrderLine orderLine = new OrderLine(0, order, product, 1, product.getPrice());
                boolean check = false;
          
                for (OrderLine ol : orderLineList) {
                    if (ol.getProduct().getId() == Product_id) {
                        check = true;
                        break;
                    }
                }

                if (check == false) {
                    pld.addOrderLine(orderLine);
                } else {
                    OrderLine orl = pld.searchOrderLineBy(order.getId(), Product_id);
                    orl.setQuantity(orl.getQuantity() + 1);
                    orl.setPrice(orl.getQuantity() * product.getPrice());
                    pld.updateOrderLine(orl,Product_id);
                }

                List<OrderLine> updatedOrderLineList = pld.getAll(order.getId());
                session.setAttribute("OrderLineList", updatedOrderLineList);
                request.getRequestDispatcher("productDetail?id=" + Product_id).forward(request, response);
            } catch (NumberFormatException e) {
                // Handle the NumberFormatException if needed
            }
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
