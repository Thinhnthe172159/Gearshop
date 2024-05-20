/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author thinh
 */
@WebServlet(name = "updateAccount", urlPatterns = {"/updateAccount"})
public class updateAccount extends HttpServlet {

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
            out.println("<title>Servlet updateAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateAccount at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        UserDao ud = new UserDao();
        int id;
        try {
            id = (id_raw == null) ? 0 : Integer.parseInt(id_raw);
            User user = ud.getUserById(id);
            request.setAttribute("users", user);
            request.getRequestDispatcher("updateAccount.jsp").forward(request, response);
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
        UserDao ud = new UserDao();
        String id_raw = request.getParameter("id");
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String role_raw = request.getParameter("role");
        String image = request.getParameter("image");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        int id, role;
        try {
            role = (role_raw == null) ? 0 : Integer.parseInt(role_raw);
            id = (id_raw == null) ? 0 : Integer.parseInt(id_raw);
            User user = new User(id, name, email, phone, username, password, role, image, address);

            if (user.getRole() == 1) {
                HttpSession session = request.getSession();
                session.removeAttribute("user");
                if (!user.getPassword().equals(ud.getUserById(id).getPassword()) || !user.getUsername().equals(ud.getUserById(id).getUsername())) {
                    ud.updateUser(user);
                    response.sendRedirect("login.jsp");
                } else {
                    session.setAttribute("user", user);
                    ud.updateUser(user);
                    response.sendRedirect("adminHomePage");
                }
            } else {
                ud.updateUser(user);
                response.sendRedirect("managerAccount");
            }
        } catch (NumberFormatException e) {

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
