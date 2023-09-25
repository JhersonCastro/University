package net;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUsers {
    static Connection conn = DataBase.Connect(false);
    public static void Update(String name, String bornDate, String ID, String permission){
        try{
            assert conn != null;
            String sql = "UPDATE Users SET name = ?, bornDate = ? WHERE ID = ? AND permission = ?";
            PreparedStatement pstmt  = conn.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, bornDate);
                pstmt.setString(3, ID);
                pstmt.setString(4, permission);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
