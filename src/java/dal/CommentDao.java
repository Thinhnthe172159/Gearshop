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
import model.Comment;
import model.User;

/**
 *
 * @author thinh
 */
public class CommentDao extends DBContext {
    UserDao udao = new UserDao();
    

    public void addAComment(String mess, int product_id, int user_id) {
        String sql = "INSERT INTO [dbo].[Comment]\n"
                + "           ([mess]\n"
                + "           ,[product_id]\n"
                + "           ,[user_id])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, mess);
            st.setInt(2, product_id);
            st.setInt(3, user_id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

 public List<Comment> showCommentList(int product_id) {
    String sql = "SELECT * FROM [dbo].[Comment] WHERE 1=1 ";
    List<Comment> list = new ArrayList<>();
    
    if (product_id != 0) {
        sql += "AND product_id = ?";
    }
    
    sql += " order by id desc";

    try (PreparedStatement st = connection.prepareStatement(sql)) {
        if (product_id != 0) {
            st.setInt(1, product_id);
        }
        
        try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Comment c = new Comment();
                c.setId(rs.getInt("id"));
                c.setMess(rs.getString("mess"));
                User u = udao.getUserById(rs.getInt("user_id"));
                c.setProduct(rs.getInt("product_id"));
                c.setUser(u);
                list.add(c); 
            }
        }
    } catch (SQLException e) {
        System.out.println(e);
    }
    
    return list;
}
    public static void main(String[] args) {
        CommentDao cdao = new CommentDao();
          List<Comment> list = cdao.showCommentList(100);
        for(Comment item:list){
            System.out.println(item.getUser().getImage());
        }
    }
}
