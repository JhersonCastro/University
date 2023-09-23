package University;

import Users.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class University {
    private List<Subject> subjects = new ArrayList<>();
    private List<Student> student = new ArrayList<>();
    private List<Teacher> teachers = new ArrayList<>();
    private List<Father> fathers = new ArrayList<>();
    public List<Subject> getSubjects() {
        return subjects;
    }
    public List<Student> getStudent() {
        return student;
    }
    public Student searchStudentById(String ID){
        Optional<Student> theStudent = student.stream().filter(std -> std.getId().equals(ID)).findFirst();
        return theStudent.orElse(null);
    }
    public Teacher searchTeacherById(String ID){
        Optional<Teacher> theTeacher = teachers.stream().filter(std -> std.getId().equals(ID)).findFirst();
        return theTeacher.orElse(null);
    }
    public Father searchFatherById(String ID){
        Optional<Father> theFather = fathers.stream().filter(std -> std.getId().equals(ID)).findFirst();
        return theFather.orElse(null);
    }
    public Subject searchSubjectById(String ID){
        Optional<Subject> theSubject = subjects.stream().filter(std -> std.getID().equals(ID)).findFirst();
        return theSubject.orElse(null);
    }
    public void CreateSubject(String name, String ID){
        subjects.add(new Subject(name, ID));
    }
    public void registerStudents(Student std) {
        student.add(std);
    }
    public void registerFather(Father father) {
        fathers.add(father);
    }
    public void registerTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
}
