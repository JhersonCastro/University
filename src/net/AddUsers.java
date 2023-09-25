package net;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUsers {
    static Connection connection = DataBase.Connect(false);
    public static void Add(String name, String ID, String bornDate, String permission){
        String sql = "INSERT INTO Users(name, DNI, bornDate, permission) VALUES (?,?,?,?)";
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, name);
                pstmt.setString(2, ID);
                pstmt.setString(3, bornDate);
                pstmt.setString(4, permission);
                pstmt.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
