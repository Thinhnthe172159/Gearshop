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
import model.Order;
import model.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.OrderLine;

/**
 *
 * @author thinh
 */
@WebServlet(name = "ordering", urlPatterns = {"/ordering"})
public class ordering extends HttpServlet {

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
            out.println("<title>Servlet ordering</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ordering at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        LocalDate today = LocalDate.now();

        // Định dạng ngày theo định dạng tùy chỉnh
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // In ra ngày hôm nay với định dạng tùy chỉnh
        HttpSession session = request.getSession();
        OrderLineDao orderLineDao = new OrderLineDao();
        User user = (User) session.getAttribute("user");
        session.removeAttribute("OrderLineList");
        OrderDao orderDao = new OrderDao();
        ProductDao productDao = new ProductDao();
        String Total_raw = request.getParameter("total");
        Double total = (Total_raw == null) ? 0 : Double.parseDouble(Total_raw);
        Order order = orderDao.getNewOrderOfUserId(user.getId());
        String[] item_id = request.getParameterValues("item_id");
        order.setDateOrder(formattedDate);
        order.setTotalprice(total);
        order.setStatus(1);// là order hàng nếu chuyển status sang 1

        Order NEW_ORDER = new Order(0, null, 0, user, 0);
        orderDao.addOrder(NEW_ORDER);
        List<OrderLine> getItem = orderLineDao.getAll(order.getId());
        List<OrderLine> subOrderLIne = new ArrayList<>();
        List<OrderLine> listOrderLine3 = new ArrayList<>();
        for (String item : item_id) {
            subOrderLIne.add(orderLineDao.searchOrderLineById(Integer.parseInt(item)));
        }

        for (OrderLine item : subOrderLIne) {
            out.println(item.getId() + "   " + item.getProduct().getName());
        }

        for (OrderLine item : getItem) {

            boolean found = false;
            for (OrderLine subItem : subOrderLIne) {
                if (item.getId() == subItem.getId()) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                listOrderLine3.add(item);
            }
        }

        for (OrderLine item : listOrderLine3) {
            OrderLine ord = orderLineDao.searchOrderLineById(item.getId());
            ord.setOrder(orderDao.getNewOrderOfUserId(user.getId()));
            orderLineDao.updateOrderLineById(ord);
        }
        List<OrderLine> updateOrderLines = orderLineDao.getAll(orderDao.getNewOrderOfUserId(user.getId()).getId());
        session.setAttribute("OrderLineList", updateOrderLines);
        orderDao.updateOrder(order);
        response.sendRedirect("home");
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
