/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cart;

import dal.OrderDao;
import dal.OrderLineDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderLine;
import model.User;

/**
 *
 * @author thinh
 */
@WebServlet(name = "process", urlPatterns = {"/process"})
public class process extends HttpServlet {

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
            out.println("<title>Servlet process</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet process at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        OrderDao od = new OrderDao();
        User user = (User) session.getAttribute("user");
        String id_raw = request.getParameter("id");
        String mode_raw = request.getParameter("mode");
        OrderLineDao old = new OrderLineDao();
        Order order = new Order();
        OrderLine orderLine = new OrderLine();

        int id, mode;
        try {
            id = (id_raw == null) ? 0 : Integer.parseInt(id_raw);
            mode = (mode_raw == null) ? 0 : Integer.parseInt(mode_raw);
            if (mode == 1) {
                //tăng
                orderLine = old.searchOrderLineBy(od.getNewOrderOfUserId(user.getId()).getId(), id);
                orderLine.setQuantity(orderLine.getQuantity() + 1);
                old.updateOrderLine(orderLine, id);
            }
            if (mode == 0) {
                //giảm
                orderLine = old.searchOrderLineBy(od.getNewOrderOfUserId(user.getId()).getId(), id);
                if (orderLine.getQuantity() == 1) {
                    orderLine.setQuantity(1);
                } else {
                    orderLine.setQuantity(orderLine.getQuantity() - 1);
                }
                old.updateOrderLine(orderLine, id);
            }

            List<OrderLine> orderLineList = old.getAll(od.getNewOrderOfUserId(user.getId()).getId());
            session.removeAttribute("OrderLineList");
            session.setAttribute("OrderLineList", orderLineList);
            response.sendRedirect("showCart");
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
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        OrderDao od = new OrderDao();
        Order order = od.getNewOrderOfUserId(user.getId());
        
        OrderLineDao old = new OrderLineDao();
        double totalPrice = 0;
        String[] ids = request.getParameterValues("product");
        List<OrderLine> orderList = new ArrayList<>();
        if (ids != null) {
            for (String id : ids) {
                int quantity = Integer.parseInt(request.getParameter("quantity_" + id));
                double price = Double.parseDouble(request.getParameter("price_" + id));
                totalPrice += quantity * price;
                OrderLine orl =  old.searchOrderLineBy(order.getId(), Integer.parseInt(id));
                orderList.add(orl);
                //xử lý trừ quantity
            }
        }
        
        request.setAttribute("orderLineList2", orderList);
        request.setAttribute("Total", ""+totalPrice);
        request.setAttribute("check", ids);
        List<OrderLine> orderLineList = old.getAll(od.getNewOrderOfUserId(user.getId()).getId());
        request.setAttribute("OrderlineList", orderLineList);
        request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
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
