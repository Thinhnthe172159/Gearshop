/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.util.List;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ProductCategory;
import model.ProductType;

/**
 *
 * @author thinh
 */
public class ProductDao extends DBContext {

    private ProductCategoryDao pdc;
    private ProductTypeDao ptd;

    public ProductDao() {
        pdc = new ProductCategoryDao();
        ptd = new ProductTypeDao();
    }

    // get product by any string 
    public Product getProductById(int id) {

        List<Product> list = new ArrayList();
        String sql = "SELECT [id]\n"
                + "      ,[description]\n"
                + "      ,[image]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[product_category_id]\n"
                + "      ,[product_id_type]\n"
                + "  FROM [dbo].[Product]\n where 1=1";
        if (id != 0) {
            sql += " and id =" + id;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();

                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                p.setProductType(pt);
                p.setProductCategoty(c);
                return p;
            }

        } catch (SQLException e) {
        }
        return null;
    }

    // search product by 
    public List<Product> searchProducts(String name, Double priceFrom, Double priceTo, int[] cid, int type, int typeid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE 1=1";

        if (name != null && !name.isEmpty()) {
            sql += " AND name LIKE '%" + name + "%'";
        }

        if (priceFrom != 0) {
            sql += " AND price >= " + priceFrom;
        }

        if (priceTo != 0) {
            sql += " AND price <= " + priceTo;
        }

        if (cid != null) {
            if (cid[0] != 0) {
                for (int i = 0; i < cid.length; i++) {
                    if (i == 0) {
                        sql += "and product_category_id =" + cid[i];
                    } else {
                        sql += "or product_category_id = " + cid[i];
                    }
                }
            }

        }

        if (type == 1) {
            sql += "order by price";
        }
        if (type == 2) {
            sql += "order by price desc";
        }
        if (typeid != 0) {
            sql += "and product_id_type=" + typeid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                p.setProductType(pt);
                p.setProductCategoty(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> searchProduct(String name, Double priceFrom, Double priceTo, int cid, int type, int typeid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE 1=1";

        if (name != null && !name.isEmpty()) {
            sql += " AND name LIKE '%" + name + "%'";
        }

        if (priceFrom != 0) {
            sql += " AND price >= " + priceFrom;
        }

        if (priceTo != 0) {
            sql += " AND price <= " + priceTo;
        }

        if (cid != 0) {
            sql += "and product_category_id =" + cid;
        }

        if (type == 1) {
            sql += "order by price";
        }
        if (type == 2) {
            sql += "order by price desc";
        }
        if (typeid != 0) {
            sql += "and product_id_type=" + typeid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                p.setProductType(pt);
                p.setProductCategoty(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> searchProductByManyCategory(int[] cid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE 1=1";

        if (cid != null) {
            for (int i = 0; i < cid.length; i++) {
                if (i == 0) {
                    sql += "and product_category_id =" + cid[i];
                } else {
                    sql += "or product_category_id = " + cid[i];
                }
            }
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                p.setProductType(pt);
                p.setProductCategoty(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
//Lấy các sản phẩm liên quan

    public List<Product> getRelateProduct(int id, int cid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT top(4)[id]\n"
                + "      ,[description]\n"
                + "      ,[image]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[product_category_id]\n"
                + "      ,[product_id_type]\n"
                + "  FROM [dbo].[Product]"
                + "where 1 = 1";
        if (id != 0) {
            sql += "and id !=" + id;
        }
        if (cid != 0) {
            sql += "and product_category_id = " + cid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                p.setProductType(pt);
                p.setProductCategoty(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // sản phẩm cùng phân khúc giá
    public List<Product> getRelateProductWithSamePrice(int id, double price, int type) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT top(4)[id]\n"
                + "      ,[description]\n"
                + "      ,[image]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[product_category_id]\n"
                + "      ,[product_id_type]\n"
                + "  FROM [dbo].[Product]"
                + "where 1 = 1";
        if (id != 0) {
            sql += "and id !=" + id;
        }
        if (type != 0) {
            sql += "and product_id_type = " + type;
        }
        if (price != 0) {
            double pr = price + (price * 0.15);
            sql += " AND price BETWEEN " + price + " AND " + pr;
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                p.setProductType(pt);
                p.setProductCategoty(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    // lấy size
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[description]\n"
                + "      ,[image]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[product_category_id]\n"
                + "      ,[product_id_type]\n"
                + "  FROM [dbo].[Product]"
                + "where 1 = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                p.setProductType(pt);
                p.setProductCategoty(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getListByPage(List<Product> list, int start, int end) {
        ArrayList<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    // lấy các sản phẩm mới nhất theo type
    public List<Product> topNewProducts(int ptid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT top(8)[id]\n"
                + "      ,[description]\n"
                + "      ,[image]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[quantity]\n"
                + "      ,[product_category_id]\n"
                + "      ,[product_id_type]\n"
                + "  FROM [dbo].[Product]"
                + "where 1 = 1";
        if (ptid != 0) {
            sql += "and product_id_type = " + ptid + " order by id desc";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setName(rs.getString("name"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                p.setProductType(pt);
                p.setProductCategoty(c);
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product searchProductByName(String name) {
        String sql = "SELECT [id], [description], [image], [name], [price], [quantity], [product_category_id], [product_id_type] FROM [dbo].[Product] WHERE 1 = 1";
        if (name != null) {
            sql += " AND [name] LIKE ?";
        }

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (name != null) {
                st.setString(1, "%" + name + "%");
            }

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setDescription(rs.getString("description"));
                    p.setImage(rs.getString("image"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getDouble("price"));
                    p.setQuantity(rs.getInt("quantity"));
                    ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                    ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                    p.setProductType(pt);
                    p.setProductCategoty(c);
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    // search product by id
    public Product searchProductById(int id) {
        String sql = "SELECT [id], [description], [image], [name], [price], [quantity], [product_category_id], [product_id_type] FROM [dbo].[Product] WHERE 1 = 1";
        if (id != 0) {
            sql += " AND [id] = ?";
        }

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            if (id != 0) {
                st.setInt(1, id);
            }

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("id"));
                    p.setDescription(rs.getString("description"));
                    p.setImage(rs.getString("image"));
                    p.setName(rs.getString("name"));
                    p.setPrice(rs.getDouble("price"));
                    p.setQuantity(rs.getInt("quantity"));
                    ProductCategory c = pdc.getCategoryById(rs.getInt("product_category_id"));
                    ProductType pt = ptd.getProductTypeById(rs.getInt("product_id_type"));
                    p.setProductType(pt);
                    p.setProductCategoty(c);
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////
    //Nhập sản phẩm
    public void InputProduct(Product p) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([description]\n"
                + "           ,[image]\n"
                + "           ,[name]\n"
                + "           ,[price]\n"
                + "           ,[quantity]\n"
                + "           ,[product_category_id]\n"
                + "           ,[product_id_type])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getDescription());
            st.setString(2, p.getImage());
            st.setString(3, p.getName());
            st.setDouble(4, p.getPrice());
            st.setInt(5, p.getQuantity());
            ProductCategory pc = p.getProductCategoty();
            if (pc == null) {
                st.setNull(6, java.sql.Types.INTEGER);
            } else {
                st.setInt(6, pc.getId());
            }

            ProductType pt = p.getProductType();
            if (pt == null) {
                st.setNull(7, java.sql.Types.INTEGER);
            } else {
                st.setInt(7, pt.getId());
            }

            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    // xóa sản phẩm 
    public void deleteProduct(int id) {
        String sql = "DELETE FROM [dbo].[Product]\n"
                + "      WHERE id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {

        }
    }

    // update sản phẩm
    public void updateProduct(Product p) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "  SET  [id] = ?"
                + ",[description] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[name] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[product_category_id] = ?\n"
                + "      ,[product_id_type] = ?\n"
                + " WHERE id=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, p.getId());
            st.setString(2, p.getDescription());
            st.setString(3, p.getImage());
            st.setString(4, p.getName());
            st.setDouble(5, p.getPrice());
            st.setInt(6, p.getQuantity());
            st.setInt(7, p.getProductCategoty().getId());
            st.setInt(8, p.getProductType().getId());
            st.setInt(9, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        ProductDao d = new ProductDao();

        Product p = new Product(200, "dep", "3.4_1.png", "thinh", 10000, 100,null ,null );
        d.InputProduct(p);
    }
}
