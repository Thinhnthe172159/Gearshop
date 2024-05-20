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
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderLine;
import model.User;

/**
 *
 * @author thinh
 */
@WebServlet(name = "showCart", urlPatterns = {"/showCart"})
public class showCart extends HttpServlet {

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
            out.println("<title>Servlet showCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet showCart at " + request.getContextPath() + "</h1>");
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
        OrderLineDao old = new OrderLineDao();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        request.setAttribute("user", user);
        List<OrderLine> orderLineList = new ArrayList<>();
        Order or = od.getNewOrderOfUserId(user.getId());
        if (or == null) {
            or = new Order(0, null, 0, user, 0);
            od.addOrder(or);
            orderLineList = old.getAll(od.getNewOrderOfUserId(user.getId()).getId());
            request.setAttribute("OrderlineList", orderLineList);
        } else {
            orderLineList = old.getAll(od.getNewOrderOfUserId(user.getId()).getId());
            request.setAttribute("OrderlineList", orderLineList);
        }

        request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
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
        OrderLineDao old = new OrderLineDao();
        OrderDao od = new OrderDao();

        String[] ids = request.getParameterValues("product");
        double totalPrice = 0;
        for (String id : ids) {
            int quantity = Integer.parseInt(request.getParameter("quantity_" + id));
            double price = Double.parseDouble(request.getParameter("price_" + id));
            totalPrice += quantity * price;
            //xử lý trừ quantity
        }

        request.setAttribute("Total", totalPrice);
        request.setAttribute("check", ids);
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
