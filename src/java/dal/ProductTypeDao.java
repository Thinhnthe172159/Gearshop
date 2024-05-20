/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProductType;

/**
 *
 * @author thinh
 */
public class ProductTypeDao extends DBContext {
    // product type

    public ProductType getProductTypeById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[product_type_name]\n"
                + "  FROM [dbo].[Product_type]\n"
                + "  where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ProductType pt = new ProductType(rs.getInt("id"), rs.getString("product_type_name"));
                return pt;
            }
        } catch (SQLException e) {

        }
        return null;
    }

    public List<ProductType> getAlls(String name) {
        List<ProductType> list = new ArrayList<>();
        String sql = "Select * FROM [dbo].[Product_type] where 1=1 ";
        if (name != null && !name.isEmpty()) {
            sql += " and product_type_name like '%" + name + "%'";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductType pt = new ProductType(rs.getInt("id"), rs.getString("product_type_name"));
                list.add(pt);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public List<ProductType> getAll() {
        List<ProductType> list = new ArrayList<>();
        String sql = "Select * FROM [dbo].[Product_type] where 1=1 ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductType pt = new ProductType(rs.getInt("id"), rs.getString("product_type_name"));
                list.add(pt);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public void uddateType(ProductType pt) {
        String sql = "UPDATE [dbo].[Product_type]\n"
                + "   SET [product_type_name] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pt.getName());
            st.setInt(2, pt.getId());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void addType(ProductType pt) {
        String sql = "INSERT INTO [dbo].[Product_type]\n"
                + "           ([product_type_name])\n"
                + "     VALUES\n"
                + "           (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pt.getName());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void deleteType(int id) {
        String sql = "DELETE FROM [dbo].[Product_type]\n"
                + "      WHERE id =?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }
}
