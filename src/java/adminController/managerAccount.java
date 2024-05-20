/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dal.ProductDao;
import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;
import model.User;

/**
 *
 * @author thinh
 */
public class managerAccount extends HttpServlet {

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
            out.println("<title>Servlet managerAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet managerAccount at " + request.getContextPath() + "</h1>");
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
        ProductDao pd = new ProductDao();
        UserDao ud = new UserDao();
        String name_result = request.getParameter("name");
        String sort_type = request.getParameter("sort");
        int sort =(sort_type==null)?0:Integer.parseInt(sort_type);

        //phân trang
        List<User> userList = ud.getAll(0,sort , name_result);
        int size = userList.size();
        int pageIndex, elementPerPage = 10;
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
        List<User> userlist = ud.getListByPage(userList, start, end);
        request.setAttribute("page", pageIndex);
        request.setAttribute("num", numOfPage);
        request.setAttribute("accountList", userList);
        request.setAttribute("userlist", userlist);
        request.getRequestDispatcher("adminHomePage").forward(request, response);

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
