package net;

import University.University;
import Users.Father;
import Users.Student;
import Users.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class getData {
    static Connection connection = DataBase.Connect(false);
    public static void setData(University uni) {
        String sql;
        try {
            PreparedStatement pstmt;
            ResultSet rs;
            rs = connection.prepareStatement("SELECT * FROM Subjects").executeQuery();
            while(rs.next()){
                uni.CreateSubject(rs.getString("Name"), rs.getString("ID"));
            }

            sql = "SELECT * FROM Users WHERE permission = ?";
            pstmt =  connection.prepareStatement(sql);
            pstmt.setString(1,"Student");
            rs = pstmt.executeQuery();
            while(rs.next()){
                Student cache = new Student(rs.getString("name"),
                        rs.getString("bornDate"),
                        rs.getString("DNI"), uni);
                Subjects.getSubjects(cache, uni);
                uni.registerStudents(cache);
            }

            pstmt.setString(1,"Father");
            rs = pstmt.executeQuery();
            while(rs.next()){
                Father cache = new Father(rs.getString("name"),
                        rs.getString("bornDate"),
                        rs.getString("DNI"));

                match.getFatherWithTheySon(cache, uni);
                uni.registerFather(cache);
            }

            pstmt.setString(1,"Teacher");
            rs = pstmt.executeQuery();

            while(rs.next()){
                Teacher cache = new Teacher(rs.getString("name"),
                        rs.getString("bornDate"),
                        rs.getString("DNI"), uni);
                uni.registerTeacher(cache);
                Subjects.GetTeacherSubjects(cache);
            }


            System.out.println("Se restablecio correctamente los usuarios");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
