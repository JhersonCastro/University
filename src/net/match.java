package net;

import University.University;
import Users.Father;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class match {
    static Connection connection = DataBase.Connect(false);
    public static void setFatherWithTheySon(String FatherID, String SonID){
        String sql = "INSERT INTO SonsOfFather(fatherid, sonid) VALUES (?,?)";
        try{
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, FatherID);
            pstmt.setString(2, SonID);
            pstmt.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void getFatherWithTheySon(Father father, University uni){
        try {
            PreparedStatement pstmt =
                    connection.prepareStatement("SELECT * FROM " +
                            "SonsOfFather WHERE FatherID = ?");
            pstmt.setString(1, father.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                father.addSon(uni.searchStudentById(rs.getString("SonID")));
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
