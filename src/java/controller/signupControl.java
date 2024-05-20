/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDao;
import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Order;
import model.User;

/**
 *
 * @author thinh
 */
@WebServlet(name = "signupControl", urlPatterns = {"/signup"})
public class signupControl extends HttpServlet {

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
            out.println("<title>Servlet signupControl</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet signupControl at " + request.getContextPath() + "</h1>");
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

        UserDao ud = new UserDao();
        String name = request.getParameter("cr_user");
        String email = request.getParameter("cr_email");
        String phone = request.getParameter("cr_phone");
        String userName = request.getParameter("cr_user_name");
        String passWord = request.getParameter("cr_password");
        String confirmPassword = request.getParameter("cr_c_password");
        request.setAttribute("c_password", confirmPassword);
        User user, user_respon_data = new User(0, name, email, phone, userName, passWord, 0, null, null);
        request.setAttribute("user_data", user_respon_data);
        if (!passWord.equals(confirmPassword)) {
            request.setAttribute("incorrect", "Confirm password is not match, please try a gain!");
        } else {
            user = ud.checkUserExist(userName);
            if (user != null) {
                request.setAttribute("mess2", "user name has been used by some one!");
            } else {
                request.setAttribute("done", "your accout has successfully created!");
                user = new User(0, name, email, phone, userName, passWord, 0, null, null);
                ud.register(user);
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
