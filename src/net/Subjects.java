package net;

import University.University;
import Users.Student;
import Users.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Subjects {
    static Connection connection = DataBase.Connect(false);
    public static void Add(String Name, String ID){
        String sql = "INSERT INTO Subjects(Name, ID) VALUES (?,?)";
        try{
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, Name);
            stmt.setString(2, ID);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static void getCurrentTeacherCoursing(String StudendID, University uni){
        List<String> TeacherID = new ArrayList<>();
        List<String> SubjectsID = new ArrayList<>();
        try {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM StudentGrades WHERE user_id = " + StudendID);
            ResultSet rs = pstmt.executeQuery();
            ResultSet rs1;
            while (rs.next()){
                SubjectsID.add(rs.getString("subject_id"));
            }
            for (String item: SubjectsID) {
                pstmt = connection.prepareStatement("SELECT * FROM Professor WHERE Subjects_ID = " + item);
                rs = pstmt.executeQuery();
                System.out.println("Estos son los profesores que haz matriculado: ");
                while (rs.next()){
                    pstmt = connection.prepareStatement("SELECT * FROM Users WHERE DNI = " + rs.getString("DNI"));
                    rs1 = pstmt.executeQuery();
                    System.out.println(rs1.getString("name")
                            + " SU DNI es: " + rs1.getString("DNI")
                            + " Te fue asignado para darte: "
                            + uni.searchSubjectById(item).getName()
                            + " con un id de: "+ item);
                    TeacherID.add(rs1.getString("DNI"));
                }
            }
            Scanner scan = new Scanner(System.in);
            do {
                List<String> cacheSubId = new ArrayList<>();

                System.out.println("Calificar a un profesor, dime el numero de identificacion");
                String txt = scan.nextLine();
                if(TeacherID.contains(txt)){

                    pstmt = connection.prepareStatement(
                            "SELECT s.* FROM StudentGrades s JOIN Professor p ON s.subject_id = p.Subjects_ID " +
                                    "WHERE p.DNI = ? AND s.user_id = ?"
                    );
                    pstmt.setString(1, txt);
                    pstmt.setString(2, StudendID);
                    String temp;
                    rs = pstmt.executeQuery();
                    while (rs.next()){
                        temp = rs.getString("subject_id");
                        cacheSubId.add(temp);

                        System.out.println("El profesor ingresado tiene estas materias matriculadas contigo: ");
                        System.out.println("Materia: " + uni.searchSubjectById(temp)
                                +"Codigo:" + temp);
                    }
                    pstmt = connection.prepareStatement("UPDATE Professor " +
                            "SET AVG_GRADE = " +
                            "(((SELECT AVG_GRADE FROM Professor " +
                            "WHERE DNI = "+ txt +" AND Subjects_ID = ?) + ?)/2)" +
                            " WHERE DNI = "+ txt +" AND Subjects_ID = ?");
                    double note;
                    if(cacheSubId.size() == 1){
                        System.out.println("¿Cuanto se merece el profesor 0-5?");
                        note = scan.nextDouble();

                        pstmt.setString(1, cacheSubId.get(0));
                        pstmt.setDouble(2, note);
                        pstmt.setString(3, cacheSubId.get(0));

                    }
                    else{
                        System.out.println("Tu profesor tiene mas materias");
                        cacheSubId.forEach(i -> System.out.println("Codigo: " + i + " Nombre de la materia: " + uni.searchSubjectById(i).getName()));
                        System.out.println("Di el codigo para calificarlo");
                        String code = scan.nextLine();
                        if (cacheSubId.contains(code)){
                            System.out.println("¿Cuanto se merece el profesor 0-5?");
                            note = scan.nextDouble();
                            pstmt.setString(1, code);
                            pstmt.setDouble(2, note);
                            pstmt.setString(3, code);
                            uni.searchTeacherById(txt).setValueToAvgGrade(code, note);
                        }
                    }
                    pstmt.executeUpdate();
                }
                System.out.println("Escribe yes para salir");
                if(scan.nextLine().equalsIgnoreCase("yes")){
                    return;
                }
            }while(true);

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
    public static void getSubjects(Student student, University uni){
        String sql = "SELECT * FROM StudentGrades WHERE user_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, student.getId());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                student.setValueToSubject(uni.searchSubjectById(rs.getString("subject_id")), rs.getString("grade"));
            }
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Update(String userID, String subjectID, String grade){
        String sql = "UPDATE StudentGrades SET grade = ?" +
                "WHERE user_id = ? AND subject_id = ?";
        try{
            assert connection != null;
            PreparedStatement pstmt  = connection.prepareStatement(sql);
            pstmt.setString(1, grade);
            pstmt.setString(2, userID);
            pstmt.setString(3, subjectID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void AddTeacherSubjects(String ID, String SubjectID){
        String sql = "INSERT INTO Professor(dni, subjects_id) VALUES(?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, ID);
            pstmt.setString(2, SubjectID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void GetTeacherSubjects(Teacher teacher){
        try {
            String sql = "SELECT * FROM Professor WHERE DNI = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, teacher.getId());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                teacher.addSubject(rs.getString("Subjects_ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
