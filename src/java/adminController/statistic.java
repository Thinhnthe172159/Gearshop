/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminController;

import dal.OrderDao;
import dal.ProductDao;
import dal.ProductTypeDao;
import dal.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.DateOrder;
import model.Product;
import model.ProductType;
import model.User;

/**
 *
 * @author thinh
 */
@WebServlet(name = "statistic", urlPatterns = {"/statistic"})
public class statistic extends HttpServlet {

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
            out.println("<title>Servlet statistic</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet statistic at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        ProductDao pd = new ProductDao();
        UserDao ud = new UserDao();
        ProductTypeDao ptd = new ProductTypeDao();
        List<ProductType> productTypeList = ptd.getAll();
        List<Product> listProduct = pd.getAll();
        List<Integer> listQuantity = new ArrayList<>();

        for (int i = 0; i < productTypeList.size(); i++) {
            int quantity = 0;
            for (int j = 0; j < listProduct.size(); j++) {
                if (productTypeList.get(i).getId() == listProduct.get(j).getProductType().getId()) {
                    quantity += listProduct.get(j).getQuantity();
                }
            }
            listQuantity.add(quantity);
        }
        String year_raw = request.getParameter("year");
        int year;
        if (year_raw != null) {
            year = Integer.parseInt(year_raw);
        } else {
            year = 2024;
        }
        DateOrder t1 = new DateOrder(1, year, od.getIncomeEachYear(1, year),od.getQuantitySale(1, year));
        DateOrder t2 = new DateOrder(2, year, od.getIncomeEachYear(2, year),od.getQuantitySale(2, year));
        DateOrder t3 = new DateOrder(3, year, od.getIncomeEachYear(3, year),od.getQuantitySale(3, year));
        DateOrder t4 = new DateOrder(4, year, od.getIncomeEachYear(4, year),od.getQuantitySale(4, year));
        DateOrder t5 = new DateOrder(5, year, od.getIncomeEachYear(5, year),od.getQuantitySale(5, year));
        DateOrder t6 = new DateOrder(6, year, od.getIncomeEachYear(6, year),od.getQuantitySale(6, year));
        DateOrder t7 = new DateOrder(7, year, od.getIncomeEachYear(7, year),od.getQuantitySale(7, year));
        DateOrder t8 = new DateOrder(8, year, od.getIncomeEachYear(8, year),od.getQuantitySale(8, year));
        DateOrder t9 = new DateOrder(9, year, od.getIncomeEachYear(9, year),od.getQuantitySale(9, year));
        DateOrder t10 = new DateOrder(10, year, od.getIncomeEachYear(10, year),od.getQuantitySale(10, year));
        DateOrder t11 = new DateOrder(11, year, od.getIncomeEachYear(11, year),od.getQuantitySale(11, year));
        DateOrder t12 = new DateOrder(12, year, od.getIncomeEachYear(12, year),od.getQuantitySale(12, year));
        List<DateOrder> dateOrderList = new ArrayList<>();
        dateOrderList.add(t1);
        dateOrderList.add(t2);
        dateOrderList.add(t3);
        dateOrderList.add(t4);
        dateOrderList.add(t5);
        dateOrderList.add(t6);
        dateOrderList.add(t7);
        dateOrderList.add(t8);
        dateOrderList.add(t9);
        dateOrderList.add(t10);
        dateOrderList.add(t11);
        dateOrderList.add(t12);
        double total_income = 0;
        for(DateOrder i : dateOrderList){
            total_income+=i.getIncome();
        }
        request.setAttribute("totalIncome1", total_income);
        request.setAttribute("dateOrderList1", dateOrderList);
        List<Product> productList = new ArrayList<>();
        List<Integer> id_list = od.getTopProductSale(year);
        for(Integer i : id_list){
            productList.add(pd.searchProductById(i));
        }
        
        List<Integer> user_id_list = ud.getTop5UserId(year);
        List<User> user_list = new ArrayList<>();
        for(Integer i : user_id_list){
            user_list.add(ud.getUserById(i));
        }
        request.setAttribute("TopBuyer1", user_list);
        request.setAttribute("year1", year);
        request.setAttribute("productListBestSale1", productList);
        request.setAttribute("quantityList1", listQuantity);
        request.setAttribute("productTypeList1", productTypeList);
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
