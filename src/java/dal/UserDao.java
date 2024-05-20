/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.User;
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
public class UserDao extends DBContext {

    public User login(String user, String pass) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[phone]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[Role]\n"
                + "      ,[image]\n"
                + "       ,[address]\n"
                + "  FROM [dbo].[User] WHERE [username] = ? AND [password] = ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, user);
            st.setString(2, pass);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    User u = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("Role"),
                            rs.getString("image"),
                            rs.getString("address")
                    );
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User checkUserExist(String user) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[phone]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[Role]\n"
                + "      ,[image]\n"
                + "      ,[address]\n"
                + "  FROM [dbo].[User] WHERE [username]= ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("Role"),
                        rs.getString("image"),
                        rs.getString("address"));
                return u;
            }

        } catch (SQLException e) {

        }
        return null;
    }

    public List<Integer> getTop5UserId(int year) {
        List<Integer> list = new ArrayList<>();
        String sql = "SELECT TOP 5 uu.id as usid, COUNT(oo.id) as count_order\n"
                + "FROM [dbo].[Order] as oo\n"
                + "INNER JOIN [dbo].[User] as uu ON uu.id = oo.user_id\n"
                + "WHERE YEAR(oo.date_order) = ?\n"
                + "GROUP BY uu.id\n"
                + "ORDER BY COUNT(oo.id) DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int user_id = rs.getInt("usid");
                list.add(user_id);
            }
        } catch (SQLException e) {

        }
        return list;
    }

    public User getUserById(int id) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[phone]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[Role]\n"
                + "      ,[image]\n"
                + "      ,[address]\n"
                + "  FROM [dbo].[User] WHERE [id]= ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    User u = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("Role"),
                            rs.getString("image"),
                            rs.getString("address"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public User getUserByEmail(String Email) {
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[email]\n"
                + "      ,[phone]\n"
                + "      ,[username]\n"
                + "      ,[password]\n"
                + "      ,[Role]\n"
                + "      ,[image]\n"
                + "      ,[address]\n"
                + "  FROM [dbo].[User] WHERE [email]= ?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, Email);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    User u = new User(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getInt("Role"),
                            rs.getString("image"),
                            rs.getString("address"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public List<User> getAll(int role, int sort, String name) {
        List<User> list = new ArrayList<>();
        String sql = "select * from [dbo].[User] where [Role] = ?";

        if (name != null && !name.isEmpty()) {
            sql += " and [name] like '%" + name + "%'";
        }

        if (sort == 1) {
            sql += " order by [name]";
        } else if (sort == 2) {
            sql += " order by [name] desc";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, role);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("Role"),
                        rs.getString("image"),
                        rs.getString("address")
                );
                list.add(u);
            }

            // Close resources
            rs.close();
            st.close();
        } catch (SQLException e) {
            // Handle SQLException
            e.printStackTrace();
        }
        return list;
    }

    public void register(User user) {
        // SQL query to insert a new user into the database
        String sql = "INSERT INTO [dbo].[User] "
                + "( [name], [email], [phone], [username], [password], [Role], [image], [address] ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setInt(6, user.getRole());
            statement.setString(7, user.getImage());
            statement.setString(8, user.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        UserDao ld = new UserDao();
//        User user = new User(5, "Vịt em", "anhNguyen@123.com", "0987612344", "", "", 0, "", "hà nội");
//        ld.updateUser(user);
//    }
    public void updateUser(User u) {
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET [name] = ?,\n"
                + "      [email] = ?,\n"
                + "      [phone] = ?,\n"
                + "      [username] = ?,\n"
                + "      [password] = ?,\n"
                + "      [Role] = ?,\n"
                + "      [image] = ?,\n"
                + "      [address]=?\n"
                + " WHERE [id] =?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, u.getName());
            st.setString(2, u.getEmail());
            st.setString(3, u.getPhone());
            st.setString(4, u.getUsername());
            st.setString(5, u.getPassword());
            st.setInt(6, u.getRole());
            st.setString(7, u.getImage());
            st.setString(8, u.getAddress());
            st.setInt(9, u.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete user
    public void deleteUser(int id) {
        String sql = "DELETE FROM [dbo].[User]\n"
                + "      WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<User> getListByPage(List<User> list, int start, int end) {
        ArrayList<User> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

}
