package net;

import University.University;

import java.io.File;
import java.sql.*;

public class DataBase {
    private static final Connection conn = Connect(false);
    private static final String dbName = "Users";
    public static void Create(University uni){
        if(new File("./"+dbName+".db").exists()){
            System.out.println(System.getProperty("user.dir")+new File("./"+dbName+".db").getPath());

            if (conn != null){
                System.out.println("¡Conexion a base de datos exitosa!");
                getData.setData(uni);
            }
        }else{
            Connection conexion = null;
            try {
                Class.forName("org.sqlite.JDBC");
                conexion = DriverManager.getConnection("jdbc:sqlite:./"+dbName+".db");
                System.out.println("Base de datos creada con éxito.");

                assert conexion != null;
                String sql1 = "CREATE TABLE IF NOT EXISTS Users(ID INTEGER PRIMARY KEY, " +
                        "name TEXT," +
                        "bornDate TEXT," +
                        "DNI TEXT," +
                        "permission TEXT);";
                String sql2 = "CREATE TABLE IF NOT EXISTS StudentGrades(" +
                        "ID INTEGER PRIMARY KEY," +
                        "user_id INTEGER," +
                        "subject TEXT," +
                        "subject_id TEXT,"+
                        "grade INTEGER, FOREIGN KEY (user_id) REFERENCES Users(ID));";
                String sql3 = "CREATE TABLE IF NOT EXISTS SonsOfFather(" +
                        "father_id TEXT," +
                        "son_id TEXT)";
                String sql4 = "CREATE TABLE IF NOT EXISTS Professor(DNI TEXT not null, Subjects_ID TEXT not null, AVG_GRADE REAL)";
                String sql5 = "CREATE TABLE IF NOT EXISTS Subjects(Name TEXT not null, ID   TEXT not null)";
                PreparedStatement pstmt1  = conexion.prepareStatement(sql1);
                PreparedStatement pstmt2  = conexion.prepareStatement(sql2);
                PreparedStatement pstmt3  = conexion.prepareStatement(sql3);
                PreparedStatement pstmt4  = conexion.prepareStatement(sql4);
                PreparedStatement pstmt5  = conexion.prepareStatement(sql5);


                pstmt1.executeUpdate();
                pstmt2.executeUpdate();
                pstmt3.executeUpdate();
                pstmt4.executeUpdate();
                pstmt5.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (conexion != null) {
                        conexion.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    public static Connection Connect(boolean debugMode) {
        Connection conexion = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conexion = DriverManager.getConnection("jdbc:sqlite:./"+dbName+".db");
            System.out.printf(debugMode ? "Conexión exitosa a la base de datos." : "");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return conexion;
    }
}
