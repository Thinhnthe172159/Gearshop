/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.User;

/**
 *
 * @author thinh
 */
public class OrderDao extends DBContext {

    private UserDao ud = new UserDao();

    //add new
    public void addOrder(Order order) {
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           ([date_order]\n"
                + "           ,[total_price]\n"
                + "           ,[user_id]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "          (?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, order.getDateOrder());
            st.setDouble(2, order.getTotalprice());
            st.setInt(3, order.getUser().getId());
            st.setInt(4, order.getStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id) {
        String sql = "DELETE FROM [dbo].[Order] WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
        }
    }

    //get Laste order
    public Order getNewOrderOfUserId(int userId) {
        String sql = "SELECT [id], [date_order], [total_price], [user_id], [status]\n"
                + "FROM [dbo].[Order]\n"
                + "WHERE [user_id] = ? AND [id] = (SELECT MAX([id]) FROM [dbo].[Order] WHERE [user_id] = ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            st.setInt(2, userId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getString("date_order"));
                order.setTotalprice(rs.getDouble("total_price"));
                User user = ud.getUserById(rs.getInt("user_id"));
                order.setUser(user);
                order.setStatus(rs.getInt("status"));
                return order;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    //  total price;
    public double getIncomeEachYear(int month, int year) {
        double income = 0;
        String sql = "SELECT sum(total_price) as total_price\n"
                + "  FROM [dbo].[Order]\n"
                + "  where month([date_order]) = ? and year([date_order]) =? and status = 2";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, month);
            st.setInt(2, year);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                income = rs.getDouble("total_price");
                return income;
            }
        } catch (SQLException e) {

        }
        return income;
    }

    public int getQuantitySale(int month, int year) {
        int quantity = 0;
        String sql = "SELECT sum(ol.quantity) as total_quantity\n"
                + "                FROM [dbo].[Order] as o inner join [dbo].[Order_line] as ol on ol.order_id = o.id\n"
                + "                where month([date_order]) = ? and year([date_order]) = ? and status = 2";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, month);
            st.setInt(2, year);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("total_quantity");
                return quantity;
            }
        } catch (SQLException e) {

        }
        return quantity;
    }

    //update Order
    public void updateOrder(Order order) {
        String sql = "UPDATE [dbo].[Order]\n"
                + "   SET [date_order] = ?\n"
                + "      ,[total_price] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE id = ? and [user_id] = ?"; // Removed extra space in the WHERE clause

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, order.getDateOrder());
            st.setDouble(2, order.getTotalprice());
            st.setInt(3, order.getStatus());
            st.setInt(4, order.getId());
            st.setInt(5, order.getUser().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order searchOrder(int orderId, int UserId) {
        String sql = "select * from [dbo].[Order]"
                + "where 1=1";
        if (orderId != 0) {
            sql += "and id =" + orderId;
        }
        if (UserId != 0) {
            sql += "and [user_id]=" + UserId;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getString("date_order"));
                order.setTotalprice(rs.getDouble("total_price"));
                User user = ud.getUserById(rs.getInt("user_id"));
                order.setUser(user);
                order.setStatus(rs.getInt("status"));
                return order;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public Order searchOrderByOrderId(int orderId) {
        String sql = "select * from [dbo].[Order]"
                + "where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getString("date_order"));
                order.setTotalprice(rs.getDouble("total_price"));
                User user = ud.getUserById(rs.getInt("user_id"));
                order.setUser(user);
                order.setStatus(rs.getInt("status"));
                return order;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    // getall
    public List<Order> getAll() {
        String sql = "select * from [dbo].[Order] where 1=1";
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getString("date_order"));
                order.setTotalprice(rs.getDouble("total_price"));
                User user = ud.getUserById(rs.getInt("user_id"));
                order.setUser(user);
                order.setStatus(rs.getInt("status"));
                list.add(order);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<Order> getAllByStatus(int status, int userID) {
        String sql = "select * from [dbo].[Order] where status =? and user_id =? order by date_order desc";
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, status);
            st.setInt(2, userID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getString("date_order"));
                order.setTotalprice(rs.getDouble("total_price"));
                User user = ud.getUserById(rs.getInt("user_id"));
                order.setUser(user);
                order.setStatus(rs.getInt("status"));
                list.add(order);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<Order> getAllByStatuss(int status) {
        String sql = "select * from [dbo].[Order] where status = ? order by date_order desc";
        List<Order> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, status);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setDateOrder(rs.getString("date_order"));
                order.setTotalprice(rs.getDouble("total_price"));
                User user = ud.getUserById(rs.getInt("user_id"));
                order.setUser(user);
                order.setStatus(rs.getInt("status"));
                list.add(order);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<Integer> getTopProductSale(int year) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT top 5 (p.id) as product_id  FROM [dbo].[Order] as o inner join dbo.Order_line as ol \n"
                + "on o.id = ol.order_id\n"
                + "inner join dbo.Product as p on ol.product_id = p.id \n"
                + "where year(o.date_order) = ? and o.status = 2\n"
                + "order by ol.quantity desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("product_id");
                list.add(id);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public static void main(String[] args) {
        OrderDao od = new OrderDao();
        UserDao ud = new UserDao();
        LocalDate today = LocalDate.now();

        // Định dạng ngày theo định dạng tùy chỉnh
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        System.out.println(ud.getUserById(2).getName());

        Order s = new Order(14, formattedDate, 10, ud.getUserById(2), 4);
        od.updateOrder(s);
        List<Order> list = od.getAll();
        for (Order item : list) {
            System.out.println(item);
        }
    }

}
