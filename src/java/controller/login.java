/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDao;
import dal.OrderLineDao;
import dal.UserDao;
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
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            session.removeAttribute("user");
            session.removeAttribute("OrderLineList");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDao ld = new UserDao();
        OrderDao od = new OrderDao();
        OrderLineDao old = new OrderLineDao();
        //login
        String username = request.getParameter("user");
        String password = request.getParameter("pass");

        User u = ld.login(username, password);
        if (u == null) {
            request.setAttribute("wrong", "user name or password incorrect,please try again");
            request.setAttribute("username", username);
            request.setAttribute("pass", password);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (u.getRole() == 1) {
                session.setAttribute("user", u);
                response.sendRedirect("admin.jsp");
            } else {
                session.setAttribute("user", u);
                Order order = new Order();
                order = od.getNewOrderOfUserId(u.getId());
                List<OrderLine> orderLineList = new ArrayList<>();
                if (order == null) {
                    session.setAttribute("OrderLineList", orderLineList);
                } else {
                    orderLineList = old.getAll(order.getId());
                    if (orderLineList.isEmpty()) {
                    }
                }
                session.setAttribute("OrderLineList", orderLineList);
                response.sendRedirect("home");
            }

        }
    }

}
