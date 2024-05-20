/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.OrderLine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.Product;

/**
 *
 * @author thinh
 */
public class OrderLineDao extends DBContext {

    private OrderDao od = new OrderDao();
    private ProductDao pd = new ProductDao();

    //add order line
    public void addOrderLine(OrderLine orderLine) {
        String sql = "INSERT INTO [dbo].[Order_line]\n"
                + "           ([order_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[price])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            Order order = orderLine.getOrder();
            st.setInt(1, order.getId());
            Product product = orderLine.getProduct();
            st.setInt(2, product.getId());
            st.setInt(3, orderLine.getQuantity());
            st.setDouble(4, orderLine.getPrice());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public OrderLine searchOrderLineBy(int orderId, int product_id) {
        String sql = "select * from [dbo].[Order_line]"
                + "where order_id=? and product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            st.setInt(2, product_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderLine orderLine = new OrderLine();
                orderLine.setId(rs.getInt("id"));
                Order order = od.searchOrder(rs.getInt("order_id"), 0);
                orderLine.setOrder(order);
                Product product = pd.getProductById(rs.getInt("product_id"));
                orderLine.setProduct(product);
                orderLine.setPrice(rs.getDouble("price"));
                orderLine.setQuantity(rs.getInt("quantity"));
                return orderLine;
            }
        } catch (SQLException e) {
        }
        return null;
    }
    
       public OrderLine searchOrderLineByOrder(int orderId) {
        String sql = "select * from [dbo].[Order_line]"
                + "where order_id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderLine orderLine = new OrderLine();
                orderLine.setId(rs.getInt("id"));
                Order order = od.searchOrder(rs.getInt("order_id"), 0);
                orderLine.setOrder(order);
                Product product = pd.getProductById(rs.getInt("product_id"));
                orderLine.setProduct(product);
                orderLine.setPrice(rs.getDouble("price"));
                orderLine.setQuantity(rs.getInt("quantity"));
                return orderLine;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public OrderLine searchOrderLineById(int id) {
        String sql = "select * from [dbo].[Order_line]"
                + "where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderLine orderLine = new OrderLine();
                Order order = od.searchOrder(rs.getInt("order_id"), 0);
                orderLine.setOrder(order);
                orderLine.setId(rs.getInt("id"));
                Product product = pd.getProductById(rs.getInt("product_id"));
                orderLine.setProduct(product);
                orderLine.setPrice(rs.getDouble("price"));
                orderLine.setQuantity(rs.getInt("quantity"));
                return orderLine;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<OrderLine> getAll(int orderId) {
        List<OrderLine> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Order_line] WHERE [order_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderLine orderLine = new OrderLine();
                Order order = od.searchOrder(rs.getInt("order_id"), 0);
                orderLine.setOrder(order);
                orderLine.setId(rs.getInt("id"));
                Product product = pd.getProductById(rs.getInt("product_id"));
                orderLine.setProduct(product);
                orderLine.setPrice(rs.getDouble("price"));
                orderLine.setQuantity(rs.getInt("quantity"));
                list.add(orderLine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
        public List<OrderLine> getAll(int[] orderId) {
        List<OrderLine> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Order_line] WHERE 1=1 ";
        if (orderId != null) {
            if (orderId[0] != 0) {
                for (int i = 0; i < orderId.length; i++) {
                    if (i == 0) {
                        sql += "and order_id =" + orderId[i];
                    } else {
                        sql += "or order_id = " + orderId[i];
                    }
                }
            }

        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderLine orderLine = new OrderLine();
                Order order = od.searchOrder(rs.getInt("order_id"), 0);
                orderLine.setOrder(order);
                orderLine.setId(rs.getInt("id"));
                Product product = pd.getProductById(rs.getInt("product_id"));
                orderLine.setProduct(product);
                orderLine.setPrice(rs.getDouble("price"));
                orderLine.setQuantity(rs.getInt("quantity"));
                list.add(orderLine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateOrderLine(OrderLine orderLine, int product_id) {
        String sql = "UPDATE [dbo].[Order_line] SET [quantity] = ? , [price] =? WHERE order_id =? and product_id= ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, orderLine.getQuantity());
            st.setDouble(2, orderLine.getProduct().getPrice() * orderLine.getQuantity());
            st.setInt(3, orderLine.getOrder().getId());
            st.setInt(4, product_id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOrderLineById(OrderLine orderLine) {
        String sql = "UPDATE [dbo].[Order_line] SET [order_id] = ? WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, orderLine.getOrder().getId());
            st.setInt(2, orderLine.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteItem(int productId) {
        String sql = "DELETE FROM [dbo].[Order_line]\n"
                + "      WHERE product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productId);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteItemByID(int id) {
        String sql = "DELETE FROM [dbo].[Order_line]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public static void main(String[] args) {
        OrderLineDao old = new OrderLineDao();
        OrderDao od = new OrderDao();
        ProductDao pd = new ProductDao();

        OrderLine orderLine = new OrderLine(29, od.searchOrderByOrderId(8), pd.getProductById(97), 2, -1000);
        old.updateOrderLineById(orderLine);
        List<OrderLine> list = old.getAll(8);

        for (OrderLine i : list) {
            System.out.println(i);
        }
    }

}
