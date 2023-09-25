package net;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSubToStd {
    static Connection connection = DataBase.Connect(false);
    public static void Add(String DNI, String subject, String grade){
        String sql = "INSERT INTO StudentGrades(user_id, subject_id, grade) VALUES (?,?,?)";
        try{
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, DNI);
            stmt.setString(2, subject);
            stmt.setString(3, grade);
            stmt.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
