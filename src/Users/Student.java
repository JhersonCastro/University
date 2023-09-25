package Users;

import University.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Student extends User {
    University uni;
    private final Map<Subject, String> subjectsAndGrade = new HashMap<>();
    public Map<Subject, String> getSubjectsAndGrade() {
        return subjectsAndGrade;
    }
    public Student(String name, String bornDate, String ID, University uni) {
        super(name, bornDate, ID);
        this.uni = uni;
    }
    @Override
    public void Menu() {
        int opc;
        do{
            System.out.println("Escoje que quieres hacer: ");
            System.out.println("""
                    1-Revisar las notas
                    2-Calificar a profesores
                    3-Salir""");
            opc = new Scanner(System.in).nextInt();
            switch (opc) {
                case 1 -> printNotes();
                case 2 -> net.Subjects.getCurrentTeacherCoursing(this.ID, uni);
            }
        }while(opc != 3);
    }
    public void printNotes(){
        subjectsAndGrade.forEach( (key, value) -> System.out.println(key.getName() + ": "+ value));
    }

    public void setValueToSubject(Subject sub, String grade){
        subjectsAndGrade.put(sub, grade);
    }
    public String getId(){
        return ID;
    }
    public void setBornDate(String bornDate){
        this.bornDate = bornDate;
    }
    public void setName(String name){
        this.name = name;
    }
}
