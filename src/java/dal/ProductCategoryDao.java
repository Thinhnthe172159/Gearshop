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
import model.ProductCategory;
import model.ProductType;

/**
 *
 * @author thinh
 */
public class ProductCategoryDao extends DBContext {
    // category dao

    public ProductCategory getCategoryById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[category_name]\n"
                + "      ,[product_id_type]\n"
                + "  FROM [dbo].[Product_Category]\n"
                + "  where [id] = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int pid = rs.getInt("product_id_type");
                ProductTypeDao ptd = new ProductTypeDao();
                ProductType pt = ptd.getProductTypeById(pid);
                ProductCategory c = new ProductCategory(rs.getInt("id"), rs.getString("category_name"), pt);
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public List<ProductCategory> getProductCategoryByIdType(int typeId) {
        List<ProductCategory> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[category_name]\n"
                + "      ,[product_id_type]\n"
                + "  FROM [dbo].[Product_Category]\n"
                + "  where [product_id_type] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(3, typeId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("product_id_type");
                ProductTypeDao ptd = new ProductTypeDao();
                ProductType pt = ptd.getProductTypeById(pid);
                ProductCategory c = new ProductCategory(rs.getInt("id"), rs.getString("category_name"), pt);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ProductCategory> getAlls(String name) {
        List<ProductCategory> list = new ArrayList<>();
        String sql = "select * FROM [dbo].[Product_Category] where 1=1 ";
        if(name != null && !name.isEmpty()){
            sql+=" and [category_name] like '%"+name+"%' ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("product_id_type");
                ProductTypeDao ptd = new ProductTypeDao();
                ProductType pt = ptd.getProductTypeById(pid);
                ProductCategory c = new ProductCategory(rs.getInt("id"), rs.getString("category_name"), pt);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }
    
        public List<ProductCategory> getAll() {
        List<ProductCategory> list = new ArrayList<>();
        String sql = "select * FROM [dbo].[Product_Category] where 1=1 ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("product_id_type");
                ProductTypeDao ptd = new ProductTypeDao();
                ProductType pt = ptd.getProductTypeById(pid);
                ProductCategory c = new ProductCategory(rs.getInt("id"), rs.getString("category_name"), pt);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public static void main(String[] args) {
        ProductCategoryDao d = new ProductCategoryDao();
        ProductCategory pc = d.getCategoryById(1);
        System.out.println(pc.getName());

//        List<ProductCategory> list = d.getAll();
//        for(ProductCategory item: list){
//            System.out.println(item.getId()+" "+item.getName()+" "+item.getProductType().getId());
//        }
    }

    public void updateProductCategory(ProductCategory pc) {
        String sql = "UPDATE [dbo].[Product_Category]\n"
                + "   SET \n"
                + "      [category_name] = ?\n"
                + "      ,[product_id_type] = ?\n"
                + " WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pc.getName());
            st.setInt(2, pc.getProductType().getId());
            st.setInt(3, pc.getId());
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    public void addProductCategory(ProductCategory pc) {
        String sql = "INSERT INTO [dbo].[Product_Category]\n"
                + "           ([category_name]\n"
                + "           ,[product_id_type])\n"
                + "     VALUES\n"
                + "           (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pc.getName());
            st.setInt(2, pc.getProductType().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductCategory(int id) {
        String sql = "DELETE FROM [dbo].[Product_Category]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

}
/*
    このスープはおしいですね、まだえもすか。
ーー＞　すぶはまだあります。

ビールはまだありますか。
いいえ、まだありません。
そですか、





 */
